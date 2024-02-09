package com.concurso.dominio.dtos;

public class CidadeDTO {

    private Long codigoMunicipio;
	private String nomeMunicipio;
	private String siglaUF;
	
	public Long getCodigoMunicipio() {
		return codigoMunicipio;
	}
	public void setCodigoMunicipio(Long codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}
	public String getNomeMunicipio() {
		return nomeMunicipio;
	}
	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}
	public String getSiglaUF() {
		return siglaUF;
	}
	public void setSiglaUF(String siglaUF) {
		this.siglaUF = siglaUF;
	}
}
