package com.concurso.web.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.concurso.dominio.dtos.CadastroConcursoDTO;
import com.concurso.dominio.dtos.ConcursoDTO;
import com.concurso.dominio.services.IConcursoService;



@Controller
@RequestMapping("concursos")
public class ConcursoController {
    
    private IConcursoService concursoService;

    public ConcursoController(IConcursoService concursoService) {
        this.concursoService = concursoService;
    }

    @GetMapping("novo")
    public String exibirFormularioCadastroConcurso(@ModelAttribute("concurso") CadastroConcursoDTO concursoDTO) {
        return "concursos/novo";
    }

    @GetMapping("confirmacao-cadastro")
    public String getMethodName(@ModelAttribute("concursoCadastrado") ConcursoDTO concursoCadastrado) {
        return "concursos/confirmacao-cadastro";
    }

    @PostMapping
	public String cadastrarConcurso(
			final Model model,
			@ModelAttribute("concurso") @Valid CadastroConcursoDTO dto,
			BindingResult bindingResult,
            final RedirectAttributes redirectAttributes) {
		
        if (bindingResult.hasErrors()) {
			return exibirFormularioCadastroConcurso(dto);
		}
		
		ConcursoDTO concursoCadastrado = concursoService.cadastrarConcurso(dto);

        redirectAttributes.addFlashAttribute("concursoCadastrado", concursoCadastrado);
		
		return "redirect:/concursos/confirmacao-cadastro";
	}
}
