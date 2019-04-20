package com.finch.burguer.models.enums;

public enum TipoPromocaoEnum {
	PORCENTAGEM(1, "Porcentagem"),
	QUANTIDADE(2, "Por quantidade(Ingredientes)");
	
	private int codigo;
	private String descricao;

	private TipoPromocaoEnum(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoPromocaoEnum toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		
		for(TipoPromocaoEnum x : TipoPromocaoEnum.values()) {
			if (codigo.equals(x.codigo)) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido " + codigo);
	}
}
