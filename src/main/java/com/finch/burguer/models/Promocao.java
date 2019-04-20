package com.finch.burguer.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import com.finch.burguer.models.enums.TipoPromocaoEnum;

@Entity
public class Promocao implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
    @SequenceGenerator(name = "seq_promocao", sequenceName = "seq_promocao", allocationSize = 1)
    @GeneratedValue(generator = "seq_promocao", strategy = GenerationType.AUTO)	
	private Long codigoPromocao;
	
	@NotNull
	private String promocao;
	private String descricao;
	
	@NotNull
	@Enumerated
	private TipoPromocaoEnum tipoPromocao;
	
	private BigDecimal desconto = BigDecimal.ZERO;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="promocao", cascade=CascadeType.ALL)
	private List<PromocaoIngrediente> promocaoIngredientes = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name="PROMOCAO_INGREDIENTE_EXCLUSAO", 
		joinColumns = @JoinColumn(name="CODIGOPROMOCAO"),
		inverseJoinColumns = @JoinColumn(name="CODIGOINGREDIENTE")
	)
	private List<Ingrediente> ingredientesExclusao = new ArrayList<>();
	
	public Promocao() {
		
	}

	public Promocao(Long codigoPromocao) {
		super();
		this.codigoPromocao = codigoPromocao;
	}
	
	public Promocao(String promocao, String descricao, TipoPromocaoEnum tipoPromocao, BigDecimal desconto) {
		super();
		this.promocao = promocao;
		this.descricao = descricao;
		this.tipoPromocao = tipoPromocao;
		this.desconto = desconto;
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

	public List<PromocaoIngrediente> getPromocaoIngredientes() {
		return promocaoIngredientes;
	}

	public void setPromocaoIngredientes(List<PromocaoIngrediente> promocaoIngredientes) {
		this.promocaoIngredientes = promocaoIngredientes;
	}

	public List<Ingrediente> getIngredientesExclusao() {
		return ingredientesExclusao;
	}

	public void setIngredientesExclusao(List<Ingrediente> ingredientesExclusao) {
		this.ingredientesExclusao = ingredientesExclusao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoPromocao == null) ? 0 : codigoPromocao.hashCode());
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
		Promocao other = (Promocao) obj;
		if (codigoPromocao == null) {
			if (other.codigoPromocao != null)
				return false;
		} else if (!codigoPromocao.equals(other.codigoPromocao))
			return false;
		return true;
	}
	
	
}
