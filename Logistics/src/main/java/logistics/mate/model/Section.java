package logistics.mate.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Section {
	@javax.persistence.Id
	@GeneratedValue
	private long Id;
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Milestone getFrom() {
		return From;
	}
	public void setFrom(Milestone from) {
		From = from;
	}
	public Milestone getTo() {
		return To;
	}
	public void setTo(Milestone to) {
		To = to;
	}
	public TransportPlan getTransportPlan() {
		return TransportPlan;
	}
	public void setTransportPlan(TransportPlan transportPlan) {
		TransportPlan = transportPlan;
	}
	private int number;
	@OneToOne
	private Milestone From;
	@OneToOne
	private Milestone To;
	@ManyToOne
	@JoinColumn(name = "TransportPlan_Id")
	private TransportPlan TransportPlan;
	public Section(long Id,Milestone From, Milestone To, int number, TransportPlan TransportPlan) {
		this.Id = Id;
		this.number = number;
		this.TransportPlan = TransportPlan;
		this.From = From;
		this.To = To;
	}
}
