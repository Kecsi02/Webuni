package hu.webuni.hr.mate.security;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import hu.webuni.hr.mate.model.Employee;
import hu.webuni.hr.mate.repository.EmployeeRepository;

@Service
public class HrUserDetailsService implements UserDetailsService 
{
	@Autowired
	EmployeeRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		Employee user = userRepository.findByUsername(username)
				.orElseThrow(()-> new UsernameNotFoundException(username));
		return new HrUser(username, user.getPassword(), Arrays.asList(new SimpleGrantedAuthority("USER")), user);
	}
}
