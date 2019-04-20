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

import com.finch.burguer.resources.dto.LancheIngredienteDTO;

@Entity
public class LancheIngrediente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
    @SequenceGenerator(name = "seq_lancheIngrediente", sequenceName = "seq_lancheIngrediente", allocationSize = 1)
    @GeneratedValue(generator = "seq_lancheIngrediente", strategy = GenerationType.AUTO)
	private Long codigoLancheIngrediente;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="CODIGOLANCHE", referencedColumnName="CODIGOLANCHE")
	private Lanche lanche;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="CODIGOINGREDIENTE", referencedColumnName="CODIGOINGREDIENTE")
	private Ingrediente ingrediente;
	
	private Integer quantidade = 1;

	public LancheIngrediente() {
		
	}
	
	public LancheIngrediente(Lanche lanche, Ingrediente ingrediente, Integer quantidade) {
		super();
		this.lanche = lanche;
		this.ingrediente = ingrediente;
		this.quantidade = quantidade;
	}

	public LancheIngrediente(Lanche lanche, LancheIngredienteDTO ing) {
		super();
		this.ingrediente = new Ingrediente(ing.getCodigoIngrediente());
		this.lanche = lanche;
		this.quantidade = ing.getQuantidade();
	}

	public Long getCodigoLancheIngrediente() {
		return codigoLancheIngrediente;
	}

	public void setCodigoLancheIngrediente(Long codigoLancheIngrediente) {
		this.codigoLancheIngrediente = codigoLancheIngrediente;
	}

	public Lanche getLanche() {
		return lanche;
	}

	public void setLanche(Lanche lanche) {
		this.lanche = lanche;
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
