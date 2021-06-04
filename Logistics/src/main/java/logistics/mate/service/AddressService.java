package logistics.mate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javassist.tools.web.BadHttpRequest;
import logistics.mate.repository.MilestoneRepository;
import logistics.mate.model.Address;
import logistics.mate.repository.AddressRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

public class AddressService {
	@Autowired
	AddressRepository AddressRepo;
	@Autowired
	MilestoneService MilestoneServ;
	public List<Address> AllAddress(){
		return AddressRepo.findAll();
	}
	public Optional<Address> AddressId(long Id){
		return AddressRepo.findById(Id);
	}
	@Transactional
	public Address NewAddress(Address Address) {
		return AddressRepo.save(Address);
	}
	@Transactional
	public void DeleteAddress(long Id) throws BadHttpRequest{
		if (AddressRepo.findById(Id).isPresent()) {
			if (!MilestoneServ.findByAddressId(Id).isEmpty()) {
				throw new BadHttpRequest();
			}
		}
		AddressRepo.deleteById(Id);
	}
	@Transactional
	public void deleteAll() {
		AddressRepo.deleteAll();
	}
	@Transactional
	public Address ChangeAddress(Address Address) {
		if (!AddressRepo.existsById(Address.getId())) {
			throw new EntityNotFoundException();
		}
		return AddressRepo.save(Address);
	}
	public Page<Address> AddressExample(AddressExampleDto Example, Pageable Pageable){
		String ISO = Example.getISO();
		String City = Example.getCity();
		String Street = Example.getStreet();
		String ZIP = Example.getZIP();
		String Number = Example.getNumber();
		Specification<Address> specification = Specification.where(null);
		if (StringUtils.hasText(ISO)) {
			specification = specification.and(AddressSpecifications.hasISO(ISO));
		}
		if (StringUtils.hasText(City)) {
			specification = specification.and(AddressSpecifications.hasCity(City));
		}
		if (StringUtils.hasText(Street)) {
			specification = specification.and(AddressSpecifications.hasStreet(Street));
		}
		if (StringUtils.hasText(ZIP)) {
			specification = specification.and(AddressSpecifications.hasZIP(ZIP));
		}
		return AddressRepo.findAll(specification, Pageable);
	}
}
