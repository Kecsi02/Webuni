package hu.webuni.hr.mate.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import hu.webuni.hr.mate.model.AverageSalaryByPosition;
import hu.webuni.hr.mate.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> 
{
	@Query("SELECT c FROM Company c JOIN c.employees e WHERE e.salary > :minSalary")
	public List<Company> findByEmployeeWithSalaryHigherThan(int minSalary);
	@Query("SELECT c FROM Company c WHERE SIZE(c.employees) > :minEmployeeCount")
	public List<Company> findByEmployeeCountHigherThan(int minEmployeeCount);
	@Query("SELECT e.position.name AS position, avg(e.salary) AS averageSalary "
			+ "FROM Company c "
			+ "JOIN c.employees e "
			+ "WHERE c.id=:companyId "
			+ "GROUP BY e.position.name "
			+ "ORDER BY avg(e.salary) DESC")
	public List<AverageSalaryByPosition> findAverageSalariesByPosition(long companyId);
	@EntityGraph("Company.full")
	@Query("SELECT c FROM Company c")
	public List<Company> findAllWithEmployees();
	@EntityGraph("Company.full")
	public Optional<Company> findWithEmployeesById(Long id);
}
