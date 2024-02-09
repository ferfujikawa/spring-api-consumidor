package com.concurso.dominio.services;

import com.concurso.dominio.dtos.CadastroConcursoDTO;
import com.concurso.dominio.dtos.ConcursoDTO;

public interface IConcursoService {

    ConcursoDTO cadastrarConcurso(CadastroConcursoDTO cadastroConcursoDTO);
}
