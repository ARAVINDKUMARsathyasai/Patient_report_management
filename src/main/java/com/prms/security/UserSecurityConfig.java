package com.prms.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Configuration class for user-specific security settings.
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
 */
@Configuration
@EnableWebSecurity
@Order(2)
public class UserSecurityConfig {
	/**
	 * Provides the PasswordEncoder bean for encoding user passwords.
	 *
	 * @return The PasswordEncoder bean instance.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * Configures the security filter chain for user-related URLs and permissions.
	 *
	 * @param http The HttpSecurity object to configure.
	 * @return The SecurityFilterChain instance for user-related URLs.
	 * @throws Exception If an error occurs during configuration.
	 */
	@Bean
	public SecurityFilterChain homePageSecurityFilterChain(HttpSecurity http) throws Exception {
		
		return http
				   .cors()
				   .and()
				   .csrf().disable()
				   .authorizeHttpRequests()
				   .requestMatchers("/user/**").hasAuthority("USER")
				   .and()
				   .formLogin()
				   .loginPage("/userLogin")
				   .defaultSuccessUrl("/user/index")
				   .and()
				   .authorizeHttpRequests()
				   .requestMatchers("/**")
				   .permitAll()
				   .and()
				   .formLogin()
				   .and()
				   .build();
	}
}
