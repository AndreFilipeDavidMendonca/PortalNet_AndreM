package com.polarising.PortalNet.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.polarising.PortalNet.Security.UserDetailsService;

public class JwtAuthFilter extends OncePerRequestFilter{
	
	@Autowired
	JwtCreator jwtCreator;
	
	@Autowired
	UserDetailsService userDetails;
	
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(JwtAuthFilter.class);
	
	//Where we decode the token
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try{
			
			String jwt = getJwt(request);
			if (jwt != null && jwtCreator.jwtValidate(jwt))
			{
				String userName = jwtCreator.getJwtUsername(jwt);
				
				//If we can load the user, then we have authenticated it using the jwt
				UserDetails user = userDetails.loadUserByUsername(userName);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
				
				//Optional
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				//Needed to hold user details
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		catch (Exception e)
		{
			logger.error("Unable to set user authentication --> ", e);
		}
		
		//We say to the authentication to continue to the next filter
		filterChain.doFilter(request, response);
	}

	//We take the token from the request header and take the "Bearer " substring from it, so we can then validate and obtain the username from it
	private String getJwt(HttpServletRequest request)
	{
		String jwt = request.getHeader("Authorization");
		if (jwt != null && jwt.startsWith("Bearer "))
		{
			return jwt.replace("Bearer ", "");
		}
		else{
			return null;
		}
	}	
}
