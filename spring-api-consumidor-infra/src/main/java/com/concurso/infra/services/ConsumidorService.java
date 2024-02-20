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

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.concurso.dominio.dtos.CidadeDTO;
import com.concurso.dominio.dtos.PaisDTO;
import com.concurso.dominio.services.IConsumidorService;
import com.concurso.infra.configs.WebServiceConfiguration;

@Service
public class ConsumidorService implements IConsumidorService {

    private WebServiceConfiguration wsConfig;

    private RestTemplate restTemplate;

    public ConsumidorService(RestTemplate restTemplate, WebServiceConfiguration wsConfig) {
        
        this.restTemplate = restTemplate;
        this.wsConfig = wsConfig;
    }

    private HttpHeaders criarCabecalhoRequisicao() {
		
		String auth = wsConfig.getUsuario() + ":" + wsConfig.getSenha();
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
        HttpHeaders header = new HttpHeaders();
		
        header.setBasicAuth(new String(encodedAuth));
		return header;
	}

    @Override
    public List<CidadeDTO> listarCidades(String pesquisa) {
        
        Map<String, String> params = Collections.singletonMap("pesquisa", pesquisa);
		
		ResponseEntity<CidadeDTO[]> response = restTemplate.getForEntity(
            wsConfig.getEndpointListarCidade() + "?query={pesquisa}",
            CidadeDTO[].class,
            params);
            
		return Arrays.asList(response.getBody());
    }

    @Override
    public List<PaisDTO> listarPaises(String pesquisa) {
        
        Map<String, String> params = Collections.singletonMap("pesquisa", pesquisa);
		
		ResponseEntity<PaisDTO[]> response = restTemplate.getForEntity(
				wsConfig.getEndpointListarPaises() + "?query={pesquisa}",
				PaisDTO[].class,
				params);
		
		return Arrays.asList(response.getBody());
    }

    @Override
    public LocalDateTime obterHoraDoServidor() {
        
        HttpHeaders header = criarCabecalhoRequisicao();

        ResponseEntity<ZonedDateTime> response = restTemplate.exchange(
				wsConfig.getEndpointObterHoraDoServidor(),
				HttpMethod.GET,
				new HttpEntity<String>(header),
				ZonedDateTime.class);
		
		ZonedDateTime dataHoraComTimeZone = response.getBody().withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));
		return dataHoraComTimeZone.toLocalDateTime();
    }

    @Override
    public byte[] obterFichaConcurso(Long inscricaoId) {
        
        HttpHeaders header = criarCabecalhoRequisicao();
        
        header.setContentType(MediaType.APPLICATION_JSON);
        header.setAccept(Collections.singletonList(MediaType.APPLICATION_OCTET_STREAM));
		
        ResponseEntity<byte[]> response = restTemplate.exchange(
				wsConfig.getEndpointObterFichaConcurso(),
				HttpMethod.POST,
				new HttpEntity<Long>(inscricaoId, header),
				byte[].class);
		
		return response.getBody();
    }
}
