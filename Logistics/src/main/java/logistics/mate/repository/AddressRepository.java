package logistics.mate.repository;

import logistics.mate.model.Address;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, Long>, JpaSpecificationExecutor<Address>{}
