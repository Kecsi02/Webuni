package hu.webuni.hr.mate.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Employee 
{
	@Id
	@GeneratedValue
	public Long id;
	private String name;
	private int salary;
	private LocalDateTime entryDate;
	private String username;
	private String password;
	@ManyToOne
	private Company company;
	@ManyToOne
	private Position position;
	@OneToMany(mappedBy = "employee")
	private List<HolidayRequest> holidayRequests;
	@ManyToOne
	private Employee manager;
	public Employee() {
	}
	public Employee(Long id, String name, int salary, LocalDateTime entryDate)
	{
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.entryDate = entryDate;
	}
	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public int getSalary() 
	{
		return salary;
	}

	public void setSalary(int salary) 
	{
		this.salary = salary;
	}

	public LocalDateTime getEntryDate() 
	{
		return entryDate;
	}

	public void setEntryDate(LocalDateTime entryDate) 
	{
		this.entryDate = entryDate;
	}

	public Company getCompany() 
	{
		return company;
	}

	public void setCompany(Company company) 
	{
		this.company = company;
	}

	public Position getPosition() 
	{
		return position;
	}

	public void setPosition(Position position) 
	{
		this.position = position;
	}

	public List<HolidayRequest> getHolidayRequests() 
	{
		return holidayRequests;
	}

	public void setHolidayRequests(List<HolidayRequest> holidayRequests) 
	{
		this.holidayRequests = holidayRequests;
	}

	public void addHolidayRequest(HolidayRequest holidayRequest) 
	{
		if(this.holidayRequests == null)
			this.holidayRequests = new ArrayList<>();
		
		this.holidayRequests.add(holidayRequest);
		holidayRequest.setEmployee(this);
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public Employee getManager() 
	{
		return manager;
	}
	public void setManager(Employee manager) 
	{
		this.manager = manager;
	}
}
