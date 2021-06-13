package logistics.mate.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import logistics.mate.dto.AddressDto;
import logistics.mate.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
	List<Address> dtosToAddresses(List<AddressDto> addressDtos);
	Address dtoToAddress(AddressDto addressDto);
	List<AddressDto> addressToDto(List<Address> addresses);
	AddressDto addressToDto(Address address);
}
