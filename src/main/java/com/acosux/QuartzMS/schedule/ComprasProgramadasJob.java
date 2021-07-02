/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acosux.QuartzMS.schedule;

import com.acosux.QuartzMS.service.ApiService;
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
public class ComprasProgramadasJob implements Job {

    Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private ApiService bs;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("COMPRAS PROGRAMADAS ** {} ** encendido @ {}", context.getJobDetail().getKey().getName(), context.getFireTime());
        bs.enviarComprasProgramadasQuartz();
        log.info("EL SIGUIENTE REGISTRO DE COMPRAS PROGRAMADAS se ejecutará @ {}", context.getNextFireTime());
    }

}
