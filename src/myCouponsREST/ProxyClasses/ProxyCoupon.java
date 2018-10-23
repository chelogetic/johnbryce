package myCouponsREST.ProxyClasses;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProxyCoupon {
	private String category;
	private String title;
	private String description;
	private String startDate;
	private String endDate;
	private int amout;
	private double price;
	private String image;
	
	public ProxyCoupon(String category, String title, String description, String startDate,
			String endDate, int amount, double price, String image) {
		setCategory(category);
		setTitle(title);
		setDescription(description);
		setStartDate(startDate);
		setEndDate(endDate);
		setAmount(amount);
		setPrice(price);
		setImage(image);
	}
	
	public ProxyCoupon() {}
	
	public String getCategory() {
		return category;
	}
	@XmlElement
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	@XmlElement
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartDate() {
		return startDate;
	}
	@XmlElement
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	@XmlElement
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getAmount() {
		return amout;
	}
	@XmlElement
	public void setAmount(int amout) {
		this.amout = amout;
	}
	public double getPrice() {
		return price;
	}
	@XmlElement
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	@XmlElement
	public void setImage(String image) {
		this.image = image;
	}
}