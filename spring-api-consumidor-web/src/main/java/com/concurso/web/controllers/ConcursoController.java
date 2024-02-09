package com.concurso.web.controllers;

import javax.servlet.http.HttpServletResponse;
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
import com.concurso.dominio.dtos.ObtencaoFichaConcursoDTO;
import com.concurso.dominio.services.IConcursoService;
import com.concurso.dominio.services.IConsumidorService;

@Controller
@RequestMapping("concursos")
public class ConcursoController extends BaseController {
    
    private IConcursoService concursoService;
    private IConsumidorService consumidorService;

    public ConcursoController(
        IConcursoService concursoService,
        IConsumidorService consumidorService) {
        this.concursoService = concursoService;
        this.consumidorService = consumidorService;
    }

    @GetMapping("novo")
    public String exibirFormularioCadastroConcurso(@ModelAttribute("concurso") CadastroConcursoDTO concursoDTO) {
        return "concursos/novo";
    }

    @GetMapping("confirmacao-cadastro")
    public String getMethodName(@ModelAttribute("concursoCadastrado") ConcursoDTO concursoCadastrado) {
        return "concursos/confirmacao-cadastro";
    }

    @GetMapping("inscricoes")
	public String exibirFormularioObterFicha(@ModelAttribute("obtencaoFicha") ObtencaoFichaConcursoDTO dto) {
		return "concursos/inscricoes";
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

    @PostMapping("inscricoes")
	public String obterFichaDeIncricao(
			@ModelAttribute("obtencaoFicha") @Valid ObtencaoFichaConcursoDTO dto,
			BindingResult bindingResult,
			HttpServletResponse response) {
		
		if (bindingResult.hasErrors()) {
			return exibirFormularioObterFicha(dto);
		}
		
		byte[] fichaBytes = consumidorService.obterFichaConcurso(dto.getIdInscricao());
		
		String nomeArquivo = "fichaConcurso" + String.format("%03d", dto.getIdInscricao()) + ".pdf";
		
		if (!this.anexarPdfEmResponse(nomeArquivo, response, fichaBytes)) {
			bindingResult.rejectValue("inscricaoId", "obtencaoFicha", "Erro ao baixar ficha");
			return exibirFormularioObterFicha(dto);
		}
		
		return null;
	}
}
