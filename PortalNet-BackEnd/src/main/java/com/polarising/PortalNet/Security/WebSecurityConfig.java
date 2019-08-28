package com.polarising.PortalNet.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.polarising.PortalNet.Repository.ClientRepository;
import com.polarising.PortalNet.Repository.WorkersRepository;
import com.polarising.PortalNet.jwt.JwtAuthEntryPoint;
import com.polarising.PortalNet.jwt.JwtAuthFilter;

@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	WorkersRepository workersRepository;
	
	@Autowired
	private UserDetailsService
	userDetailsService;
	
	@Autowired
	private JwtAuthEntryPoint jwtAuthEntryPoint;
	
	@Bean
	public PasswordEncoder PasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public JwtAuthFilter authenticationJwtFilter()
	{
		return new JwtAuthFilter();
	}
	
	@Bean
	public AuthenticationProvider authProvider()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		
		//Inject out UserDetailsService
		provider.setUserDetailsService(userDetailsService);
		
		//Inject a Password Encoder
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		
		return provider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
	throws Exception {
		
		//Inject our authentication provider, created previously
		auth.authenticationProvider(authProvider());

	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		//Exposing a bean to be used by the application
	    return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http)  //Here we configure user access.
	throws Exception
	{
		http
			.httpBasic()
			.and()
			//We disable some configurations
			.cors().and().csrf().disable()
			.authorizeRequests().antMatchers("/home").permitAll()
			.antMatchers("/registration/**").permitAll()
			.and()
			.authorizeRequests()
			.antMatchers("/client/**").access("hasAuthority('CLIENT') or hasAuthority('EMPLOYEE') or hasAuthority('ADMIN')")
			.antMatchers("/clientsTable", "/administrator", "/employeesTable").access("hasAuthority('EMPLOYEE') or hasAuthority('ADMIN')")
			.antMatchers(HttpMethod.GET, "/servicesTable").access("hasAuthority('EMPLOYEE') or hasAuthority('ADMIN')")
			.antMatchers("/**").access("hasAuthority('ADMIN')")
			.anyRequest().authenticated()
			.and()
			//We specify our own login form
			.formLogin()
			.loginProcessingUrl("/").permitAll()
			.loginPage("/home").permitAll()
			.and()
			.exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint)
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(authenticationJwtFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
