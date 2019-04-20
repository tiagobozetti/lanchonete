package com.finch.burguer.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity
public class PedidoItemIngrediente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
    @SequenceGenerator(name = "seq_pedidoitemingrediente", sequenceName = "seq_pedidoitemingrediente", allocationSize = 1)
    @GeneratedValue(generator = "seq_pedidoitemingrediente", strategy = GenerationType.AUTO)
	private Long codigoPedidoItemIngrediente;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="CODIGOPEDIDOITEM", referencedColumnName="CODIGOPEDIDOITEM")
	private PedidoItem pedidoItem;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="CODIGOINGREDIENTE", referencedColumnName="CODIGOINGREDIENTE")
	private Ingrediente ingrediente;
	
	private Integer quantidade = 1;
	
	public PedidoItemIngrediente() {
		
	}
	
	public PedidoItemIngrediente(PedidoItem pedidoItem, Ingrediente ingrediente, Integer quantidade) {
		super();
		this.pedidoItem = pedidoItem;
		this.ingrediente = ingrediente;
		this.quantidade = quantidade;
	}

	public Long getCodigoPedidoItemIngrediente() {
		return codigoPedidoItemIngrediente;
	}

	public void setCodigoPedidoItemIngrediente(Long codigoPedidoItemIngrediente) {
		this.codigoPedidoItemIngrediente = codigoPedidoItemIngrediente;
	}

	public PedidoItem getPedidoItem() {
		return pedidoItem;
	}

	public void setPedidoItem(PedidoItem pedidoItem) {
		this.pedidoItem = pedidoItem;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
}
