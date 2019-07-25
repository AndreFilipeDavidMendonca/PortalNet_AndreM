package com.polarising.PortalNet.jwt;
import java.sql.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.polarising.PortalNet.Security.UserPrincipal;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtCreator {
	
	//To help us with error logs
	private static final Logger logger = LoggerFactory.getLogger(JwtCreator.class);

	//The JWT signature algorithm we will be using to sign the token
	private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	//We will sign our JWT with our ApiKey secret
	private byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("To change");
	private SecretKeySpec signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	
	//Defining the token's duration
	private int milsec = 86400000;
	
	public String createJWT(Authentication authentication, int Id, String name)
	{
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		
		//Get the current date for the Issued At claim
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		
		//Let's set the JWT claims
		JwtBuilder builder = Jwts.builder()
				.setSubject(userPrincipal.getUsername())
				.claim("userId", Id)
				.claim("userName", name)
				.claim("authorities", userPrincipal.getAuthorities())
				.setIssuedAt(now)
				.signWith(signatureAlgorithm, signingKey);
		
		//Let's add the expiration, if it has been specified
		if (milsec > 0)
		{
			long expMillis = nowMillis + milsec;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}
		
		//Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}
	
	
	public String getJwtUsername(String jwt)
	{
		return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(jwt).getBody().getSubject();
	}
	
	
	public boolean jwtValidate(String jwt) {
		try{
			Jwts.parser().setSigningKey(signingKey).parseClaimsJws(jwt);
			return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }
		return false;
	}
}
