package logistics.mate.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import logistics.mate.model.Section;

public interface SectionRepository extends JpaRepository<Section, Long>
{
	@Query("SELECT s FROM Section s WHERE s.transportPlan.id = :transportPlanId AND s.number = :number")
	Optional<Section> findByTPN(long transportPlanId, int number);
	@Query("SELECT s FROM Section s WHERE s.From.id = :milestoneId OR s.To.id = :milestoneId")
	Optional<Section> findByMId(long milestoneId);
	@Query("SELECT s FROM Section s WHERE s.transportPlan.id = :transportPlanId AND (s.From.id = :milestoneId OR s.toMilestone.id = :milestoneId)")
	List<Section> findByTPMS(long transportPlanId, long milestoneId);
}
