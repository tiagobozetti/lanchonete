package com.finch.burguer.resources.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CardapioDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<LancheDTO> lanches = new ArrayList<>();
	private List<PromocaoDTO> promocoes = new ArrayList<>();
	
	public CardapioDTO() {
		
	}

	public List<LancheDTO> getLanches() {
		return lanches;
	}

	public void setLanches(List<LancheDTO> lanches) {
		this.lanches = lanches;
	}

	public List<PromocaoDTO> getPromocoes() {
		return promocoes;
	}

	public void setPromocoes(List<PromocaoDTO> promocoes) {
		this.promocoes = promocoes;
	}
	
	
}
