package com.SustentaCommerce.Ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailServiceImplement userDetailService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/usuarios/logar").permitAll()
			.antMatchers("/usuarios/cadastrar").permitAll()
			.antMatchers("/**").permitAll()
			.antMatchers(HttpMethod.GET,"/produtos").permitAll()
			.antMatchers(HttpMethod.GET,"/produtos/id/{id}").permitAll()
			.antMatchers(HttpMethod.GET,"/produtos/nome/{nome}").permitAll()
			.antMatchers("/produtos").permitAll()
			.antMatchers("/produtos/id/{id}").permitAll()
			.antMatchers("/produtos/nome/{nome}").permitAll()
			.antMatchers(HttpMethod.GET,"/categorias").permitAll()
			.antMatchers(HttpMethod.GET,"/categorias/id/{id}").permitAll()
			.antMatchers(HttpMethod.GET,"/categorias/departamentoProduto/{departamentoProduto}").permitAll()
			.antMatchers("/categorias").permitAll()
			.antMatchers("/categorias/id/{id}").permitAll()
			.antMatchers("/categorias/departamentoProduto/{departamentoProduto}").permitAll()
			.antMatchers("/produtos/pesquisar-produto").permitAll()
			.antMatchers("/pesquisar-produto").permitAll()
		.anyRequest().authenticated()
		.and().httpBasic()
		.and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().cors()
		.and().csrf()
			.disable();
	}
}