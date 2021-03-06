package hu.webuni.hr.mate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import hu.webuni.hr.mate.service.EmployeeService;
import hu.webuni.hr.mate.service.SmartEmployeeService;

@Configuration
@Profile("smart")
public class SmartSalaryConfiguration 
{
	@Bean
	public EmployeeService employeeService() 
	{
		return new SmartEmployeeService();
	}
}
