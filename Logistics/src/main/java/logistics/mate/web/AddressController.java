package logistics.mate.web;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import logistics.mate.dto.AddressDto;
import logistics.mate.dto.AddressExampleDto;
import logistics.mate.mapper.AddressMapper;
import logistics.mate.model.Address;
import logistics.mate.service.AddressService;
import javassist.tools.web.BadHttpRequest;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
	@Autowired
	AddressMapper addressMapper;
	@Autowired
	AddressService addressService;
	@GetMapping
	public List<AddressDto> getAddress() {
		return addressMapper.addressToDto(addressService.AllAddress());
	}
	@GetMapping("/{id}")
	public AddressDto getAddressById(@PathVariable long Id) {
		return addressMapper.addressToDto(addressService.AddressId(Id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
	}
	@PostMapping(value = "/search")
	public ResponseEntity<List<AddressDto>> findByExample(@RequestBody AddressExampleDto example, @PageableDefault(direction = Sort.Direction.ASC, page = 0, size = Integer.MAX_VALUE, sort = "id") Pageable pageable){
		Page<Address> result = addressService.AddressExample(example, pageable);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Count", Long.toString(result.getTotalElements()));
		return ResponseEntity.ok().headers(responseHeaders).body(addressMapper.addressToDto(result.getContent()));
	}
	@PostMapping
	@PreAuthorize("hasAuthority('AddressManager')")
	public AddressDto addNewAddress(@RequestBody @Valid AddressDto addressDto) {
		if (addressDto != null && addressDto.getId() != 0L)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		return addressMapper.addressToDto(addressService.NewAddress(addressMapper.dtoToAddress(addressDto)));
	}
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('AddressManager')")
	public void deleteAddress(@PathVariable long Id) {
		try {
			addressService.DeleteAddress(Id);
		} catch (BadHttpRequest e) {
		  throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('AddressManager')")
	public AddressDto modifyAddress(@RequestBody @Valid AddressDto addressDto, @PathVariable long Id) {
		if (addressDto != null && addressDto.getId() != 0 && addressDto.getId() != Id)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		addressDto.setId(Id);
		AddressDto modifiedAddress;
		try {
			modifiedAddress = addressMapper.addressToDto(addressService.ChangeAddress(addressMapper.dtoToAddress(addressDto)));
		} catch (EntityNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return modifiedAddress;
	}
}
