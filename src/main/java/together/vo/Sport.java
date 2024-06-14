package together.vo;

public class Sport {
	int sportNumber;
	String type;
	String city;
	String location;
	String agency;
	String management;
	public Sport() {
		super();
	}
	public Sport(int sportNumber, String type, String city, String location, String agency, String management) {
		super();
		this.sportNumber = sportNumber;
		this.type = type;
		this.city = city;
		this.location = location;
		this.agency = agency;
		this.management = management;
	}
	public int getSportNumber() {
		return sportNumber;
	}
	public void setSportNumber(int sportNumber) {
		this.sportNumber = sportNumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAgency() {
		return agency;
	}
	public void setAgency(String agency) {
		this.agency = agency;
	}
	public String getManagement() {
		return management;
	}
	public void setManagement(String management) {
		this.management = management;
	}
	
}
