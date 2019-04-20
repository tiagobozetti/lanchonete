package com.finch.burguer.resources.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.finch.burguer.models.Ingrediente;
import com.finch.burguer.models.Lanche;
import com.finch.burguer.models.Pedido;
import com.finch.burguer.models.PedidoItem;
import com.finch.burguer.models.PedidoItemIngrediente;

public class PedidoDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long codigoPedido;
	private Date dataPedido;
	private BigDecimal valor = BigDecimal.ZERO;
	private List<PedidoItemDTO> itens = new ArrayList<>();
	
	public PedidoDTO() {
		
	}

	public PedidoDTO(Pedido pedido) {
		super();
		this.codigoPedido = pedido.getCodigoPedido();
		this.dataPedido = pedido.getDataPedido();
		this.valor = pedido.getValorTotal();
		
		for(PedidoItem item: pedido.getItens()) {
			PedidoItemDTO itemDTO = new PedidoItemDTO(item);
			itens.add(itemDTO);
		}
	}
	
	public Long getCodigoPedido() {
		return codigoPedido;
	}

	public void setCodigoPedido(Long codigoPedido) {
		this.codigoPedido = codigoPedido;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public List<PedidoItemDTO> getItens() {
		return itens;
	}

	public void setItens(List<PedidoItemDTO> itens) {
		this.itens = itens;
	}
	
	public Pedido build() {
		Pedido pedido = new Pedido();
		pedido.setDataPedido(dataPedido);
		for(PedidoItemDTO item: itens) {
			PedidoItem pi = new PedidoItem();
			pi.setPedido(pedido);
			pi.setLanche(new Lanche(item.getCodigoLanche()));
			
			for(PedidoItemIngredienteDTO ingredientes: item.getIngredientes()) {
				Ingrediente ing = new Ingrediente(ingredientes.getCodigoIngrediente());
				PedidoItemIngrediente piIngrediente = new PedidoItemIngrediente(pi, ing, ingredientes.getQuantidade());
				pi.getIngredientes().add(piIngrediente);
			}
			pedido.getItens().add(pi);
		}
		return pedido;
	}
}
