package com.finch.burguer.resources.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.finch.burguer.models.Lanche;
import com.finch.burguer.models.LancheIngrediente;

public class LancheDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long codigoLanche;
	private String lanche;
	private BigDecimal valor = BigDecimal.ZERO;
	private List<LancheIngredienteDTO> ingredientes = new ArrayList<>();
	
	public LancheDTO() {
		
	}
	
	public LancheDTO(Lanche lanche) {
		super();
		this.codigoLanche = lanche.getCodigoLanche();
		this.lanche = lanche.getLanche();
		
		ingredientes = lanche.getLancheIngredientes().stream().map(obj -> new LancheIngredienteDTO(obj)).collect(Collectors.toList());
		
		this.valor = lanche.getLancheIngredientes().stream()
				.map(d-> d.getIngrediente().getValor().multiply(BigDecimal.valueOf(d.getQuantidade())))
				.reduce(BigDecimal.ZERO,BigDecimal::add);
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

	public List<LancheIngredienteDTO> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<LancheIngredienteDTO> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public Lanche build() {
		Lanche lanche = new Lanche(this.lanche);
		for(LancheIngredienteDTO ing: this.getIngredientes()) {
			lanche.getLancheIngredientes().add(new LancheIngrediente(lanche, ing));
		}		
		return lanche;
	}
}
