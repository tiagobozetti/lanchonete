package com.finch.burguer.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finch.burguer.models.Ingrediente;
import com.finch.burguer.models.PedidoItem;
import com.finch.burguer.models.Promocao;
import com.finch.burguer.models.PromocaoIngrediente;
import com.finch.burguer.models.enums.TipoPromocaoEnum;
import com.finch.burguer.repositories.PromocaoRepository;
import com.finch.burguer.services.exceptions.ObjectNotFoundException;
import com.finch.burguer.services.exceptions.RegraNegocioException;

@Service
public class PromocaoService extends AbstractService<Promocao, Long> {

	@Autowired
	private PromocaoRepository promocaoRepository;

	@Override
	protected JpaRepository<Promocao, Long> getRepository() {
		return promocaoRepository;
	}

	public Promocao find(Long codigoPromocao) {
		Optional<Promocao> ing = promocaoRepository.findById(codigoPromocao);
		return ing.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + codigoPromocao + ", Tipo: " + Promocao.class.getName()));
	}

	@Transactional
	public void calcularPromocao(PedidoItem pedidoItem) {
		//List<PedidoItemIngrediente> ingredientesPromocao = ingredienteService.findByPedidoItem(pedidoItem.getCodigoPedidoItem());
		pedidoItem.setValorUnitario(pedidoItem.getIngredientes().stream()
				.map(d->d.getIngrediente().getValor().multiply(BigDecimal.valueOf(d.getQuantidade())))
				.reduce(BigDecimal.ZERO,BigDecimal::add));
		pedidoItem.setValorTotal(pedidoItem.getValorUnitario().multiply(BigDecimal.valueOf(pedidoItem.getQuantidade())));
		pedidoItem.setValorDescontoUnitario(BigDecimal.ZERO);
		pedidoItem.setValorDescontoTotal(BigDecimal.ZERO);
		BigDecimal valorDesconto = BigDecimal.ZERO;
				
		for (Promocao promocao : promocaoRepository.findAllByOrderByDesconto()) {
			Boolean ativarPromocao = true;

			// Promocao com desconto em ingredientes
			if (promocao.getTipoPromocao().equals(TipoPromocaoEnum.PORCENTAGEM)) {
				for (Ingrediente ing : promocao.getIngredientesExclusao()) {
					if (pedidoItem.getIngredientes().stream()
							.filter(obj -> ing.getCodigoIngrediente().equals(obj.getIngrediente().getCodigoIngrediente()))
							.count() > 0) {
						ativarPromocao = false;
					}
				}

				if (ativarPromocao) {
					ativarPromocao = false;
					for (PromocaoIngrediente ing : promocao.getPromocaoIngredientes()) {
						if (pedidoItem.getIngredientes().stream()
								.filter(obj -> ing.getIngrediente().getCodigoIngrediente().equals(obj.getIngrediente().getCodigoIngrediente()))
								.count() > 0) {
							ativarPromocao = true;
						}
					}
				}

				if (ativarPromocao) {
					BigDecimal totalPedido = pedidoItem.getIngredientes().stream()
							.map(d -> d.getIngrediente().getValor().multiply(BigDecimal.valueOf(d.getQuantidade())))
							.reduce(BigDecimal.ZERO,BigDecimal::add);
					
					valorDesconto = valorDesconto.add((totalPedido.subtract(valorDesconto)).multiply((promocao.getDesconto().divide(BigDecimal.valueOf(100)))));					
				}
			}
			
			// Promoção por quantidade
			if (promocao.getTipoPromocao().equals(TipoPromocaoEnum.QUANTIDADE)) {
				
				//Verificar se o ingrediente do promocao possui promoção
				for (PromocaoIngrediente promocaoIngrediente: promocao.getPromocaoIngredientes()) {
					Ingrediente ingPromocao = promocaoIngrediente.getIngrediente();
					
					if (pedidoItem.getIngredientes().stream().
							filter(obj -> ingPromocao.getCodigoIngrediente().equals(obj.getIngrediente().getCodigoIngrediente())).count() > 0) {
						Integer qtdeTotalIngrediente = pedidoItem.getIngredientes().stream()
								.filter(ing -> promocaoIngrediente.getIngrediente().getCodigoIngrediente().equals(ing.getIngrediente().getCodigoIngrediente()))
								.mapToInt(ing -> ing.getQuantidade())
								.sum();
						
						if (qtdeTotalIngrediente >= promocaoIngrediente.getQuantidadeLevar()) {
							Integer diferencaLevarPagar = promocaoIngrediente.getQuantidadeLevar() - promocaoIngrediente.getQuantidadePagar();
							Integer qtdePromocao = qtdeTotalIngrediente/promocaoIngrediente.getQuantidadeLevar();
							
							valorDesconto = valorDesconto.add(promocaoIngrediente.getIngrediente().getValor().multiply(BigDecimal.valueOf(diferencaLevarPagar*qtdePromocao)));
						}
					}					
				}
			}
		}
		
		if (valorDesconto.compareTo(BigDecimal.ZERO) > 0) {
			pedidoItem.setValorDescontoUnitario(valorDesconto);
			pedidoItem.setValorUnitario(pedidoItem.getValorUnitario().subtract(valorDesconto));				
			pedidoItem.setValorTotal(pedidoItem.getValorUnitario().multiply(BigDecimal.valueOf(pedidoItem.getQuantidade())));
			pedidoItem.setValorDescontoUnitario(valorDesconto);
			pedidoItem.setValorDescontoTotal(valorDesconto.multiply(BigDecimal.valueOf(pedidoItem.getQuantidade())));
		}
	}

	@Override
	public Promocao save(Promocao promocao) {

		if (promocao.getTipoPromocao().equals(TipoPromocaoEnum.PORCENTAGEM) && promocao.getDesconto().compareTo(BigDecimal.ZERO) == 0) {
			throw new RegraNegocioException("Informar o percentual de desconto.");
		}

		if (promocao.getTipoPromocao().equals(TipoPromocaoEnum.QUANTIDADE)
				&& !promocao.getIngredientesExclusao().isEmpty()) {
			throw new RegraNegocioException(
					"Desconto por quantidade não possui ingredientes que desativam a promoção.");
		}

		if (promocao.getTipoPromocao().equals(TipoPromocaoEnum.QUANTIDADE) && promocao.getDesconto().compareTo(BigDecimal.ZERO) > 0) {
			throw new RegraNegocioException("Não informar o percentual de desconto para o tipo de promoção.");
		}

		return super.save(promocao);
	}

	public Page<Promocao> search(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return promocaoRepository.findAll(pageRequest);
	}

	public void update(Promocao promocao) {
		Promocao pro = this.find(promocao.getCodigoPromocao());
		pro.setPromocao(promocao.getPromocao());
		pro.setDescricao(promocao.getDescricao());
		pro.setDesconto(promocao.getDesconto());
		pro.setTipoPromocao(promocao.getTipoPromocao());
		this.save(pro);
	}

	@Override
	public void deleteById(Long codigoPromocao) {
		// Promocao lan = this.find(codigoPromocao);

		// If (!lan.getPedidosItem().isEmpty()) {
		// throw new RegraNegocioException("Promocao possui pedido associado, impossível
		// excluir.");
		// }

		super.deleteById(codigoPromocao);
	}
}
