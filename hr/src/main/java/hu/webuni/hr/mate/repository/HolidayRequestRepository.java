package hu.webuni.hr.mate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import hu.webuni.hr.mate.model.HolidayRequest;

public interface HolidayRequestRepository extends JpaRepository<HolidayRequest, Long>, JpaSpecificationExecutor<HolidayRequest> 
{
	
}
