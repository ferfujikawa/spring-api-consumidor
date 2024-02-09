package com.concurso.infra.services;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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

    private String wsEndpointObterHoraDoServidor;

    public ConsumidorService(
        @Value("${ws.url}") String wsUrl,
        @Value("${ws.usuario}") String wsUsuario,
        @Value("${ws.senha}") String wsSenha,
        @Value("${ws.endpoints.listarcidades}") String wsEndpointListarCidade,
        @Value("${ws.endpoints.listarpaises}") String wsEndpointListarPaises,
        @Value("${ws.endpoints.obterhoradoservidor}") String wsEndpointObterHoraDoServidor,
        RestTemplate restTemplate) {
        this.wsUrl = wsUrl;
        this.wsUsuario = wsUsuario;
        this.wsSenha = wsSenha;
        this.wsEndpointListarCidade = wsEndpointListarCidade;
        this.wsEndpointListarPaises = wsEndpointListarPaises;
        this.wsEndpointObterHoraDoServidor = wsEndpointObterHoraDoServidor;
        this.restTemplate = restTemplate;
    }

    private HttpHeaders criarCabecalhoRequisicao() {
		
		String auth = wsUsuario + ":" + wsSenha;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
        HttpHeaders header = new HttpHeaders();
		
        header.setBasicAuth(new String(encodedAuth));
		return header;
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

    @Override
    public LocalDateTime obterHoraDoServidor() {
        
        HttpHeaders header = criarCabecalhoRequisicao();

        ResponseEntity<ZonedDateTime> response = restTemplate.exchange(
				wsUrl + wsEndpointObterHoraDoServidor,
				HttpMethod.GET,
				new HttpEntity<String>(header),
				ZonedDateTime.class);
		
		ZonedDateTime dataHoraComTimeZone = response.getBody().withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));
		return dataHoraComTimeZone.toLocalDateTime();
    }
}
