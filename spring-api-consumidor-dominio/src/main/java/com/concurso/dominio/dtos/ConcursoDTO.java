package com.concurso.dominio.dtos;

import java.time.LocalDateTime;

public class ConcursoDTO {

	private String nome;
	
	private String pais;
	
	private String cidade;
	
	private LocalDateTime dataCadastro;

	public ConcursoDTO(String nome, String pais, String cidade, LocalDateTime dataCadastro) {
		this.nome = nome;
		this.pais = pais;
		this.cidade = cidade;
		this.dataCadastro = dataCadastro;
	}

	public String getNome() {
		return nome;
	}

	public String getPais() {
		return pais;
	}

	public String getCidade() {
		return cidade;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
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

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}
