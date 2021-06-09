package logistics.mate.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import logistics.mate.model.Section;
import logistics.mate.repository.SectionRepository;

public class SectionService {
	@Autowired
	SectionRepository sectionRepo;
	@Autowired
	MilestoneService milestoneServ;
	public List<Section> getSections(){
		return sectionRepo.findAll();
	}
	public Optional<Section> findId(long Id){
		return sectionRepo.findById(Id);
	}
	public Optional<Section> findMilestone(long milestoneId) {
		return sectionRepo.findByMilestoneId(milestoneId);
	}
	public Optional<Section> findByTransportPlanId(long Id) {
		return sectionRepo.findByTransportId(Id);
	}
	public Optional<Section> findNumber(int Number) {
		return sectionRepo.findByNumber(Number);
	}
	@Transactional
	public void delete() {
		getSections.stream().forEach(s->s.setFrom(null));
		getSections.stream().forEach(s -> s.setTo(null));
		sectionRepo.deleteAll();
	}
}
