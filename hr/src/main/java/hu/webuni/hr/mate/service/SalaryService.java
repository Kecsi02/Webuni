package hu.webuni.hr.mate.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import hu.webuni.hr.mate.model.Employee;
import hu.webuni.hr.mate.repository.EmployeeRepository;
import hu.webuni.hr.mate.repository.PositionDetailsByCompanyRepository;
import hu.webuni.hr.mate.repository.PositionRepository;

@Service
public class SalaryService 
{
	private EmployeeService employeeService;
	private PositionRepository positionRepository;
	PositionDetailsByCompanyRepository positionDetailsByCompanyRepository;
	EmployeeRepository employeeRepository;
	public SalaryService(EmployeeService employeeService, PositionRepository positionRepository,
			PositionDetailsByCompanyRepository positionDetailsByCompanyRepository,
			EmployeeRepository employeeRepository) {
		super();
		this.employeeService = employeeService;
		this.positionRepository = positionRepository;
		this.positionDetailsByCompanyRepository = positionDetailsByCompanyRepository;
		this.employeeRepository = employeeRepository;
	}
	public void setNewSalary(Employee employee) {
		int newSalary = employee.getSalary() * (100 + employeeService.getPayRaisePercent(employee)) / 100;
		employee.setSalary(newSalary);
	}
	@Transactional
	public void raiseMinimalSalary(String positionName, int minSalary, long companyId) {
		positionDetailsByCompanyRepository.findByPositionNameAndCompanyId(positionName, companyId)
		.forEach(pd ->{
			pd.setMinSalary(minSalary);
		});
		employeeRepository.updateSalaries(positionName, minSalary, companyId);
	}
}
