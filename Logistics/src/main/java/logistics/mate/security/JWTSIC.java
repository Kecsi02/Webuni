package logistics.mate.security;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import logistics.mate.dto.SignInDto;

@RestController
public class JWTSIC {
	@Autowired
	JWTS jwtS;
	@Autowired
	AuthenticationManager authManager;
	@PostMapping("/api/login")
	public String signin(@RequestBody SignInDto signInDto) {
		org.springframework.security.core.Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(signInDto.getUsername(), signInDto.getPassword()));
		return jwtS.addJWTToken((UserDetails)auth.getPrincipal());
	}
}
