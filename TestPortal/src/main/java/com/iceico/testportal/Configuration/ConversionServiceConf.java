/**
 * 
 */
package com.iceico.testportal.Configuration;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import com.iceico.testportal.Converter.RoleToUserProfileConverter;


/**
 * @author LEKHA BHANGE
 * @version 0.1
 * 
 *          Created Date : 11/02/2020
 *
 */
@Configuration
public class ConversionServiceConf {

	@Bean
	public ConversionService getConversionService() {
		ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
		bean.setConverters(getConverters());
		bean.afterPropertiesSet();
		ConversionService object = bean.getObject();
		return object;
	}

	@SuppressWarnings("rawtypes")
	private Set<Converter> getConverters() {
		Set<Converter> converters = new HashSet<Converter>();
		converters.add(new RoleToUserProfileConverter());
		return converters;
	}

}
