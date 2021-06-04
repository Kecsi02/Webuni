package logistics.mate.service;

import logistics.mate.model.Address;
import logistics.mate.model.Address_;
import org.springframework.data.jpa.domain.Specification;

public class AddressSpecifications {
	public static Specification<Address> hasISO(String ISO){
		return (root, cq, cb)-> cb.equal(root.get(Address_.ISO), ISO);
	}
	public static Specification<Address> hasCity(String City){
		return (root, cq, cb)-> cb.like(cb.lower(root.get(Address_.City)),(City + "%").toLowerCase());
	}
	public static Specification<Address> hasStreet(String Street){
		return (root, cq, cb)-> cb.like(cb.lower(root.get(Address_.Street)),(Street + "%").toLowerCase());
	}
	public static Specification<Address> hasZIP(String ZIP){
		return (root, cq, cb)-> cb.equal(root.get(Address_.ZIP), ZIP);
	}
}
