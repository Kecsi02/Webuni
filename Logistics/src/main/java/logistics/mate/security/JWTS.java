package logistics.mate.security;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JWTS {
	private String issuer = "Logistics App";
	private static final String AUTH = "auth";
	private static final int TIMEOUT_MINUTES = 10;
	private Algorithm alg = Algorithm.HMAC256("JavApp");
	public String addJWTToken(UserDetails principal) {
		return JWT.create().withSubject(principal.getUsername()).withArrayClaim(AUTH, principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new))
				.withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(TIMEOUT_MINUTES))).withIssuer(issuer).sign(alg);
	}
	public UserDetails JWTP(String jwtToken) {
		DecodedJWT decodedJWT = JWT.require(alg).withIssuer(issuer).build().verify(jwtToken);
		return new User(decodedJWT.getSubject(), "Java", decodedJWT.getClaim(AUTH).asList(String.class).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
	}
}
