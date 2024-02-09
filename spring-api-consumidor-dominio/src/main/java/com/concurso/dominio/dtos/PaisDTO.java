package com.concurso.dominio.dtos;

public class PaisDTO {

	private String codigoPais;
	private String nomePais;
	private Boolean brasil;
	
	public String getCodigoPais() {
		return codigoPais;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getNomePais() {
		return nomePais;
	}
	public void setNomePais(String nomePais) {
		this.nomePais = nomePais;
	}
	public Boolean getBrasil() {
		return brasil;
	}
	public void setBrasil(Boolean brasil) {
		this.brasil = brasil;
	}
}

