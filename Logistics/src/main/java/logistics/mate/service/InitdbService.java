package logistics.mate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

import logistics.mate.model.Address;
import logistics.mate.model.Section;
import logistics.mate.model.Milestone;
import logistics.mate.model.TransportPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class InitdbService {
	@Autowired
	AddressService addressServ;
	@Autowired
	SectionService sectionServ;
	@Autowired
	TransportPlanService transportPlanServ;
	List<Section> section = new ArrayList<>();
	public TransportPlan initTP() {
		addressServ.deleteAll();
		milestoneServ.deleteAll();
		sectionServ.deleteAll();
		transportPlanServ.deleteAll();
		Address address1 = addressServ.ChangeAddress(new Address(1, "HU" "Gyöngyös" "Farkas Street" "3200"  "5.", null, null));
		Address address2 = addressServ.ChangeAddress(new Address(2, "HU" "Budapest" "Bocskai Street" "1151"  "3.", null, null));
		Address address3 = addressServ.ChangeAddress(new Address(3, "HU" "Salgótarján" "Virág Street" "2341"  "7.", null, null));
		Address address4 = addressServ.ChangeAddress(new Address(4, "HU" "Ózd" "Pecsét Street" "6700"  "5.", null, null));
		Address address5 = addressServ.ChangeAddress(new Address(5, "HU" "Dunaújváros" "Kézi Street" "4500"  "9.", null, null));
		Milestone milestone0 = milestoneServ.addMilestone(new Milestone(0, address4, LocalDateTime.of(2021, 06, 22, 11, 0)));
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
		section.add(sectionServ.addSection(new Section(1, milestone0, milestone3, 0, null));
		section.add(sectionServ.addSection(new Section(2, milestone1, milestone2, 1, null));
		section.add(sectionServ.addSection(new Section(1, milestone5, milestone6, 2, null));
		section.add(sectionServ.addSection(new Section(1, milestone8, milestone9, 3, null));
		section.add(sectionServ.addSection(new Section(1, milestone2, milestone4, 4, null));
		return transportPlanServ.addTransportPlan(new TransportPlan(1, section, 10_000L));
	}
}
