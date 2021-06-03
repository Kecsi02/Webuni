package hu.webuni.hr.mate.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import hu.webuni.hr.mate.dto.EmployeeDto;
import hu.webuni.hr.mate.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper 
{	
	List<EmployeeDto> employeesToDtos(List<Employee> employees);
	@Mapping(target = "companyName", source = "company.name")
	@Mapping(target = "title", source = "position.name")
	EmployeeDto employeeToDto(Employee employee);
	@Mapping(source = "companyName", target = "company.name")
	@Mapping(source = "title", target = "position.name")
	Employee dtoToEmployee(EmployeeDto employeeDto);
	List<Employee> dtosToEmployees(List<EmployeeDto> employees);
}
