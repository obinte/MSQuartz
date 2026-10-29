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
 * Libera las ventas que quedaron en proceso de autorización SRI (envío interrumpido) y cuya fecha ya pasó,
 * para que el usuario cambie la fecha del comprobante y vuelva a enviarlo.
 */
@Component
@DisallowConcurrentExecution
public class LiberarVentasEnProcesoAutorizacionJob implements Job {

    Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private ApiService bs;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("JOB CONTROLLER MSQUARTZ - LIBERAR VENTAS EN PROCESO DE AUTORIZACION ** {} ** encendido @ {}", context.getJobDetail().getKey().getName(), context.getFireTime());
        try {
            bs.liberarVentasEnProcesoAutorizacionQuartz();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(LiberarVentasEnProcesoAutorizacionJob.class.getName()).log(Level.SEVERE, null, ex);
        }
        log.info("JOB CONTROLLER MSQUARTZ - LA SIGUIENTE LIBERACION DE VENTAS EN PROCESO se ejecutará @ {}", context.getNextFireTime());
    }

}
