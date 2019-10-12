/**
 * 
 */
package com.cg.frs.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author: DEVANG
 * description: Configuration class for JPA Auditing
 * created date: 10/10/2019
 * modified: -
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class FRSConfiguration {

	/*	
	 *  Author: DEVANG
	 *  Description: Configures the JPA AUditor
	 *  Input: -
	 *  Output: AuditorAware object.
	 *  Created Date: 10/10/2019
	 *  Last Modified: -
	 */
	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditorAwareImpl();
	}

}
