/**
 * 
 */
package com.cg.frs.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author DEVANG
 *
 */

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class FRSConfiguration {

	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditorAwareImpl();
	}

}
