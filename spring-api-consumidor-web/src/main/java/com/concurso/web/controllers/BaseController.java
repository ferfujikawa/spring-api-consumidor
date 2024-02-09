package com.concurso.web.controllers;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;

public abstract class BaseController {

    protected Boolean anexarPdfEmResponse(
			String nomeArquivo,
			HttpServletResponse response,
			byte[] conteudoEmBytes) {
		
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
	    response.setHeader("Content-disposition", "attachment; filename=" + nomeArquivo);
	    
	    try {
	    	OutputStream output = response.getOutputStream();
			output.write(conteudoEmBytes);
			output.flush();
			output.close();
			
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (IOException e) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			return false;
		}
	    
	    return true;
	}
}
