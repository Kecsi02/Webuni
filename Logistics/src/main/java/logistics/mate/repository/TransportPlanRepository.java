package logistics.mate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import logistics.mate.model.TransportPlan;

public interface TransportPlanRepository extends JpaRepository<TransportPlan, Long> {}
