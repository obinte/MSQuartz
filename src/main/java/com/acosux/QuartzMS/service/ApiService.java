/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acosux.QuartzMS.service;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

    public void enviarFacturasElectronicasQuartz() {
        log.info("Servicio De envío de facturas iniciado...");
        try {
            Map<String, Object> map = new HashMap<>();
            restTemplate.postForObject(ENDPOINT + "/todocompuWS/quartzController/enviarFacturasElectronicasQuartz", map, void.class);
            log.info("El envío de facturas culminó exitosamente...");
        } catch (RestClientException e) {
            log.error("Error mientras enviamos facturas electronicas", e.getMessage());
        }
    }

    public void enviarRetencionesElectronicasQuartz() {
        log.info("Servicio De envío de retenciones iniciado...");
        try {
            Map<String, Object> map = new HashMap<>();
            restTemplate.postForObject(ENDPOINT + "/todocompuWS/quartzController/enviarRetencionesElectronicasQuartz", map, void.class);
            log.info("El envío de retenciones culminó exitosamente...");
        } catch (RestClientException e) {
            log.error("Error mientras enviamos retenciones electronicas", e.getMessage());
        }
    }

    public void enviarComprasProgramadasQuartz() {
        log.info("Servicio De compras porgramadas iniciado...");
        try {
            Map<String, Object> map = new HashMap<>();
            restTemplate.postForObject(ENDPOINT + "/todocompuWS/quartzController/enviarComprasProgramadasQuartz", map, void.class);
            log.info("El registro de compras porgramadas culminó exitosamente...");
        } catch (RestClientException e) {
            log.error("Error registramos compras programadas", e.getMessage());
        }
    }

    public void enviarEmailComprobantesQuartz() {
        log.info("Servicio De enviar emails iniciado...");
        try {
            Map<String, Object> map = new HashMap<>();
            restTemplate.postForObject(ENDPOINT + "/todocompuWS/quartzController/enviarComprasProgramadasQuartz", map, void.class);
            log.info("El envío de emails de comprobantes culminó exitosamente...");
        } catch (RestClientException e) {
            log.error("Error enviamos emails de comprobantes", e.getMessage());
        }
    }

}
