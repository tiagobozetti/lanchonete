package com.finch.burguer.resources.dto;

import com.finch.burguer.models.PromocaoIngrediente;

public class PromocaoIngredienteDTO {
	
	private Long codigoIngrediente;
	private String ingrediente;
	private Integer quantidadeLevar;
	private Integer quantidadePagar;

	public PromocaoIngredienteDTO() {
		
	}
	
	public PromocaoIngredienteDTO(Long codigoIngrediente, String ingrediente, Integer quantidadeLevar, Integer quantidadePagar) {
		super();
		this.codigoIngrediente = codigoIngrediente;
		this.ingrediente = ingrediente;
		this.quantidadeLevar = quantidadeLevar;
		this.quantidadePagar = quantidadePagar;
	}

	public PromocaoIngredienteDTO(PromocaoIngrediente promocaoIngrediente) {
		super();
		this.codigoIngrediente = promocaoIngrediente.getIngrediente().getCodigoIngrediente();
		this.ingrediente = promocaoIngrediente.getIngrediente().getIngrediente();
		this.quantidadeLevar = promocaoIngrediente.getQuantidadeLevar();
		this.quantidadePagar = promocaoIngrediente.getQuantidadePagar();
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
}
