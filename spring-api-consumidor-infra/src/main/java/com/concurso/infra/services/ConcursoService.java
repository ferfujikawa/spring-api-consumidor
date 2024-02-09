package com.concurso.infra.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.concurso.dominio.dtos.CadastroConcursoDTO;
import com.concurso.dominio.dtos.ConcursoDTO;
import com.concurso.dominio.services.IConcursoService;
import com.concurso.dominio.services.IConsumidorService;

@Service
public class ConcursoService implements IConcursoService {

    private IConsumidorService consumidorService;

    public ConcursoService(IConsumidorService consumidorService) {
        this.consumidorService = consumidorService;
    }

    @Override
    public ConcursoDTO cadastrarConcurso(CadastroConcursoDTO cadastroConcursoDTO) {
        
        LocalDateTime dataHoraServidor = consumidorService.obterHoraDoServidor();
		
		return new ConcursoDTO(
            cadastroConcursoDTO.getNome(),
            cadastroConcursoDTO.getPais(),
            cadastroConcursoDTO.getCidade(),
            dataHoraServidor);
    }

}
