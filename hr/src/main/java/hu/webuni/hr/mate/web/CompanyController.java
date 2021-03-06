package hu.webuni.hr.mate.web;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import hu.webuni.hr.mate.dto.CompanyDto;
import hu.webuni.hr.mate.dto.EmployeeDto;
import hu.webuni.hr.mate.mapper.CompanyMapper;
import hu.webuni.hr.mate.mapper.EmployeeMapper;
import hu.webuni.hr.mate.model.AverageSalaryByPosition;
import hu.webuni.hr.mate.model.Company;
import hu.webuni.hr.mate.repository.CompanyRepository;
import hu.webuni.hr.mate.service.CompanyService;

@RestController
@RequestMapping("/api/companies")
public class CompanyController 
{
	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CompanyRepository companyRepository;
	@GetMapping
	public List<CompanyDto> getCompanys(@RequestParam(required = false) Boolean full) 
	{
		return full == null || full == false ? 
				companyMapper.companySummariesToDtos(companyService.findAll()) 
				: companyMapper.companiesToDtos(companyRepository.findAllWithEmployees());
	}
	@GetMapping("/{id}")
	public CompanyDto getById(@PathVariable long id, @RequestParam(required = false) Boolean full) 
	{
		Company company = companyService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return full == null || full == false ? 
				companyMapper.companySummaryToDto(company) 
				: companyMapper.companyToDto(company);
	}
	@PostMapping
	public CompanyDto createCompany(@RequestBody CompanyDto companyDto) 
	{
		return companyMapper.companyToDto(companyService.save(companyMapper.dtoToCompany(companyDto)));
	}
	@PutMapping("/{id}")
	public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto) 
	{
		companyDto.setId(id);
		Company updatedCompany = companyService.update(companyMapper.dtoToCompany(companyDto));
		if (updatedCompany == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(companyMapper.companyToDto(updatedCompany));
	}
	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable long id) 
	{
		companyService.delete(id);
	}
	@PostMapping("/{id}/employees")
	public CompanyDto addNewEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto) 
	{
		try {
			return companyMapper.companyToDto(
					companyService.addEmployee(id, employeeMapper.dtoToEmployee(employeeDto))
					);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/{id}/employees/{employeeId}")
	public CompanyDto deleteEmployee(@PathVariable long id, @PathVariable long employeeId)
	{
		try {
			return companyMapper.companyToDto(
					companyService.deleteEmployee(id, employeeId)
					);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/{id}/employees")
	public CompanyDto replaceEmployees(@PathVariable long id, @RequestBody List<EmployeeDto> employees) 
	{
		try {
			return companyMapper.companyToDto(
					companyService.replaceEmployees(id, employeeMapper.dtosToEmployees(employees))
					);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping(params = "aboveSalary")
	public List<CompanyDto> getCompaniesAboveASalary(@RequestParam int aboveSalary,
			@RequestParam(required = false) String full) 
	{
		List<Company> allCompanies = companyRepository.findByEmployeeWithSalaryHigherThan(aboveSalary);
		if (full == null || full.equals("false")) 
		{
			return companyMapper.companySummariesToDtos(allCompanies);
		} else
			return companyMapper.companiesToDtos(allCompanies);
	}
	@GetMapping(params = "aboveEmployeeNumber")
	public List<CompanyDto> getCompaniesAboveEmployeeNumber(@RequestParam int aboveEmployeeNumber,
			@RequestParam(required = false) String full) 
	{
		List<Company> filteredCompanies = companyRepository.findByEmployeeCountHigherThan(aboveEmployeeNumber);
		if (full == null || full.equals("false")) 
		{
			return companyMapper.companySummariesToDtos(filteredCompanies);
		} else
			return companyMapper.companiesToDtos(filteredCompanies);
	}
	@GetMapping("/{id}/salaryStats")
	public List<AverageSalaryByPosition> getSalaryStatsById(@PathVariable long id, @RequestParam(required = false) Boolean full)
	{
		return companyRepository.findAverageSalariesByPosition(id);
	}
}
