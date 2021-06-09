package logistics.mate.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;

import java.util.List;

@Entity
public class TransportPlan {
	@javax.persistence.Id
	@GeneratedValue
	private long Id;
	private long IncomeExpectation;
	@OneToMany(mappedBy = "TransportPlan")
	private List<Section> section;
	
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public List<Section> getSection() {
		return section;
	}
	public void setSection(List<Section> section) {
		this.section = section;
	}
	public long getIncomeExpectation() {
		return IncomeExpectation;
	}
	public void setIncomeExpectation(long incomeExpectation) {
		IncomeExpectation = incomeExpectation;
	}
	public TransportPlan(long Id, List<Section> Section, long IncomeExpectation) {
		

	}
}
