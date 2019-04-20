package com.finch.burguer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.finch.burguer.models.LancheIngrediente;
import com.finch.burguer.repositories.LancheIngredienteRepository;
import com.finch.burguer.services.exceptions.ObjectNotFoundException;

@Service
public class LancheIngredienteService extends AbstractService<LancheIngrediente,Long>{

	@Autowired
	private LancheIngredienteRepository lancheIngredienteRepository;
	
    @Override
    protected JpaRepository<LancheIngrediente, Long> getRepository() {
        return lancheIngredienteRepository;
    }
	
	public LancheIngrediente find(Long codigoLancheIngrediente) {
		return lancheIngredienteRepository.findById(codigoLancheIngrediente).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + codigoLancheIngrediente + ", Tipo: " + LancheIngrediente.class.getName()));
	}
	
	public void update(LancheIngrediente lancheIngrediente) {
		LancheIngrediente lanIngrediente = this.find(lancheIngrediente.getCodigoLancheIngrediente());
		lanIngrediente.setQuantidade(lancheIngrediente.getQuantidade());
		this.save(lanIngrediente);
	}
	
	@Override
	public void deleteById(Long codigoLancheIngrediente) {
		this.find(codigoLancheIngrediente);
		
		super.deleteById(codigoLancheIngrediente);
	}
}
