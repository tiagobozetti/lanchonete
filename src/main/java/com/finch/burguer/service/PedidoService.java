package com.finch.burguer.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finch.burguer.models.Lanche;
import com.finch.burguer.models.LancheIngrediente;
import com.finch.burguer.models.Pedido;
import com.finch.burguer.models.PedidoItem;
import com.finch.burguer.models.PedidoItemIngrediente;
import com.finch.burguer.repositories.PedidoRepository;
import com.finch.burguer.services.exceptions.RegraNegocioException;

@Service
public class PedidoService extends AbstractService<Pedido,Long>{

	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private LancheService lancheService;
	@Autowired
	private PromocaoService promocaoService;
	
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
		
		/*Adicionar ingredientes padrões do lanche*/
		for(PedidoItem item: pedido.getItens()) {
			Lanche lanche = lancheService.find(item.getLanche().getCodigoLanche());
			for (LancheIngrediente lancheIngrediente: lanche.getLancheIngredientes()) {
				item.getIngredientes().add(new PedidoItemIngrediente(item, lancheIngrediente.getIngrediente(), lancheIngrediente.getQuantidade()));
			}
		}
		
		pedidoRepository.save(pedido);
				
		calcularValores(pedido);
		pedidoRepository.save(pedido);
		
		return pedido;
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
