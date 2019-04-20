package com.finch.burguer.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.finch.burguer.models.Ingrediente;
import com.finch.burguer.repositories.IngredienteRepository;
import com.finch.burguer.services.exceptions.ObjectNotFoundException;

@Service
public class IngredienteService extends AbstractService<Ingrediente,Long>{

	@Autowired
	private IngredienteRepository ingredienteRepository;
	
    @Override
    protected JpaRepository<Ingrediente, Long> getRepository() {
        return ingredienteRepository;
    }
	
	public Ingrediente find(Long codigoIngrediente) {
		Optional<Ingrediente> ing = ingredienteRepository.findById(codigoIngrediente);
		return ing.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + codigoIngrediente + ", Tipo: " + Ingrediente.class.getName()));
	}

	public void atualizarPreco(BigDecimal inflacao) {
		ingredienteRepository.setValorForIngrediente(inflacao);
	}
	
	public void atualizarPreco(Long codigoIngrediente, BigDecimal inflacao) {
		Ingrediente ing = find(codigoIngrediente);
		
		ing.setValor(ing.getValor().add((ing.getValor().multiply(inflacao))));
		ingredienteRepository.save(ing);
	}
	
	public List<Ingrediente> findByPedidoItem(Long codigoPedidoItem) {
		return ingredienteRepository.findByPedidoItem(codigoPedidoItem);
	}
	
	public List<Ingrediente> findByPromocao(Long codigoPromocao) {
		return ingredienteRepository.findByPromocao(codigoPromocao);
	}
	
	public Page<Ingrediente> search(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return ingredienteRepository.findAll(pageRequest);
	}

}
