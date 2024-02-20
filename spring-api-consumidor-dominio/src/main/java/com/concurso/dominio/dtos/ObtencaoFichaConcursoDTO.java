package com.concurso.dominio.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ObtencaoFichaConcursoDTO {

    @NotNull(message = "Informe um número de inscrição")
	@Min(value = 1, message = "Número de inscrição deve ser maior que 0")
	private Long idInscricao;

	public Long getIdInscricao() {
		return idInscricao;
	}

	public void setIdInscricao(Long idInscricao) {
		this.idInscricao = idInscricao;
	}
}
