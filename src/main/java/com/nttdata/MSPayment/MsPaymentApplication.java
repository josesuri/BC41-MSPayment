package com.nttdata.MSPayment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsPaymentApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(MsPaymentApplication.class, args);
	}
	private static final Logger logger = LogManager.getLogger(MsPaymentApplication.class);
	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.debug("Debug de la aplicación");
		logger.info("Info Log");
		logger.warn("Warning");
		logger.error("Error");
		logger.fatal("Fatal Error");
	}
}
