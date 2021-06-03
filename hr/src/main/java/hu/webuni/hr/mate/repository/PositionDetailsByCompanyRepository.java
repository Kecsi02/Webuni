package hu.webuni.hr.mate.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import hu.webuni.hr.mate.model.Position;
import hu.webuni.hr.mate.model.PositionDetailsByCompany;

public interface PositionDetailsByCompanyRepository extends JpaRepository<PositionDetailsByCompany, Long> 
{
	List<PositionDetailsByCompany> findByPositionNameAndCompanyId(String positionName, long companyId);
}
