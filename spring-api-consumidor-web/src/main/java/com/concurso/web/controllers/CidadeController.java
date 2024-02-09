package com.concurso.web.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import com.concurso.dominio.dtos.CidadeDTO;
import com.concurso.dominio.services.IConsumidorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("cidades")
public class CidadeController {

    private IConsumidorService consumidorService;

    public CidadeController(IConsumidorService consumidorService) {
        this.consumidorService = consumidorService;
    }

    @GetMapping
    public ResponseEntity<List<CidadeDTO>> listarCidades(@RequestParam(name =  "pesquisa", defaultValue = "") String query) {
        
        List<CidadeDTO> cidades = consumidorService.listarCidades(query);
		
		return ResponseEntity.ok(cidades);
    }
    
}
