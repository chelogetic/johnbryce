package main.Beans;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * Coupon JavaBean
 * @author chelogetic
 */
@XmlRootElement
public class Coupon {
	/**
	 * Coupon's id.
	 */
	private int id;
	/**
	 * Coupon's companyID.
	 */
	private int companyID;
	/**
	 * Coupon's category.
	 */
	private main.Beans.Category category;
	/**
	 * Coupon's title.
	 */
	private String title;
	/**
	 * Coupon's description.
	 */
	private String description;
	/**
	 * Coupon's startDate.
	 */
	private LocalDate startDate;
	/**
	 * Coupon's endDate.
	 */
	private LocalDate endDate;
	/**
	 * Coupon's amount.
	 */
	private int amount;
	/**
	 * Coupon's price.
	 */
	private double price;
	/**
	 * Coupon's image.
	 */
	private String image;
	/**
	 * Customer ctor.
	 * @param id Coupon's id sent to the ctor.
	 * @param companyID Coupon's companyID sent to the ctor.
	 * @param category Coupon's category sent to the ctor.
	 * @param title Coupon's title sent to the ctor.
	 * @param description Coupon's description sent to the ctor.
	 * @param startDate Coupon's startDate sent to the ctor.
	 * @param endDate Coupon's endDate sent to the ctor.
	 * @param amount Coupon's amount sent to the ctor.
	 * @param price Coupon's price sent to the ctor.
	 * @param image Coupon's image sent to the ctor.
	 */
	public Coupon(int id, int companyID, main.Beans.Category category, String title, String description, LocalDate startDate, LocalDate endDate, int amount, double price, String image) {
		setId(id);
		setCompanyID(companyID);
		setCategory(category);
		setTitle(title);
		setDescription(description);
		setStartDate(startDate);
		setEndDate(endDate);
		setAmount(amount);
		setPrice(price);
		setImage(image);
	}
	public Coupon(int companyID, main.Beans.Category category, String title, String description, LocalDate startDate, LocalDate endDate, int amount, double price, String image) {
		setCompanyID(companyID);
		setCategory(category);
		setTitle(title);
		setDescription(description);
		setStartDate(startDate);
		setEndDate(endDate);
		setAmount(amount);
		setPrice(price);
		setImage(image);
	}
	
	public String toString() {
	    return "Coupon With ID number of "+id+
	    		" ID: "+this.id+
	    		" CompanyID: "+this.companyID+
	    		" Category Type: "+this.category+
	    		" Title: "+this.title+
	    		" Description: "+this.description+
	    		" Start Date: "+this.startDate+
	    		" End Date: "+this.endDate+
	    		" Amount: "+this.amount+
	    		" Price: "+this.price+
	    		" Image: "+this.image;
	} 
	
	public Coupon() {}
	/**
	 * Get Coupon's id.
	 * @return Coupon's id.
	 */
	public int getId() {
		return id;
	}
	/**
	 * Set Coupon's id.
	 * @param id Coupon's id.
	 */
	@XmlElement
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * Get Coupon's CompanyID.
	 * @return Coupon's CompanyID.
	 */
	public int getCompanyID() {
		return companyID;
	}
	/**
	 * Set Coupon's CompanyID.
	 * @param companyID Coupon's CompanyID.
	 */
	@XmlElement
	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}
	/**
	 * Get Coupon's Category.
	 * @return Coupon's Category.
	 */
	public main.Beans.Category getCategory() {
		return category;
	}
	/**
	 * Set Coupon's Category.
	 * @param category Coupon's Category.
	 */
	@XmlElement
	public void setCategory(main.Beans.Category category) {
		this.category = category;
	}
	/**
	 * Get Coupon's title.
	 * @return Coupon's title.
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Set Coupon's title.
	 * @param title Coupon's title.
	 */
	@XmlElement
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * Get Coupon's description.
	 * @return Coupon's description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Set Coupon's description.
	 * @param description Coupon's description.
	 */
	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Get Coupon's startDate.
	 * @return Coupon's startDate.
	 */
	public LocalDate getStartDate() {
		return startDate;
	}
	/**
	 * Set Coupon's startDate.
	 * @param startDate Coupon's startDate.
	 */
	@XmlElement
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	/**
	 * Get Coupon's endDate.
	 * @return Coupon's endDate.
	 */
	public LocalDate getEndDate() {
		return endDate;
	}
	/**
	 * Set Coupon's endDate.
	 * @param endDate Coupon's endDate.
	 */
	@XmlElement
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	/**
	 * Get Coupon's amount.
	 * @return Coupon's amount.
	 */
	public int getAmount() {
		return amount;
	}
	/**
	 * Set Coupon's amount.
	 * @param amount Coupon's amount.
	 */
	@XmlElement
	public void setAmount(int amount) {
		this.amount = amount;
	}
	/**
	 * Get Coupon's price.
	 * @return Coupon's price.
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * Set Coupon's price.
	 * @param price Coupon's price.
	 */
	@XmlElement
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * Get Coupon's image.
	 * @return Coupon's image.
	 */
	public String getImage() {
		return image;
	}
	/**
	 * Set Coupon's image.
	 * @param image Coupon's image.
	 */
	@XmlElement
	public void setImage(String image) {
		this.image = image;
	}
}
