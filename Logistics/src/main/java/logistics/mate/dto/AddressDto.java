package logistics.mate.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AddressDto {
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
		this.ISO = iSO;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		this.City = city;
	}
	public String getStreet() {
		return Street;
	}
	public void setStreet(String street) {
		this.Street = street;
	}
	public String getZIP() {
		return ZIP;
	}
	public void setZIP(String zIP) {
		this.ZIP = zIP;
	}
	public String getNumber() {
		return Number;
	}
	public void setNumber(String number) {
		this.Number = number;
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
	public AddressDto(long Id, @NotEmpty String ISO, @NotEmpty String City, @NotEmpty String Street, @NotEmpty String ZIP, @NotEmpty String Number, Double Length, Double Width) {
		this.Id = Id;
		this.ISO = ISO;
		this.City = City;
		this.Street = Street;
		this.ZIP = ZIP;
		this.Number = Number;
		this.Length = Length;
		this.Width = Width;
	}
}