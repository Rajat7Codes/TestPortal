package com.iceico.testportal.audit.Auditable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author jack
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditConfiguration {

	@Bean(name = "auditorProvider")
	public AuditorAware<String> auditorProvider() {
		return new AuditorAwareImpl();
	}

}
