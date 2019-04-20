package com.finch.burguer.resources.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.finch.burguer.models.Ingrediente;

public class IngredienteDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long codigoIngrediente;
	private String ingrediente;
	private BigDecimal valor = BigDecimal.ZERO;
	
	public IngredienteDTO() {
		
	}
	
	public IngredienteDTO(Ingrediente ingrediente, Integer quantidade) {
		this.codigoIngrediente = ingrediente.getCodigoIngrediente();
		this.ingrediente = ingrediente.getIngrediente();
		this.valor = ingrediente.getValor();
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
	
	public Ingrediente build() {
		return new Ingrediente(this.ingrediente, this.valor);		
	}
}
