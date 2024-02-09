package com.concurso.dominio.dtos;

import javax.validation.constraints.NotBlank;

public class CadastroConcursoDTO {
	
	@NotBlank(message = "Informe um nome para o concurso")
	private String nome;
	
	@NotBlank(message = "Informe o país onde será realizado o concurso")
	private String pais;
	
	@NotBlank(message = "Informe a cidade onde será realizado o concurso")
	private String cidade;

	public String getNome() {
		return nome;
	}

	public String getPais() {
		return pais;
	}

	public String getCidade() {
		return cidade;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
}
