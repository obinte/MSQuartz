/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acosux.QuartzMS.schedule;

import org.quartz.JobDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

/**
 *
 * @author mario
 */
@Configuration
public class QuartzSchedulerJobs {

    @Value("${quartz.croncomprobantes}")
    private String CRON_FACTURAS;
    @Value("${quartz.cronretenciones}")
    private String CRON_RETENCIONES;
    @Value("${quartz.cronemailcomprobantes}")
    private String CRON_EMAILS;
    @Value("${quartz.croncompras}")
    private String CRON_COMPRAS;
    @Value("${quartz.cronanularoc}")
    private String CRON_ANULAR_OC;
    Logger log = LoggerFactory.getLogger(getClass());

    /**
     *
     * @return a job of a specifics FACTURAS ELECTRONICAS
     */
    @Bean(name = "envioFacturasElectronicas")
    public JobDetailFactoryBean jobEnvioFacturasElectronicas() {
        return QuartzConfig.createJobDetail(EnvioFacturasElectronicasJob.class, "jobEnvioFacturasElectronicas");
    }

    @Bean(name = "envioFacturasElectronicasTrigger")
    public CronTriggerFactoryBean triggerEnvioFacturasElectronicas(@Qualifier("envioFacturasElectronicas") JobDetail jobDetail) {
        return QuartzConfig.createCronTrigger(jobDetail, CRON_FACTURAS, "triggerEnvioFacturasElectronicas");
    }

    /**
     *
     * @return a job of a specifics RETENCIONES ELECTRONICAS
     */
    @Bean(name = "envioRetencionesElectronicas")
    public JobDetailFactoryBean jobEnvioRetencionesElectronicas() {
        return QuartzConfig.createJobDetail(EnvioRetencionesElectronicasJob.class, "jobEnvioRetencionesElectronicas");
    }

    @Bean(name = "envioRetencionesElectronicasTrigger")
    public CronTriggerFactoryBean triggerEnvioRetencionesElectronicas(@Qualifier("envioRetencionesElectronicas") JobDetail jobDetail) {
        return QuartzConfig.createCronTrigger(jobDetail, CRON_RETENCIONES, "triggerEnvioRetencionesElectronicas");
    }

    /**
     *
     * @return a job of a specifics ENVIO CORREOS
     */
    @Bean(name = "envioEmailCombrobantes")
    public JobDetailFactoryBean jobEnvioEmailCombrobantes() {
        return QuartzConfig.createJobDetail(EnvioEmailComprobantesElectronicasJob.class, "jobEnvioEmailCombrobantes");
    }

    @Bean(name = "envioEmailCombrobantesTrigger")
    public CronTriggerFactoryBean triggerEnvioEmailCombrobantes(@Qualifier("envioEmailCombrobantes") JobDetail jobDetail) {
        return QuartzConfig.createCronTrigger(jobDetail, CRON_EMAILS, "triggerEnvioEmailCombrobantes");
    }

    /**
     *
     * @return a job of a specifics COMPRAS PROGRAMADAS
     */
    @Bean(name = "comprasProgramadas")
    public JobDetailFactoryBean jobComprasProgramadas() {
        return QuartzConfig.createJobDetail(ComprasProgramadasJob.class, "jobComprasProgramadas");
    }

    @Bean(name = "comprasProgramadasTrigger")
    public CronTriggerFactoryBean triggerComprasProgramadas(@Qualifier("comprasProgramadas") JobDetail jobDetail) {
        return QuartzConfig.createCronTrigger(jobDetail, CRON_COMPRAS, "triggerComprasProgramadas");
    }

    /**
     *
     * @return a job of a specifics ANULAR ORDENES DE COMPRA
     */
    @Bean(name = "anularOrdenesDeCompra")
    public JobDetailFactoryBean jobAnularOrdenesDeCompra() {
        return QuartzConfig.createJobDetail(AnularOrdenesDeCompraJob.class, "jobAnularOrdenesDeCompra");
    }

    @Bean(name = "anularOrdenesDeCompraTrigger")
    public CronTriggerFactoryBean triggerAnularOrdenesDeCompra(@Qualifier("anularOrdenesDeCompra") JobDetail jobDetail) {
        return QuartzConfig.createCronTrigger(jobDetail, CRON_ANULAR_OC, "triggerAnularOrdenesDeCompra");
    }

}
