package com.concurso.infra.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebServiceConfiguration {

    private String url;
	
	private String usuario;
	
	private String senha;
    
    private String endpointListarCidade;

    private String endpointListarPaises;

    private String endpointObterHoraDoServidor;

    private String endpointObterFichaConcurso;

    public WebServiceConfiguration(
        @Value("${ws.url}") String wsUrl,
        @Value("${ws.usuario}") String wsUsuario,
        @Value("${ws.senha}") String wsSenha,
        @Value("${ws.endpoints.listarcidades}") String wsEndpointListarCidade,
        @Value("${ws.endpoints.listarpaises}") String wsEndpointListarPaises,
        @Value("${ws.endpoints.obterhoradoservidor}") String wsEndpointObterHoraDoServidor,
        @Value("${ws.endpoints.obterfichaconcurso}") String wsEndpointObterFichaConcurso) {
        this.url = wsUrl;
        this.usuario = wsUsuario;
        this.senha = wsSenha;
        this.endpointListarCidade = wsEndpointListarCidade;
        this.endpointListarPaises = wsEndpointListarPaises;
        this.endpointObterHoraDoServidor = wsEndpointObterHoraDoServidor;
        this.endpointObterFichaConcurso = wsEndpointObterFichaConcurso;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public String getEndpointListarCidade() {
        return this.url + endpointListarCidade;
    }

    public String getEndpointListarPaises() {
        return this.url + endpointListarPaises;
    }

    public String getEndpointObterHoraDoServidor() {
        return this.url + endpointObterHoraDoServidor;
    }

    public String getEndpointObterFichaConcurso() {
        return this.url + endpointObterFichaConcurso;
    }
}
