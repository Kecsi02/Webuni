package hu.webuni.hr.mate.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import hu.webuni.hr.mate.model.AverageSalaryByPosition;
import hu.webuni.hr.mate.model.Company;
import hu.webuni.hr.mate.model.Position;

public interface PositionRepository extends JpaRepository<Position, Integer> 
{
	public List<Position> findByName(String name);
}
