package com.finch.burguer.models;

import java.io.Serializable;
import java.util.ArrayList;
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
public class Lanche implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
    @SequenceGenerator(name = "seq_lanche", sequenceName = "seq_lanche", allocationSize = 1)
    @GeneratedValue(generator = "seq_lanche", strategy = GenerationType.AUTO)
	private Long codigoLanche;
	
	@NotNull
	private String lanche;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="lanche", cascade=CascadeType.ALL)
	private List<LancheIngrediente> lancheIngredientes = new ArrayList<>();
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="lanche", cascade=CascadeType.ALL)
	private List<PedidoItem> pedidosItem = new ArrayList<>();	
	
	public Lanche() {
		
	}

	public Lanche(Long codigoLanche) {
		super();
		this.codigoLanche = codigoLanche;
	}
	
	public Lanche(String lanche) {
		super();
		this.lanche = lanche;
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

	public List<LancheIngrediente> getLancheIngredientes() {
		return lancheIngredientes;
	}

	public void setLancheIngredientes(List<LancheIngrediente> lancheIngredientes) {
		this.lancheIngredientes = lancheIngredientes;
	}

	public List<PedidoItem> getPedidosItem() {
		return pedidosItem;
	}

	public void setPedidosItem(List<PedidoItem> pedidosItem) {
		this.pedidosItem = pedidosItem;
	}
	
	
}
