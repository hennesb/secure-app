package ie.secure;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class AppTokenProducer {
	
	private static final String APP = "secure-apps";
	private static final String SECRET_KEY = "some-secret-key";
	private static final long defaultTimeToLive = 600000L;
	
	public AppTokenProducer(){
		
	}
	
	public String createJWTToken(Identity identity, long timetolive){
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	 
	    JwtBuilder builder = Jwts.builder().setId(UUID.randomUUID().toString())
	                                .setIssuedAt(now)
	                                .setSubject(identity.getEmail())
	                                .setIssuer(APP)
	                                .signWith(signatureAlgorithm, signingKey);

	    setTTL(builder, timetolive, nowMillis);
	    return builder.compact();
	}

	public String createJWTToken(Identity identity){
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	 
	    JwtBuilder builder = Jwts.builder().setId(UUID.randomUUID().toString())
	                                .setIssuedAt(now)
	                                .setSubject(identity.getEmail())
	                                .setIssuer(APP)
	                                .signWith(signatureAlgorithm, signingKey);

	    setTTL(builder, defaultTimeToLive, nowMillis);
	    return builder.compact();
	}
	
	public Claims extractJWTTokenDetails(String token){
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
				.parseClaimsJws(token).getBody();
		return claims;
	}

	public String createCSRFToken(){
		return UUID.randomUUID().toString();
	}
	
	private void setTTL(JwtBuilder builder, long ttlMillis, long now){
	    if (ttlMillis >= 0) {
	    long expMillis = now + ttlMillis;
	        Date exp = new Date(expMillis);
	        builder.setExpiration(exp);
	    }		
	}
}
