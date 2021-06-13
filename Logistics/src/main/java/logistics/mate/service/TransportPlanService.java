package logistics.mate.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import logistics.mate.config.LogisticsConfigProperties;
import logistics.mate.model.Milestone;
import logistics.mate.model.Section;
import logistics.mate.model.TransportPlan;
import logistics.mate.repository.TransportPlanRepository;

public class TransportPlanService {
	@Autowired
	SectionService sectionService;
	@Autowired
	MilestoneService milestoneService;
	@Autowired
	TransportPlanRepository transportPlanRepo;
	@Autowired
	LogisticsConfigProperties config;
	public Optional<TransportPlan> findById(long Id){
		return transportPlanRepo.findById(Id);
	}
	public List<TransportPlan> geTP(){
	return transportPlanRepo.findAll();
	}
	@Transactional
	public TransportPlan newTP(TransportPlan transportPlan) {
		TransportPlan newTransportPlan = transportPlanRepo.save(transportPlan);
		newTransportPlan.getSection().stream().forEach(s->s.setTransportPlan(newTransportPlan));
		return newTransportPlan;
	}
	@Transactional
	public TransportPlan updateTP(TransportPlan transportPlan) {
		return transportPlanRepo.save(transportPlan);
	}
	@Transactional
	public void deleteAll() {
		sectionService.getSections().stream().forEach(s->s.setTransportPlan(null));
		geTP().stream().forEach(t->t.setSection(null));
		transportPlanRepo.deleteAll();
	}
	@Transactional
	public long Delay( long tPid,long milestoneId, int delay) {
		long newIncome = adjIncome(tPid, delay);
		setDelay(tPid, milestoneId, delay);
		return newIncome;
	}
	private long adjIncome(long tPid, int delay) {
		TransportPlan transportPlan = transportPlanRepo.findById(tPid).get();
		long cIncome = transportPlan.getIncomeExpectation();
		long adjdIncome = cIncome;
		if (delay < 30) 
		{
			adjdIncome *= (100-config.getIncomeDrop().getUnder30m()) * 0.01;
		} 
		else if (delay < 60) 
		{
			adjdIncome *= (100-config.getIncomeDrop().getUnder60m()) * 0.01;
		} 
		else if (delay < 120) 
		{
			adjdIncome *= (100-config.getIncomeDrop().getUnder120m()) * 0.01;
		} 
		else 
		{
			adjdIncome *= (100-config.getIncomeDrop().getAbove120m()) * 0.01;
		}
		transportPlan.setIncomeExpectation(adjdIncome);
		return adjdIncome;
	}
	private void setDelay(long transportPlanId, long firstMilestoneId, int Delay) {
		Milestone currentMilestone = milestoneService.findId(firstMilestoneId).get();
		currentMilestone.setPlannedTime(currentMilestone.getPlannedTime().plusMinutes(Delay));
		Section section = sectionService.findMilestone(firstMilestoneId).get();
		Milestone nextMilestone = null;
		if (section.getFrom().equals(currentMilestone)) {
			nextMilestone = section.getTo();
		} 
		else 
		{
			int nextSectionNumber = section.getNumber() + 1;
			Section nextSection = sectionService.findByTPN(transportPlanId, nextSectionNumber).orElse(null);
			if (nextSection != null) {	
				nextMilestone = nextSection.getFrom();
			}
		}
		if (nextMilestone != null) {
			nextMilestone.setPlannedTime(nextMilestone.getPlannedTime().plusMinutes(Delay));
		}
	}
}

