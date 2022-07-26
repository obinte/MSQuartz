/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acosux.QuartzMS.schedule;

import com.acosux.QuartzMS.service.ApiService;
import java.util.logging.Level;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author mario
 */
@Component
@DisallowConcurrentExecution
public class EnvioRetencionesElectronicasJob implements Job {

    Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private ApiService bs;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("ENVIO RETENCIONES ** {} ** encendido @ {}", context.getJobDetail().getKey().getName(), context.getFireTime());
        try {
            bs.enviarRetencionesElectronicasQuartz();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(EnvioRetencionesElectronicasJob.class.getName()).log(Level.SEVERE, null, ex);
        }
        log.info("EL SIGUIENTE ENVIO DE RETENCIONES se ejecutará @ {}", context.getNextFireTime());
    }

}
