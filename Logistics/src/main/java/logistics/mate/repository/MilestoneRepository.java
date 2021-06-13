package logistics.mate.repository;
import logistics.mate.model.Milestone;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<Milestone, Long>{
	List<Milestone> findByAddressId(long Id);
}
