package com.iceico.testportal.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.iceico.testportal.Security.MySimpleUrlAuthenticationSuccessHandler;

/**
 * @author LEKHA BHANGE
 * @version 0.1
 * 
 *          Created Date : 11/02/2020
 *
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	PersistentTokenRepository tokenRepository;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
		return new MySimpleUrlAuthenticationSuccessHandler();
	}

	@Bean("authenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

		/* http.requiresChannel().anyRequest().requiresSecure(); */

		http.authorizeRequests().antMatchers("/static/**", "/forgot/password/**", "/getImage/**", "/register/**",
				"/caste/list**", "/subcaste/list**", "/subject/list**", "/upload/file", "/download/**").permitAll();

		http.authorizeRequests().antMatchers("/anonymous*").anonymous().antMatchers("/login").permitAll()
				.antMatchers("/admin/**").access("hasRole('ADMIN')").antMatchers("/teacher/**")
				.access("hasRole('TEACHER') or hasRole('ADMIN')").antMatchers("/student/**")
				.access("hasRole('STUDENT') or hasRole('ADMIN')").anyRequest().authenticated().and().formLogin()
				.loginPage("/login").successHandler(myAuthenticationSuccessHandler()).usernameParameter("ssoId")
				.passwordParameter("password").and().rememberMe().rememberMeParameter("remember-me")
				.tokenRepository(tokenRepository).tokenValiditySeconds(86400).and().csrf().and().exceptionHandling()
				.accessDeniedPage("/accessDenied").and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
				.deleteCookies("JSESSIONID").invalidateHttpSession(true).and().sessionManagement()
				.invalidSessionUrl("/login").maximumSessions(20).and().sessionFixation().migrateSession().and().csrf()
				.disable();

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
		PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
				"remember-me", userDetailsService, tokenRepository);
		return tokenBasedservice;
	}

	@Bean
	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
		return new AuthenticationTrustResolverImpl();
	}

}
