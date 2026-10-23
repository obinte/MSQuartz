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

@Component
@DisallowConcurrentExecution
public class NotificarPasswordProximoVencimientoJob implements Job {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApiService bs;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("JOB CONTROLLER MSQUARTZ - AVISO DE VENCIMIENTO DE CONTRASEÑA ** {} ** encendido @ {}", context.getJobDetail().getKey().getName(), context.getFireTime());
        try {
            bs.notificarPasswordProximoVencimientoQuartz();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(NotificarPasswordProximoVencimientoJob.class.getName()).log(Level.SEVERE, null, ex);
        }
        log.info("JOB CONTROLLER MSQUARTZ - EL PROXIMO AVISO DE VENCIMIENTO DE CONTRASEÑA se ejecutará @ {}", context.getNextFireTime());
    }

}
