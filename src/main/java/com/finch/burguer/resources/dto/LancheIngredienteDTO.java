package com.finch.burguer.resources.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.finch.burguer.models.Ingrediente;
import com.finch.burguer.models.LancheIngrediente;

public class LancheIngredienteDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long codigoLancheIngrediente;
	private Long codigoIngrediente;
	private String ingrediente;
	private BigDecimal valor = BigDecimal.ZERO;
	private Integer quantidade;
	
	public LancheIngredienteDTO() {
		
	}
	
	public LancheIngredienteDTO(LancheIngrediente lancheIngrediente) {
		this.codigoLancheIngrediente = lancheIngrediente.getCodigoLancheIngrediente();
		this.codigoIngrediente = lancheIngrediente.getIngrediente().getCodigoIngrediente();
		this.ingrediente = lancheIngrediente.getIngrediente().getIngrediente();
		this.valor = lancheIngrediente.getIngrediente().getValor();
		this.quantidade = lancheIngrediente.getQuantidade();
	}

	public Long getCodigoLancheIngrediente() {
		return codigoLancheIngrediente;
	}

	public void setCodigoLancheIngrediente(Long codigoLancheIngrediente) {
		this.codigoLancheIngrediente = codigoLancheIngrediente;
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

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public LancheIngrediente build() {
		LancheIngrediente lancheIngrediente = new LancheIngrediente();
		lancheIngrediente.setCodigoLancheIngrediente(this.codigoLancheIngrediente);
		lancheIngrediente.setIngrediente(new Ingrediente(this.codigoIngrediente));
		lancheIngrediente.setQuantidade(this.getQuantidade());
		return lancheIngrediente;
	}
}
