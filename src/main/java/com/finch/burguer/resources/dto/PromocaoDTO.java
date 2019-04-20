package com.finch.burguer.resources.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.finch.burguer.models.Ingrediente;
import com.finch.burguer.models.Promocao;
import com.finch.burguer.models.PromocaoIngrediente;
import com.finch.burguer.models.enums.TipoPromocaoEnum;

public class PromocaoDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long codigoPromocao;
	private String promocao;
	private String descricao;
	private TipoPromocaoEnum tipoPromocao;
	private BigDecimal desconto = BigDecimal.ZERO;	
	private List<PromocaoIngredienteDTO> ingredientes = new ArrayList<>();
	private List<IngredienteDTO> ingredientesExclusao = new ArrayList<>();
	
	public PromocaoDTO() {
		
	}
	
	public PromocaoDTO(Promocao promocao) {
		super();
		this.codigoPromocao = promocao.getCodigoPromocao();
		this.promocao = promocao.getPromocao();
		this.descricao = promocao.getDescricao();
		this.tipoPromocao = promocao.getTipoPromocao();
		this.desconto = promocao.getDesconto();
		
		ingredientes = promocao.getPromocaoIngredientes().stream().map(obj -> new PromocaoIngredienteDTO(obj)).collect(Collectors.toList());
		ingredientesExclusao = promocao.getIngredientesExclusao().stream().map(obj -> new IngredienteDTO(obj, 1)).collect(Collectors.toList());
	}

	public Long getCodigoPromocao() {
		return codigoPromocao;
	}

	public void setCodigoPromocao(Long codigoPromocao) {
		this.codigoPromocao = codigoPromocao;
	}

	public String getPromocao() {
		return promocao;
	}

	public void setPromocao(String promocao) {
		this.promocao = promocao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoPromocaoEnum getTipoPromocao() {
		return tipoPromocao;
	}

	public void setTipoPromocao(TipoPromocaoEnum tipoPromocao) {
		this.tipoPromocao = tipoPromocao;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public List<PromocaoIngredienteDTO> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<PromocaoIngredienteDTO> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public List<IngredienteDTO> getIngredientesExclusao() {
		return ingredientesExclusao;
	}

	public void setIngredientesExclusao(List<IngredienteDTO> ingredientesExclusao) {
		this.ingredientesExclusao = ingredientesExclusao;
	}
	
	public Promocao build() {
		Promocao promocao = new Promocao();
		promocao.setCodigoPromocao(this.codigoPromocao);
		promocao.setPromocao(this.promocao);
		promocao.setDescricao(this.descricao);
		promocao.setTipoPromocao(this.tipoPromocao);
		promocao.setDesconto(this.desconto);
		
		for(PromocaoIngredienteDTO promocaoIng: ingredientes) {
			promocao.getPromocaoIngredientes().add(new PromocaoIngrediente(promocao, new Ingrediente(promocaoIng.getCodigoIngrediente()), promocaoIng.getQuantidadeLevar(), promocaoIng.getQuantidadePagar()));
		}
		
		for(IngredienteDTO promocaoIngExclusao: ingredientesExclusao) {
			promocao.getIngredientesExclusao().add(new Ingrediente(promocaoIngExclusao.getCodigoIngrediente()));
		}
		
		return promocao;
	}
}
