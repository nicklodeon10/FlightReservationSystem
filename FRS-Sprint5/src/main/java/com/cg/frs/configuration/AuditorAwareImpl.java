package com.cg.frs.configuration;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String>{
	/*
	 * Author Surya
	 * Created on 08/10/2019
	 * Last modified on 10/10/2019
	 */
	@Override
	public Optional<String> getCurrentAuditor(){
		return Optional.of("Mr.Ayrus");
	}

}
