/**
 * 
 */
package com.cg.frs.configuration;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

/**
 * @author: DEVANG
 * description: Provides the current user name to the Auditor 
 * created date: 09/10/2019
 * modified: -
 */
public class AuditorAwareImpl implements AuditorAware<String> {

	/*	
	 *  Author: DEVANG
	 *  Description: Provides a String Optional for the current user.
	 *  Input: -
	 *  Output: Optional<String>
	 *  Created Date: 09/10/2019
	 *  Last Modified: -
	 */
	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of("DEVANG");
	}

}
