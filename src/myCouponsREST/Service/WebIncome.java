package myCouponsREST.Service;

public class WebIncome { // This class is made for a clearer output to WEB, making date* and description* output as strings
	
	private int id;
	private String email;
	private String date;
	private String description;
	private double amount;
	
	public WebIncome() {}
	
	public WebIncome(int id, String email, String date, String description, double amount) {
		setId(id);
		setEmail(email);
		setDate(date);
		setDescription(description);
		setAmount(amount);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
