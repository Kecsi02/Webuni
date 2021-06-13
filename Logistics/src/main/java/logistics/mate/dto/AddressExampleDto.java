package logistics.mate.dto;

public class AddressExampleDto {
	private String ISO;
	private String City;
	private String Street;
	private String ZIP;
	
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
	public String getISO() {
		return ISO;
	}
	public void setISO(String iSO) {
		ISO = iSO;
	}
	public AddressExampleDto(String City, String Street, String ZIP, String ISO) {
		this.City = City;
		this.Street = Street;
		this.ZIP = ZIP;
		this.ISO = ISO;
	}
}
