package hu.webuni.hr.mate.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import hu.webuni.hr.mate.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> 
{
	Page<Employee> findBySalaryGreaterThan(Integer minSalary, Pageable pageable);
	List<Employee> findByPositionName(String title);
	List<Employee> findByNameStartingWithIgnoreCase(String name);
	List<Employee> findByEntryDateBetween(LocalDateTime start, LocalDateTime end);
	@Modifying
	@Transactional
	@Query("UPDATE Employee e "
			+ "SET e.salary = :minSalary "
			+ "WHERE e.id IN ("
			+ "SELECT e2.id "
			+ "FROM Employee e2 "
			+ "WHERE e2.position.name=:positionName "
			+ "AND e2.salary< :minSalary "
			+ "AND e2.company.id=:companyId)")
	int updateSalaries(String positionName, int minSalary, long companyId);
	Optional<Employee> findByUsername(String username);
}
