package com.convergenciax.service.controlador.configuracion;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	//
	
	@Bean
	@LoadBalanced  //++jcotrado soporte a balanceo de carga
	public RestTemplate restTemplate() { 	
		return new RestTemplate();
	}
}
