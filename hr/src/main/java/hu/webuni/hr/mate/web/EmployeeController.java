package hu.webuni.hr.mate.web;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import hu.webuni.hr.mate.dto.EmployeeDto;
import hu.webuni.hr.mate.mapper.EmployeeMapper;
import hu.webuni.hr.mate.model.Employee;
import hu.webuni.hr.mate.repository.EmployeeRepository;
import hu.webuni.hr.mate.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController 
{
	@Autowired
	EmployeeService employeeService;
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	EmployeeRepository employeeRepository;
	@GetMapping
	public List<EmployeeDto> getEmployees(@RequestParam(required = false) Integer minSalary, Pageable pageable)
	{
		List<Employee> employees = null;
		if(minSalary == null) {
			employees = employeeService.findAll();
		} 
		else 
		{
			Page<Employee> page = employeeRepository.findBySalaryGreaterThan(minSalary, pageable);
			employees = page.getContent();
			System.out.println(page.getNumber());
			System.out.println(page.getNumberOfElements());
			System.out.println(page.getSize());
			System.out.println(page.getTotalElements());
			System.out.println(page.getTotalPages());
			System.out.println(page.isFirst());
		}
		return employeeMapper.employeesToDtos(employees);
	}
	
	@GetMapping("/{id}")
	public EmployeeDto getById(@PathVariable long id)
	{
		Employee employee = findByIdOrThrow(id);
		return employeeMapper.employeeToDto(employee);
	}

	private Employee findByIdOrThrow(long id)
	{
		return employeeService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping
	public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employeeDto) 
	{
		return employeeMapper.employeeToDto(employeeService.save(employeeMapper.dtoToEmployee(employeeDto)));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable long id, @RequestBody @Valid EmployeeDto employeeDto) 
	{
		employeeDto.setId(id);
		Employee updatedEmployee = employeeService.update(employeeMapper.dtoToEmployee(employeeDto));
		if(updatedEmployee == null) 
		{
			return ResponseEntity.notFound().build();
		} 
		else 
		{
			return ResponseEntity.ok(employeeMapper.employeeToDto(updatedEmployee));
		}
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable long id) 
	{
		employeeService.delete(id);
	}
	
	@PostMapping("/payRaise")
	public int getPayRaise(@RequestBody Employee employee) 
	{
		return employeeService.getPayRaisePercent(employee);
	}
	
	@PostMapping("/search")
	public List<EmployeeDto> findByExample(@RequestBody EmployeeDto example) 
	{
		return employeeMapper.employeesToDtos(employeeService.findEmployeesByExample(employeeMapper.dtoToEmployee(example)));
	}
}
