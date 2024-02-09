package com.concurso.infra.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.concurso.dominio.dtos.CadastroConcursoDTO;
import com.concurso.dominio.dtos.ConcursoDTO;
import com.concurso.dominio.services.IConcursoService;

@Service
public class ConcursoService implements IConcursoService {

    @Override
    public ConcursoDTO cadastrarConcurso(CadastroConcursoDTO cadastroConcursoDTO) {
        
        LocalDateTime dataHoraServidor = LocalDateTime.now();
		
		return new ConcursoDTO(
            cadastroConcursoDTO.getNome(),
            cadastroConcursoDTO.getPais(),
            cadastroConcursoDTO.getCidade(),
            dataHoraServidor);
    }

}
