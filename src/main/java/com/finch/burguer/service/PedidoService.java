package com.finch.burguer.service;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finch.burguer.models.Pedido;
import com.finch.burguer.models.PedidoItem;
import com.finch.burguer.repositories.PedidoRepository;
import com.finch.burguer.services.exceptions.RegraNegocioException;

@Service
public class PedidoService extends AbstractService<Pedido,Long>{

	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PromocaoService promocaoService;
	@Autowired
	private EntityManager entityManager;
	
	@Override
    protected JpaRepository<Pedido, Long> getRepository() {
        return pedidoRepository;
    }
	
	@Transactional
	@Override
	public Pedido save(Pedido pedido) {
		
		if (pedido.getItens().isEmpty()) {
			throw new RegraNegocioException("Pedido não possui itens.");
		}
		
		pedido = pedidoRepository.saveAndFlush(pedido);	
		entityManager.refresh(pedido);
		calcularValores(pedido);		
		
		return pedidoRepository.save(pedido);
	}
	
	public void calcularValores(Pedido pedido) {
		
		for(PedidoItem pi: pedido.getItens()) {
			promocaoService.calcularPromocao(pi);
		}
		pedido.setValorTotal(pedido.getItens().stream().map(itens -> itens.getValorTotal()).reduce(BigDecimal.ZERO,BigDecimal::add));
	}
	
	public Page<Pedido> search(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return pedidoRepository.findAll(pageRequest);
	}
}
