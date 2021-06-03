package hu.webuni.hr.mate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hu.webuni.hr.mate.config.HrConfigProperties;
import hu.webuni.hr.mate.model.Employee;

@Service
public class DefaultEmployeeService extends AbstractEmployeeService 
{
	@Autowired
	HrConfigProperties config;
	@Override
	public int getPayRaisePercent(Employee employee) 
	{
		return config.getSalary().getDef().getPercent();
	}
}
