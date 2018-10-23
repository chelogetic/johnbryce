package main.Beans;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * Customer JavaBean
 * @author chelogetic
 */
@XmlRootElement
public class Customer {
	/**
	 * Customer's id.
	 */
	private int id;
	/**
	 * Customer's firstName.
	 */
	private String firstName;
	/**
	 * Customer's lastName.
	 */
	private String lastName;
	/**
	 * Customer's email.
	 */
	private String email;
	/**
	 * Customer's password.
	 */
	private String password;
	/**
	 * Customer's coupons.
	 */
	private ArrayList<Coupon> coupons;
	/**
	 * Customer ctor.
	 * @param id Customer's id sent to the ctor.
	 * @param firstName Customer's firstName sent to the ctor.
	 * @param lastName Customer's lastName sent to the ctor.
	 * @param email Customer's email sent to the ctor.
	 * @param password Customer's password sent to the ctor.
	 * @param coupons Customer's Coupons's array sent to the ctor.
	 */
	public Customer(int id, String firstName, String lastName, String email, String password, ArrayList<Coupon> coupons) {

		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setPassword(password);
		setCoupons(coupons);
	}
	public Customer(String firstName, String lastName, String email, String password, ArrayList<Coupon> coupons) {
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setPassword(password);
		setCoupons(coupons);
	}

	public String toString() { 
		return "Customer With ID number of "+id+":\n"+
				" ID: "+this.id+"\n"+
				" First Name: "+this.firstName+"\n"+
				" Last Name: "+this.lastName+"\n"+
				" Email: "+this.email+"\n"+
				" Password: "+this.password+"\n"+
				" Coupons: "+this.coupons+"\n\n";
	} 

	public Customer() {}
	/**
	 * Get Customer's id.
	 * @return Customer's id.
	 */
	public int getId() {
		return id;
	}
	/**
	 * Set Customer's id.
	 * @param id Customer's id.
	 */
	@XmlElement
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * Get Customer's firstName.
	 * @return Customer's firstName.
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * Set Customer's firstName.
	 * @param firstName Customer's firstName.
	 */
	@XmlElement
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * Get Customer's lastName.
	 * @return Customer's lastName.
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * Set Customer's lastName.
	 * @param lastName Customer's lastName.
	 */
	@XmlElement
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * Get Customer's email.
	 * @return Customer's email.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Set Customer's email.
	 * @param email Customer's email.
	 */
	@XmlElement
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Get Customer's password.
	 * @return Customer's password.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Set Customer's password.
	 * @param password Customer's password.
	 */
	@XmlElement
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Get Customer's coupons.
	 * @return Customer's Coupons' Array.
	 */
	public ArrayList<Coupon> getCoupons() {
		return coupons;
	}
	/**
	 * Set Customer's coupons.
	 * @param coupons Customer's coupons.
	 */
	@XmlElement
	public void setCoupons(ArrayList<Coupon> coupons) {
		this.coupons = coupons;
	}
}
