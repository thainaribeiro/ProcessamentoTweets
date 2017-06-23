package com.processamentotweets.jobs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobFactory {
	
	@Autowired private KeywordJob keywordJob;

	/*
	 * Método responsável por injetar a lista de jobs diarios na classe WebBoletoJobs
	 */
	@Bean("jobsDiarios")
	public List<InterfaceJob> getJobsDiarios() {
		List<InterfaceJob> jobsDiarios = new ArrayList<>();
		jobsDiarios.add(keywordJob);
		return jobsDiarios;
	}
	
	/*
	 * Método responsável por injetar a lista de jobs continuos na classe WebBoletoJobs
	 */
	@Bean(name="jobsContinuos")
	public List<InterfaceJob> getJobsContinuos() {
		List<InterfaceJob> jobsContinuos = new ArrayList<>();
		
		return jobsContinuos;
	}
}