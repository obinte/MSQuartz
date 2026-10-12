package com.acosux.QuartzMS.schedule;

import com.acosux.QuartzMS.service.ApiService;
import java.util.logging.Level;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * AR-137: una vez al dia pide al servidor el correo de caducidad de la firma electronica.
 */
public class EnviarAvisoCaducidadFirmaJob implements Job {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApiService bs;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("JOB CONTROLLER MSQUARTZ - AVISO CADUCIDAD FIRMA ** {} ** encendido @ {}", context.getJobDetail().getKey().getName(), context.getFireTime());
        try {
            bs.enviarAvisoCaducidadFirmaQuartz();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(EnviarAvisoCaducidadFirmaJob.class.getName()).log(Level.SEVERE, null, ex);
        }
        log.info("JOB CONTROLLER MSQUARTZ - EL PROXIMO AVISO DE CADUCIDAD DE FIRMA se ejecutará @ {}", context.getNextFireTime());
    }

}
