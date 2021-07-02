package com.acosux.QuartzMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuartzMsApplication {

    //WORKAROUND. TO BE REMOVED. Avoid problems with wrong CN of  self signed certificate
//    static {
//        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier((String hostname, javax.net.ssl.SSLSession sslSession) -> hostname.equals("localhost"));
//    }

    public static void main(String[] args) {
        SpringApplication.run(QuartzMsApplication.class, args);
    }

}
