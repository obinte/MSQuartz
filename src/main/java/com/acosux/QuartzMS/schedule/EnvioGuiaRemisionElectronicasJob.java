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
 * @author gramirez
 */
@Component
@DisallowConcurrentExecution
public class EnvioGuiaRemisionElectronicasJob implements Job {

    Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private ApiService bs;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("JOB CONTROLLER MSQUARTZ - GUIA REMISION ELECTRONICAS ** {} ** encendido @ {}", context.getJobDetail().getKey().getName(), context.getFireTime());
        try {
            bs.enviarGuiaRemisionElectronicasQuartz();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(EnvioGuiaRemisionElectronicasJob.class.getName()).log(Level.SEVERE, null, ex);
        }
        log.info("JOB CONTROLLER MSQUARTZ - EL SIGUIENTE ENVIO DE GUIAS REMISION se ejecutará @ {}", context.getNextFireTime());
    }

}
