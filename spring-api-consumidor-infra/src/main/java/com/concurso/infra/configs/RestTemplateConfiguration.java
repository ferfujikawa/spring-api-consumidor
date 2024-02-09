package com.concurso.infra.configs;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Base64;

import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    private String wsUsuario;
    private String wsSenha;

    public RestTemplateConfiguration(
        @Value("${ws.usuario}") String wsUsuario,
        @Value("${ws.senha}") String wsSenha) {
            
        this.wsUsuario = wsUsuario;
        this.wsSenha = wsSenha;
    }

    @Bean
	RestTemplate restTemplate()
        throws KeyStoreException, IOException, CertificateException,
        NoSuchAlgorithmException, UnrecoverableKeyException, KeyManagementException {
		
		HttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		
		CloseableHttpClient httpClient = HttpClients.custom()
               .setConnectionManager(connectionManager)
               .evictExpiredConnections()
               .build();

		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		requestFactory.setConnectTimeout(150000);

		return new RestTemplate(requestFactory);
    }

    @Bean
    HttpHeaders cabecalhoAutenticado() {

        String auth = this.wsUsuario + ":" + this.wsSenha;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
        HttpHeaders header = new HttpHeaders();
		
        header.setBasicAuth(new String(encodedAuth));
		return header;
    }
}
