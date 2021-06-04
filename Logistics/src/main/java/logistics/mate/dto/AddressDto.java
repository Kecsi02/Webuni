package logistics.mate.dto;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AddressDto {
	@javax.persistence.Id
	@GeneratedValue
	private long Id;
	@NotEmpty
	@Size(min=2, max=2)
	private String ISO;
	@NotEmpty
	private String City;
	@NotEmpty
	private String Street;
	@NotEmpty
	private String ZIP;
	@NotEmpty
	private String Number;
	private double Width;
	private double Length;
	
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getISO() {
		return ISO;
	}
	public void setISO(String iSO) {
		ISO = iSO;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getStreet() {
		return Street;
	}
	public void setStreet(String street) {
		Street = street;
	}
	public String getZIP() {
		return ZIP;
	}
	public void setZIP(String zIP) {
		ZIP = zIP;
	}
	public double getWidth() {
		return Width;
	}
	public void setWidth(double Width) {
		this.Width = Width;
	}
	public double getLength() {
		return Length;
	}
	public void setLength(double Length) {
		this.Length = Length;
	}
	public AddressDto(long Id, String ISO, String City, String Street, String ZIP, String Number, double Width, double Length) {
		this.Id = Id;
		this.ISO = ISO;
		this.City = City;
		this.Street = Street;
		this.ZIP = ZIP;
		this.Number = Number;
		this.Width = Width;
		this.Length = Length;
	}
}