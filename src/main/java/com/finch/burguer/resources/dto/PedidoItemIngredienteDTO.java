package com.finch.burguer.resources.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.finch.burguer.models.PedidoItemIngrediente;

public class PedidoItemIngredienteDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long codigoPedidoItemIngrediente;
	private Long codigoIngrediente;
	private String ingrediente;
	private BigDecimal valor = BigDecimal.ZERO;
	private Integer quantidade;
	
	public PedidoItemIngredienteDTO() {
		
	}
	
	public PedidoItemIngredienteDTO(PedidoItemIngrediente pedidoItemIngrediente) {
		this.codigoPedidoItemIngrediente = pedidoItemIngrediente.getCodigoPedidoItemIngrediente();
		this.codigoIngrediente = pedidoItemIngrediente.getIngrediente().getCodigoIngrediente();
		this.ingrediente = pedidoItemIngrediente.getIngrediente().getIngrediente();
		this.valor = pedidoItemIngrediente.getIngrediente().getValor();
		this.quantidade = pedidoItemIngrediente.getQuantidade();
	}

	public Long getCodigoPedidoItemIngrediente() {
		return codigoPedidoItemIngrediente;
	}

	public void setCodigoPedidoItemIngrediente(Long codigoPedidoItemIngrediente) {
		this.codigoPedidoItemIngrediente = codigoPedidoItemIngrediente;
	}

	public Long getCodigoIngrediente() {
		return codigoIngrediente;
	}

	public void setCodigoIngrediente(Long codigoIngrediente) {
		this.codigoIngrediente = codigoIngrediente;
	}

	public String getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(String ingrediente) {
		this.ingrediente = ingrediente;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
}
