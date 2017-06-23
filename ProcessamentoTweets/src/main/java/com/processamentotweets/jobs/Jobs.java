package com.processamentotweets.jobs;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

 @Component
 public class Jobs {
 	private final Logger log = LoggerFactory.getLogger(Jobs.class);
 	
 	
 	@Autowired
 	@Qualifier("jobsDiarios")
 	private List<InterfaceJob> jobsDiarios = new ArrayList<>();
 	
 	@Autowired
 	@Qualifier("jobsContinuos")
 	private List<InterfaceJob> jobsContinuos = new ArrayList<>();
 	
 	@Scheduled(cron="0 1 0 * * *")
 	public void runJobsDiarios() {
 		try {
 			log.info("Execução dos Jobs Diários Iniciada!");
 			for(InterfaceJob job : jobsDiarios) {
 				try {
 					log.info("Executando o job: {} - ({})", job.getJobName(), job.getClass().getCanonicalName());
 					job.run();
 				} catch(Exception e) {
 					log.error(e.getMessage(), e);
 				}
 			}
 		} catch (Exception e) {
 			log.error(e.getMessage(), e);
 		}
 	}
 	
 	@Scheduled(cron="0 */5 * * * *")
 	public void runJobsContinuos() {
 		try {
 			log.info("Execução dos Jobs Contínuos Iniciada!");
 			for(InterfaceJob job : jobsContinuos) {
 				try {
 					log.info("Executando o job: {} - ({})", job.getJobName(), job.getClass().getCanonicalName());
 					job.run();
 				} catch(Exception e) {
 					log.error(e.getMessage(), e);
 				}
 			}
 		} catch (Exception e) {
 			log.error(e.getMessage(), e);
 		}
 	}
 	
 }