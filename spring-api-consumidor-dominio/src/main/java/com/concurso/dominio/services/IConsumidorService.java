package com.concurso.dominio.services;

import java.time.LocalDateTime;
import java.util.List;

import com.concurso.dominio.dtos.CidadeDTO;
import com.concurso.dominio.dtos.PaisDTO;

public interface IConsumidorService {

    List<CidadeDTO> listarCidades(String pesquisa);

    List<PaisDTO> listarPaises(String pesquisa);

    LocalDateTime obterHoraDoServidor();

    byte[] obterFichaConcurso(Long inscricaoId);
}
