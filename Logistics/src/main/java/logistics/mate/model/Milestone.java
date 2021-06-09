package logistics.mate.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Milestone {
	@javax.persistence.Id
	@GeneratedValue
	private long Id;
	@ManyToOne
	@JoinColumn(name = "AddressId")
	private Address address;
	private LocalDateTime plannedTime;
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public LocalDateTime getPlannedTime() {
		return plannedTime;
	}
	public void setPlannedTime(LocalDateTime plannedTime) {
		this.plannedTime = plannedTime;
	}
	public Milestone(long Id, Address Address, LocalDateTime plannedTime) {
		this.Id = Id;
		this.address = Address;
		this.plannedTime = plannedTime;
	}
}
