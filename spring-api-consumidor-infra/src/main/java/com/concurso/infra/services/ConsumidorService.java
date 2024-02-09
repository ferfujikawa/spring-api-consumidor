package com.concurso.infra.services;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.concurso.dominio.dtos.CidadeDTO;
import com.concurso.dominio.services.IConsumidorService;

@Service
public class ConsumidorService implements IConsumidorService {

    @Value("${ws.url}")
	private String wsUrl;
	
	@Value("${ws.usuario}")
	private String wsUsuario;
	
	@Value("${ws.senha}")
	private String wsSenha;

    @Value("${ws.endpoints.listarcidades}")
    private String wsEndpointListarCidade;

    private RestTemplate restTemplate;

    public ConsumidorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<CidadeDTO> listarCidades(String pesquisa) {
        
        Map<String, String> params = Collections.singletonMap("pesquisa", pesquisa);
		
		ResponseEntity<List<CidadeDTO>> response = restTemplate.getForEntity(
            wsUrl + wsEndpointListarCidade + "?query={pesquisa}",
            null,
            new ParameterizedTypeReference<List<CidadeDTO>>() {},
            params);
		
		return response.getBody();
    }
}
