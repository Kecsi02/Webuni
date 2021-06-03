package hu.webuni.hr.mate.mapper;

import java.util.List;
import javax.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import hu.webuni.hr.mate.dto.HolidayRequestDto;
import hu.webuni.hr.mate.model.HolidayRequest;

@Mapper(componentModel = "spring")
public interface HolidayRequestMapper {

	List<HolidayRequestDto> holidayRequestsToDtos(List<HolidayRequest> holidayRequests);	
	
	@Mapping(source = "employee.id", target = "employeeId")
	@Mapping(source = "approver.id", target = "approverId")	
	HolidayRequestDto holidayRequestToDto(HolidayRequest holidayRequest);

	@Mapping(target = "employee", ignore = true)
	@Mapping(target = "approver", ignore = true)
	HolidayRequest dtoToHolidayRequest(@Valid HolidayRequestDto holidayRequestDto);

	List<HolidayRequest> dtosToHolidayRequests(List<HolidayRequestDto> holidayRequestDtos);
}
