package com.finch.burguer.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity
public class Ingrediente implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
    @SequenceGenerator(name = "seq_ingrediente", sequenceName = "seq_ingrediente", allocationSize = 1)
    @GeneratedValue(generator = "seq_ingrediente", strategy = GenerationType.AUTO)
	private Long codigoIngrediente;
	
	@NotNull
	private String ingrediente;
	
	@NotNull
	private BigDecimal valor = BigDecimal.ZERO;
	
	public Ingrediente() {
		
	}

	public Ingrediente(Long codigoIngrediente) {
		super();
		this.codigoIngrediente = codigoIngrediente;
	}
	
	public Ingrediente(String ingrediente, BigDecimal valor) {
		super();
		this.ingrediente = ingrediente;
		this.valor = valor;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoIngrediente == null) ? 0 : codigoIngrediente.hashCode());
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
		Ingrediente other = (Ingrediente) obj;
		if (codigoIngrediente == null) {
			if (other.codigoIngrediente != null)
				return false;
		} else if (!codigoIngrediente.equals(other.codigoIngrediente))
			return false;
		return true;
	}
	
}
