package logistics.mate.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import logistics.mate.dto.DelayDto;
import logistics.mate.service.MilestoneService;
import logistics.mate.service.SectionService;
import logistics.mate.service.TransportPlanService;

@RequestMapping("api/tranportPlans")
@RestController
public class TransportPlanController {
@Autowired
SectionService sectionServ;
@Autowired
MilestoneService milestoneServ;
@Autowired
TransportPlanService transportPlanServ;
@PostMapping("/{id}/delay")
public void DelayTP(@PathVariable long Id, @RequestBody DelayDto delay) {
	if (transportPlanServ.findById(Id).isEmpty() || milestoneServ.findId(delay.getMilestoneId()).isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			if (sectionServ.findByTransportPlanAndMilestone(Id, delay.getMilestoneId()).isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		transportPlanServ.Delay(Id, delay.getMilestoneId(),delay.getDelay());
	}
}
