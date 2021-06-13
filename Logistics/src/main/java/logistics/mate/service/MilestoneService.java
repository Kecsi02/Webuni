package logistics.mate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import logistics.mate.model.Milestone;
import logistics.mate.repository.MilestoneRepository;

@Service
public class MilestoneService {
	@Autowired
	MilestoneRepository milestoneRepo;
	public List<Milestone> getMilestones(){
		return milestoneRepo.findAll();
	}
	public List<Milestone> findAddressId(long Id){
		return milestoneRepo.findByAddressId(Id);
	}
	public Optional<Milestone> findId(long Id){
		return milestoneRepo.findById(Id);
	}
	@Transactional
	public Milestone addMilestone(Milestone milestone) {
		return milestoneRepo.save(milestone);
	}
	@Transactional
	public void deleteAll() {
		getMilestones().stream().forEach(m -> m.setAddress(null));
		milestoneRepo.deleteAll();
	}
}
