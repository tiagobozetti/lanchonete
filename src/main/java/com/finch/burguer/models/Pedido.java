package com.finch.burguer.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity
public class Pedido implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
    @SequenceGenerator(name = "seq_pedido", sequenceName = "seq_pedido", allocationSize = 1)
    @GeneratedValue(generator = "seq_pedido", strategy = GenerationType.AUTO)
	private Long codigoPedido;
	
	@NotNull
	private Date dataPedido;
	
	private BigDecimal valorTotal = BigDecimal.ZERO;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="pedido", cascade=CascadeType.ALL)
	private List<PedidoItem> itens = new ArrayList<>();
	
	public Pedido() {
		
	}
	
	public Pedido(Date dataPedido) {
		super();
		this.dataPedido = dataPedido;
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

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<PedidoItem> getItens() {
		return itens;
	}

	public void setItens(List<PedidoItem> itens) {
		this.itens = itens;
	}
	
}
