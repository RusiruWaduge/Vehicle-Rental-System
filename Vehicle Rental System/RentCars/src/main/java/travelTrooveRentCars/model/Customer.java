package travelTrooveRentCars.model;

public class Customer {
	
	private int cusID;
	private String cusName;
	private String address;
	private String email;
	private String phoneNumber;
	private String userName;
	private String password;
	
	public Customer(int cusID, String cusName, String address, String email, String phoneNumber, String userName,
			String password) {
		super();
		this.cusID = cusID;
		this.cusName = cusName;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.userName = userName;
		this.password = password;
	}

	public Customer(String cusName, String address, String email, String phoneNumber, String userName,
			String password) {
		super();
		this.cusName = cusName;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.userName = userName;
		this.password = password;
	}

	public int getCusID() {
		return cusID;
	}

	public void setCusID(int cusID) {
		this.cusID = cusID;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
