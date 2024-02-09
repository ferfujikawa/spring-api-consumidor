package com.concurso.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("concursos")
public class ConcursoController {
    
    @GetMapping("/novo")
    public String exibirFormularioCadastroConcurso() {
        return "concursos/novo";
    }
}
