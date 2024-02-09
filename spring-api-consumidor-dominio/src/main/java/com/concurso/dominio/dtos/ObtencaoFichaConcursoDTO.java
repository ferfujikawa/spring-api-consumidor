package com.concurso.dominio.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ObtencaoFichaConcursoDTO {

    @NotNull(message = "Informe o id da inscrição para ")
	@Min(value = 1, message = "Id deve ser maior ou igual a 1")
	private Long idInscricao;

	public Long getIdInscricao() {
		return idInscricao;
	}

	public void setIdInscricao(Long idInscricao) {
		this.idInscricao = idInscricao;
	}
}
