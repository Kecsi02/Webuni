package logistics.mate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javassist.tools.web.BadHttpRequest;
import logistics.mate.repository.MilestoneRepository;
import logistics.mate.dto.AddressExampleDto;
import logistics.mate.model.Address;
import logistics.mate.repository.AddressRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

public class AddressService {
	@Autowired
	AddressRepository addressRepo;
	@Autowired
	MilestoneService milestoneServ;
	public List<Address> AllAddress(){
		return addressRepo.findAll();
	}
	public Optional<Address> AddressId(long Id){
		return addressRepo.findById(Id);
	}
	@Transactional
	public Address NewAddress(Address Address) {
		return addressRepo.save(Address);
	}
	@Transactional
	public void DeleteAddress(long Id) throws BadHttpRequest{
		if (addressRepo.findById(Id).isPresent()) {
			if (!milestoneServ.findAddressId(Id).isEmpty()) {
				throw new BadHttpRequest();
			}
		}
		addressRepo.deleteById(Id);
	}
	@Transactional
	public void DeleteAll() {
		addressRepo.deleteAll();
	}
	@Transactional
	public Address ChangeAddress(Address Address) {
		if (!addressRepo.existsById(Address.getId())) {
			throw new EntityNotFoundException();
		}
		return addressRepo.save(Address);
	}
	public Page<Address> AddressExample(AddressExampleDto Example, org.springframework.data.domain.Pageable pageable){
		String City = Example.getCity();
		String Street = Example.getStreet();
		String ZIP = Example.getZIP();
		String ISO = Example.getISO();
		Specification<Address> specification = Specification.where(null);
		if (StringUtils.hasText(City)) {
			specification = specification.and(AddressSpecifications.hasCity(City));
		}
		if (StringUtils.hasText(Street)) {
			specification = specification.and(AddressSpecifications.hasStreet(Street));
		}
		if (StringUtils.hasText(ZIP)) {
			specification = specification.and(AddressSpecifications.hasZIP(ZIP));
		}
		if (StringUtils.hasText(ISO)) {
			specification = specification.and(AddressSpecifications.hasISO(ISO));
		}
		return addressRepo.findAll(specification, (org.springframework.data.domain.Pageable) pageable);
	}
}
