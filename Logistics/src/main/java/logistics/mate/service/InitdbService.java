package logistics.mate.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import logistics.mate.model.Address;
import logistics.mate.model.Section;
import logistics.mate.model.Milestone;
import logistics.mate.model.TransportPlan;

@Service
public class InitdbService {
	@Autowired
	AddressService addressServ;
	@Autowired
	SectionService sectionServ;
	@Autowired
	MilestoneService milestoneServ;
	@Autowired
	TransportPlanService transportPlanServ;
	public TransportPlan initTP() {
		addressServ.DeleteAll();
		milestoneServ.deleteAll();
		sectionServ.delete();
		transportPlanServ.deleteAll();
		Address address1 = addressServ.NewAddress(new Address(1, "HU", "Gyöngyös", "3200", "Farkas Street", "5.", null, null));
		Address address2 = addressServ.NewAddress(new Address(2, "HU", "Budapest", "1151", "Bocskai Street",  "3.", null, null));
		Address address3 = addressServ.NewAddress(new Address(3, "HU", "Salgótarján","2341", "Virág Street",  "7.", null, null));
		Address address4 = addressServ.NewAddress(new Address(4, "HU", "Ózd", "6700", "Pecsét Street", "5.", null, null));
		Address address5 = addressServ.NewAddress(new Address(5, "HU", "Dunaújváros", "4500", "Kézi Street", "9.", null, null));
		Milestone milestone1 = milestoneServ.addMilestone(new Milestone(1, address1, LocalDateTime.of(2021, 06, 10, 20, 0)));
		Milestone milestone2 = milestoneServ.addMilestone(new Milestone(2, address3, LocalDateTime.of(2021, 06, 10, 10, 0)));
		Milestone milestone3 = milestoneServ.addMilestone(new Milestone(3, address3, LocalDateTime.of(2021, 06, 10, 10, 0)));
		Milestone milestone4 = milestoneServ.addMilestone(new Milestone(4, address2, LocalDateTime.of(2021, 06, 11, 10, 0)));
		Milestone milestone5 = milestoneServ.addMilestone(new Milestone(5, address2, LocalDateTime.of(2021, 06, 12, 20, 0)));
		Milestone milestone6 = milestoneServ.addMilestone(new Milestone(6, address2, LocalDateTime.of(2021, 06, 13, 30, 0)));
		Milestone milestone7 = milestoneServ.addMilestone(new Milestone(7, address4, LocalDateTime.of(2021, 06, 14, 10, 0)));
		Milestone milestone8 = milestoneServ.addMilestone(new Milestone(8, address5, LocalDateTime.of(2021, 06, 15, 10, 0)));
		Milestone milestone9 = milestoneServ.addMilestone(new Milestone(9, address5, LocalDateTime.of(2021, 06, 16, 11, 0)));
		Milestone milestone10 = milestoneServ.addMilestone(new Milestone(10, address1, LocalDateTime.of(2021, 06, 18, 11, 0)));
		List<Section> section = new ArrayList<>();
		section.add(sectionServ.addSection(new Section(2, milestone1, milestone2, 1, null)));
		section.add(sectionServ.addSection(new Section(1, milestone5, milestone6, 2, null)));
		section.add(sectionServ.addSection(new Section(1, milestone8, milestone9, 3, null)));
		section.add(sectionServ.addSection(new Section(1, milestone2, milestone4, 4, null)));
		return transportPlanServ.newTP(new TransportPlan(1, section, 10_000L));
	}
}
