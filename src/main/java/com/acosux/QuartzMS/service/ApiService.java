/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acosux.QuartzMS.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author mario
 */
@Service
public class ApiService {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${acosux.endpoint}")
    private String ENDPOINT;
    private static final long VALIDITY_TIME_MS = 10 * 24 * 60 * 60 * 1000;// 10 days Validity
    private final String secret = "mrin";
    private static final ObjectMapper om = new ObjectMapper();

    public HttpEntity<String> createTokenForUser(Map<String, Object> map) throws Exception {
        String token = Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + VALIDITY_TIME_MS))
                .setSubject("admin")
                .claim("userId", "admin")
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer" + token);
        HttpEntity<String> entity = new HttpEntity<>(objetoToJson(map), headers);
        return entity;
    }

    public static String objetoToJson(Object obj) throws JsonProcessingException {
        return om.writeValueAsString(obj);
    }

    public void enviarFacturasElectronicasQuartz() throws Exception {
        log.info("Servicio De envío de facturas iniciado...");
        try {
            Map<String, Object> map = new HashMap<>();
            restTemplate.postForObject(ENDPOINT + "/todocompuWS/quartzController/enviarFacturasElectronicasQuartz", createTokenForUser(map), void.class);
            log.info("El envío de facturas culminó exitosamente...");
        } catch (RestClientException e) {
            log.error("Error mientras enviamos facturas electronicas", e.getMessage());
        }
    }

    public void enviarRetencionesElectronicasQuartz() throws Exception {
        log.info("Servicio De envío de retenciones iniciado...");
        try {
            Map<String, Object> map = new HashMap<>();
            restTemplate.postForObject(ENDPOINT + "/todocompuWS/quartzController/enviarRetencionesElectronicasQuartz", createTokenForUser(map), void.class);
            log.info("El envío de retenciones culminó exitosamente...");
        } catch (RestClientException e) {
            log.error("Error mientras enviamos retenciones electronicas", e.getMessage());
        }
    }

    public void enviarComprasProgramadasQuartz() throws Exception {
        log.info("Servicio De compras porgramadas iniciado...");
        try {
            Map<String, Object> map = new HashMap<>();
            restTemplate.postForObject(ENDPOINT + "/todocompuWS/quartzController/enviarComprasProgramadasQuartz", createTokenForUser(map), void.class);
            log.info("El registro de compras porgramadas culminó exitosamente...");
        } catch (RestClientException e) {
            log.error("Error registramos compras programadas", e.getMessage());
        }
    }

    public void enviarEmailComprobantesQuartz() throws Exception {
        log.info("Servicio De enviar emails iniciado...");
        try {
            Map<String, Object> map = new HashMap<>();
            restTemplate.postForObject(ENDPOINT + "/todocompuWS/quartzController/enviarEmailComprobantesQuartz", createTokenForUser(map), void.class);
            log.info("El envío de emails de comprobantes culminó exitosamente...");
        } catch (RestClientException e) {
            log.error("Error enviamos emails de comprobantes", e.getMessage());
        }
    }

    public void anularOrdenesDeCompraQuartz() throws Exception {
        log.info("Servicio De anulación de órdenes de compra iniciado...");
        try {
            Map<String, Object> map = new HashMap<>();
            restTemplate.postForObject(ENDPOINT + "/todocompuWS/quartzController/anularOrdenesCompraQuartz", createTokenForUser(map), void.class);
            log.info("El proceso de anulacion de órdenes de compras culminó exitosamente...");
        } catch (RestClientException e) {
            log.error("Error anulacion de órdenes de compras", e.getMessage());
        }
    }

}

