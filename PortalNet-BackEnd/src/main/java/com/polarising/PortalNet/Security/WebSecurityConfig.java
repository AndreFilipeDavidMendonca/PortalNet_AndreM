package com.polarising.PortalNet.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.polarising.PortalNet.model.Workers;
import com.polarising.PortalNet.Repository.ClientRepository;
import com.polarising.PortalNet.Repository.WorkersRepository;
import com.polarising.PortalNet.model.Client;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	Client client;
	
	@Autowired
	Workers worker;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	WorkersRepository workersRepository;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder PasswordEncoder()
	{
		return new BCryptPasswordEncoder();
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
	protected void configure(AuthenticationManagerBuilder auth)  //Here we configure users.
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
	
//	public Authentication attemptAuthentication(HttpServletRequest httpServletRequest)
//	{
//		String token = httpServletRequest.getHeader("Authorization");
//		System.err.println(token);
//		Authentication requestAuthentication = new UsernamePasswordAuthenticationToken(token, token);
//	}
	
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
			.antMatchers("/client/**").access("hasAuthority('CLIENT') or hasAuthority('EMPLOYEE') or hasAuthority('ADMIN')")
			.antMatchers("/clientsTable", "/administrator", "/servicesTable", "/employeesTable/**",  "/createEmployee").access("hasAuthority('EMPLOYEE') or hasAuthority('ADMIN')")
			.antMatchers("/**").access("hasAuthority('ADMIN')")
			.anyRequest().authenticated()
			.and()
			//We specify our own login form
			.formLogin()
			.loginProcessingUrl("/").permitAll()
			.loginPage("/home").permitAll()
			.and()
			.logout().invalidateHttpSession(true)
			.clearAuthentication(true)
			//logout page
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			//logout redirect page
			.logoutSuccessUrl("/home").permitAll()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
		
//		http.addFilterBefore(filter, beforeFilter);
	}
}
