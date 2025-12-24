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
 * @author joel
 */
@Component
@DisallowConcurrentExecution
public class InactivarActivoFijoJob implements Job {

    Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private ApiService bs;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("JOB CONTROLLER MSQUARTZ - INACTIVAR ACTIVO FIJO ** {} ** encendido @ {}", context.getJobDetail().getKey().getName(), context.getFireTime());
        try {
            bs.inactivarActivoFijoQuartz();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(InactivarActivoFijoJob.class.getName()).log(Level.SEVERE, null, ex);
        }
        log.info("JOB CONTROLLER MSQUARTZ - LA SIGUIENTE INACTIVACION DE ACTIVO FIJO se ejecutará @ {}", context.getNextFireTime());
    }

}
