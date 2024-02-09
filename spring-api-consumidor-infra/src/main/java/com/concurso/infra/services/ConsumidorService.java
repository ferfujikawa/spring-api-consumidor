package com.concurso.infra.services;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.concurso.dominio.dtos.CidadeDTO;
import com.concurso.dominio.dtos.PaisDTO;
import com.concurso.dominio.services.IConsumidorService;

@Service
public class ConsumidorService implements IConsumidorService {

    private String wsUrl;
	
	private String wsUsuario;
	
	private String wsSenha;
    
    private String wsEndpointListarCidade;

    private String wsEndpointListarPaises;

    private RestTemplate restTemplate;

    public ConsumidorService(
        @Value("${ws.url}") String wsUrl,
        @Value("${ws.usuario}") String wsUsuario,
        @Value("${ws.senha}") String wsSenha,
        @Value("${ws.endpoints.listarcidades}") String wsEndpointListarCidade,
        @Value("${ws.endpoints.listarpaises}") String wsEndpointListarPaises,
        RestTemplate restTemplate) {
        this.wsUrl = wsUrl;
        this.wsUsuario = wsUsuario;
        this.wsSenha = wsSenha;
        this.wsEndpointListarCidade = wsEndpointListarCidade;
        this.wsEndpointListarPaises = wsEndpointListarPaises;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<CidadeDTO> listarCidades(String pesquisa) {
        
        Map<String, String> params = Collections.singletonMap("pesquisa", pesquisa);
		
		ResponseEntity<CidadeDTO[]> response = restTemplate.getForEntity(
            wsUrl + wsEndpointListarCidade + "?query={pesquisa}",
            CidadeDTO[].class,
            params);
            
		return Arrays.asList(response.getBody());
    }

    @Override
    public List<PaisDTO> listarPaises(String pesquisa) {
        
        Map<String, String> params = Collections.singletonMap("pesquisa", pesquisa);
		
		ResponseEntity<PaisDTO[]> response = restTemplate.getForEntity(
				wsUrl + wsEndpointListarPaises + "?query={pesquisa}",
				PaisDTO[].class,
				params);
		
		return Arrays.asList(response.getBody());
    }
}
