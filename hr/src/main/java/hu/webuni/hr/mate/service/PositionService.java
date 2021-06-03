package hu.webuni.hr.mate.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hu.webuni.hr.mate.model.Employee;
import hu.webuni.hr.mate.model.Position;
import hu.webuni.hr.mate.repository.PositionRepository;

@Service
public class PositionService 
{
	@Autowired
	PositionRepository positionRepository;
	public void setPositionByName(Employee employee) 
	{
		Position position = null;
		String positionName = employee.getPosition().getName();
		if (positionName != null) 
		{
			List<Position> positions = positionRepository.findByName(positionName);
			if (positions.isEmpty()) 
			{
				position = positionRepository.save(new Position(positionName, null));
			} else {
				position = positions.get(0);
			}
		}
		employee.setPosition(position);
	}
}
