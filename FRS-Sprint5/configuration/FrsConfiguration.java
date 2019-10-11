package com.cg.frs.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.domain.AuditorAware;
@Configuration
@EnableJpaAuditing(auditorAwareRef="auditorProvider")
public class FrsConfiguration {
	@Bean
	public AuditorAware<String> auditorProvider(){
		return new AuditorAwareImpl();
	}
	

}
