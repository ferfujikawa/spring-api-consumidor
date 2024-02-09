package com.concurso.web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.concurso.dominio.dtos.PaisDTO;
import com.concurso.dominio.services.IConsumidorService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("paises")
public class PaisController {

    private IConsumidorService consumidorService;

    public PaisController(IConsumidorService consumidorService) {
		this.consumidorService = consumidorService;
	}

	@GetMapping
	public ResponseEntity<List<PaisDTO>> listarPaises(@RequestParam(name =  "pesquisa", defaultValue = "") String query) {
		
		List<PaisDTO> paises = consumidorService.listarPaises(query);

		return ResponseEntity.ok(paises);
	}

}
