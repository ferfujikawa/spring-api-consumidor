package com.concurso.dominio.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ObtencaoFichaConcursoDTO {

    @NotNull(message = "Informe o id da inscrição para ")
	@Min(value = 1, message = "Id deve ser entre 1 e 68")
	@Max(value = 68, message = "Id deve ser entre 1 e 68")
	private Long idInscricao;

	public Long getIdInscricao() {
		return idInscricao;
	}

	public void setIdInscricao(Long idInscricao) {
		this.idInscricao = idInscricao;
	}
}
