package hu.webuni.hr.mate.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import hu.webuni.hr.mate.model.Company;
import hu.webuni.hr.mate.model.Employee;
import hu.webuni.hr.mate.repository.CompanyRepository;
import hu.webuni.hr.mate.repository.EmployeeRepository;

@Service
public class CompanyService 
{
	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeRepository employRepository;
	
	@Autowired
	PositionService positionService;

	public Company save(Company company) 
	{
		return companyRepository.save(company);
	}
	@Transactional
	public Company update(Company company) 
	{
		if(!companyRepository.existsById(company.getId()))
			return null;
		return companyRepository.save(company);
	}
	public List<Company> findAll() 
	{
		return companyRepository.findAll();
	}
	public Optional<Company> findById(long id) 
	{
		return companyRepository.findById(id);
	}
	public void delete(long id) {
		companyRepository.deleteById(id);
	}
	@Transactional
	public Company addEmployee(long id, Employee employee) 
	{
		Company company = companyRepository.findWithEmployeesById(id).get();
		positionService.setPositionByName(employee);
		Employee employeeSaved = employeeRepository.save(employee);
		company.addEmployee(employeeSaved);
		return company;
	}
	@Transactional
	public Company deleteEmployee(long id, long employeeId) 
	{
		Company company = companyRepository.findById(id).get();
		Employee employee = employeeRepository.findById(employeeId).get();
		employee.setCompany(null);
		company.getEmployees().remove(employee);
		return company;
	}
	@Transactional
	public Company replaceEmployees(long id, List<Employee> employees) 
	{
		Company company = companyRepository.findById(id).get();
		company.getEmployees().stream().forEach(e -> 
		{
			e.setCompany(null);
		});
		company.getEmployees().clear();
		
		for(Employee emp: employees) 
		{
			company.addEmployee(emp);
			positionService.setPositionByName(emp);
			employeeRepository.save(emp);
		}
		return company;
	}
}
