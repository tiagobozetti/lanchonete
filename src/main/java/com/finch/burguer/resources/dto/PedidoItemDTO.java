package com.finch.burguer.resources.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.finch.burguer.models.PedidoItem;
import com.finch.burguer.models.PedidoItemIngrediente;

public class PedidoItemDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigoLanche;
	private String lanche;
	private Integer quantidade;
	private BigDecimal valorUnitario = BigDecimal.ZERO;
	private BigDecimal valorDescontoUnitario = BigDecimal.ZERO;
	private BigDecimal valorTotal = BigDecimal.ZERO;
	private BigDecimal valorDescontoTotal = BigDecimal.ZERO;
	private List<PedidoItemIngredienteDTO> ingredientes = new ArrayList<>();

	public PedidoItemDTO() {

	}

	public PedidoItemDTO(PedidoItem item) {
		super();
		this.codigoLanche = item.getLanche().getCodigoLanche();
		this.lanche = item.getLanche().getLanche();
		this.quantidade = item.getQuantidade();
		this.valorUnitario = item.getValorUnitario();
		this.valorDescontoUnitario = item.getValorDescontoUnitario();
		this.valorTotal = item.getValorTotal();
		this.valorDescontoTotal = item.getValorDescontoTotal();
		for (PedidoItemIngrediente piIngrediente : item.getIngredientes()) {
			PedidoItemIngredienteDTO ingDTO = new PedidoItemIngredienteDTO(piIngrediente);
			this.ingredientes.add(ingDTO);
		}
	}

	public Long getCodigoLanche() {
		return codigoLanche;
	}

	public void setCodigoLanche(Long codigoLanche) {
		this.codigoLanche = codigoLanche;
	}

	public String getLanche() {
		return lanche;
	}

	public void setLanche(String lanche) {
		this.lanche = lanche;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public BigDecimal getValorDescontoUnitario() {
		return valorDescontoUnitario;
	}

	public void setValorDescontoUnitario(BigDecimal valorDescontoUnitario) {
		this.valorDescontoUnitario = valorDescontoUnitario;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorDescontoTotal() {
		return valorDescontoTotal;
	}

	public void setValorDescontoTotal(BigDecimal valorDescontoTotal) {
		this.valorDescontoTotal = valorDescontoTotal;
	}

	public List<PedidoItemIngredienteDTO> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<PedidoItemIngredienteDTO> ingredientes) {
		this.ingredientes = ingredientes;
	}

}
