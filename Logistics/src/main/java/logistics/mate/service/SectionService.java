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
		return sectionRepo.findByMId(milestoneId);
	}
	public List<Section> findByTransportPlanAndMilestone(long transportPlanId, long milestoneId) {
		return sectionRepo.findByTPMS(transportPlanId, milestoneId);
	}
	public Optional<Section> findByTPN(long Id, int number) {
		return sectionRepo.findByTPN(Id, number);
	}
	@Transactional
	public Section addSection(Section section) {
		Section newSection = sectionRepo.save(section);
		return newSection;
	}
	@Transactional
	public void delete() {
		getSections().stream().forEach(s->s.setFrom(null));
		getSections().stream().forEach(s -> s.setTo(null));
		sectionRepo.deleteAll();
	}
}
