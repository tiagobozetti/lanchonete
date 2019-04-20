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
public class PromocaoIngrediente implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
    @SequenceGenerator(name = "seq_promocaoingrediente", sequenceName = "seq_promocaoingrediente", allocationSize = 1)
    @GeneratedValue(generator = "seq_promocaoingrediente", strategy = GenerationType.AUTO)	
	private Long codigoPromocaoIngrediente;
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CODIGOPROMOCAO", referencedColumnName = "CODIGOPROMOCAO")
	private Promocao promocao;
	
	@NotNull 
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CODIGOINGREDIENTE", referencedColumnName = "CODIGOINGREDIENTE")
	private Ingrediente ingrediente;
	
	private Integer quantidadeLevar;	
	private Integer quantidadePagar;
	
	public PromocaoIngrediente() {
		
	}
	
	public PromocaoIngrediente(Promocao promocao, Ingrediente ingrediente, Integer quantidadeLevar, Integer quantidadePagar) {
		super();
		this.promocao = promocao;
		this.ingrediente = ingrediente;
		this.quantidadeLevar = quantidadeLevar;
		this.quantidadePagar = quantidadePagar;
	}

	public Long getCodigoPromocaoIngrediente() {
		return codigoPromocaoIngrediente;
	}

	public void setCodigoPromocaoIngrediente(Long codigoPromocaoIngrediente) {
		this.codigoPromocaoIngrediente = codigoPromocaoIngrediente;
	}

	public Promocao getPromocao() {
		return promocao;
	}

	public void setPromocao(Promocao promocao) {
		this.promocao = promocao;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	public Integer getQuantidadeLevar() {
		return quantidadeLevar;
	}

	public void setQuantidadeLevar(Integer quantidadeLevar) {
		this.quantidadeLevar = quantidadeLevar;
	}

	public Integer getQuantidadePagar() {
		return quantidadePagar;
	}

	public void setQuantidadePagar(Integer quantidadePagar) {
		this.quantidadePagar = quantidadePagar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoPromocaoIngrediente == null) ? 0 : codigoPromocaoIngrediente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PromocaoIngrediente other = (PromocaoIngrediente) obj;
		if (codigoPromocaoIngrediente == null) {
			if (other.codigoPromocaoIngrediente != null)
				return false;
		} else if (!codigoPromocaoIngrediente.equals(other.codigoPromocaoIngrediente))
			return false;
		return true;
	}
	
}
