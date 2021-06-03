package hu.webuni.hr.mate.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import hu.webuni.hr.mate.dto.CompanyDto;
import hu.webuni.hr.mate.dto.EmployeeDto;
import hu.webuni.hr.mate.model.Company;
import hu.webuni.hr.mate.model.Employee;

@Mapper(componentModel = "spring")
public interface CompanyMapper 
{
	CompanyDto companyToDto(Company company);
	@Mapping(target = "employees", ignore = true)
	@Named("summary")
	CompanyDto companySummaryToDto(Company company);
	List<CompanyDto> companiesToDtos(List<Company> companies);
	@IterableMapping(qualifiedByName = "summary")
	List<CompanyDto> companySummariesToDtos(List<Company> companies);
	Company dtoToCompany(CompanyDto companyDto);
	@Mapping(target = "companyName", source = "company.name")
	@Mapping(target = "title", source = "position.name")
	EmployeeDto employeeToDto(Employee employee);
}
