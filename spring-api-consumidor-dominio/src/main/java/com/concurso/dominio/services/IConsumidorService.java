package com.concurso.dominio.services;

import java.util.List;

import com.concurso.dominio.dtos.CidadeDTO;

public interface IConsumidorService {

    List<CidadeDTO> listarCidades(String pesquisa);
}
