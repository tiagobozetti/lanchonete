package com.finch.burguer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.finch.burguer.models.Lanche;
import com.finch.burguer.repositories.LancheRepository;
import com.finch.burguer.services.exceptions.ObjectNotFoundException;
import com.finch.burguer.services.exceptions.RegraNegocioException;

@Service
public class LancheService extends AbstractService<Lanche,Long>{

	@Autowired
	private LancheRepository lancheRepository;
	
    @Override
    protected JpaRepository<Lanche, Long> getRepository() {
        return lancheRepository;
    }
	
	public Lanche find(Long codigoLanche) {
		return lancheRepository.findById(codigoLanche).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + codigoLanche + ", Tipo: " + Lanche.class.getName()));
	}
	
	public Page<Lanche> search(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return lancheRepository.findAll(pageRequest);
	}
	
	public void update(Lanche lanche) {
		Lanche lan = this.find(lanche.getCodigoLanche());
		lan.setLanche(lanche.getLanche());
		this.save(lan);
	}
	
	@Override
	public void deleteById(Long codigoLanche) {
		Lanche lan = this.find(codigoLanche);
		
		if (!lan.getPedidosItem().isEmpty()) {
			throw new RegraNegocioException("Lanche possui pedido associado, impossível excluir.");
		}
		
		super.deleteById(codigoLanche);
	}
}
