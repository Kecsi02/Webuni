package hu.webuni.hr.mate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.hr.mate.service.DefaultEmployeeService;
import hu.webuni.hr.mate.service.EmployeeService;

@Configuration
@Profile("!smart")
public class DefaultSalaryConfiguration
{
	@Bean
	public EmployeeService employeeService() 
	{
		return new DefaultEmployeeService();
	}
}
