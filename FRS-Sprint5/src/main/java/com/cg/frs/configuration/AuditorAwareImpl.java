/**
 * 
 */
package com.cg.frs.configuration;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

/**
 * @author DEVANG
 *
 */
public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of("DEVANG");
	}

}
