package com.finch.burguer.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity
public class PedidoItem implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
    @SequenceGenerator(name = "seq_pedidoitem", sequenceName = "seq_pedidoitem", allocationSize = 1)
    @GeneratedValue(generator = "seq_pedidoitem", strategy = GenerationType.AUTO)
	private Long codigoPedidoItem;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="CODIGOPEDIDO", referencedColumnName="CODIGOPEDIDO")
	private Pedido pedido;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="CODIGOLANCHE", referencedColumnName="CODIGOLANCHE")
	private Lanche lanche;	
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="pedidoItem", cascade=CascadeType.ALL)
	private List<PedidoItemIngrediente> ingredientes = new ArrayList<>();
	
	private Integer quantidade = 1;
	private BigDecimal valorUnitario = BigDecimal.ZERO;
	private BigDecimal valorDescontoUnitario = BigDecimal.ZERO;
	private BigDecimal valorDescontoTotal = BigDecimal.ZERO;
	private BigDecimal valorTotal = BigDecimal.ZERO;
	
	public PedidoItem() {
		
	}
	
	public PedidoItem(Pedido pedido, Lanche lanche, Integer quantidade) {
		super();
		this.pedido = pedido;
		this.lanche = lanche;
		this.quantidade = quantidade;
	}

	public Long getCodigoPedidoItem() {
		return codigoPedidoItem;
	}

	public void setCodigoPedidoItem(Long codigoPedidoItem) {
		this.codigoPedidoItem = codigoPedidoItem;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Lanche getLanche() {
		return lanche;
	}

	public void setLanche(Lanche lanche) {
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

	public BigDecimal getValorDescontoTotal() {
		return valorDescontoTotal;
	}

	public void setValorDescontoTotal(BigDecimal valorDescontoTotal) {
		this.valorDescontoTotal = valorDescontoTotal;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<PedidoItemIngrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<PedidoItemIngrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}
	
}
