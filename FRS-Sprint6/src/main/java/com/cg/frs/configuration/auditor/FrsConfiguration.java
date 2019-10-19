package com.cg.frs.configuration.auditor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.domain.AuditorAware;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class FrsConfiguration {

	/*
	 * Author Devang, Surya 
	 * Created on 08/10/2019 Last modified on 10/10/2019
	 */
	@Bean
	public AuditorAware<String> auditorProvider() {
		return new AuditorAwareImpl();
	}

}
