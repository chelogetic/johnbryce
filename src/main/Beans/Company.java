package main.Beans;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * Company JavaBean
 * @author chelogetic
 */
@XmlRootElement
public class Company {
	/**
	 * Company's id.
	 */
	private int id;
	/**
	 * Company's name.
	 */
	private String name;
	/**
	 * Company's email.
	 */
	private String email;
	/**
	 * Company's password.
	 */
	private String password;
	/**
	 * Company's coupons' Array.
	 */
	private ArrayList<Coupon> coupons;
	/**
	 * Company ctor.
	 * @param id Company's id sent to the ctor.
	 * @param name Company's name sent to the ctor.
	 * @param email Company's email sent to the ctor.
	 * @param password Company's password sent to the ctor.
	 * @param coupons Company's Array of coupons sent to the ctor.
	 */
	public Company(int id, String name, String email, String password, ArrayList<Coupon> coupons) {
		setId(id);
		setName(name);
		setEmail(email);
		setPassword(password);
		setCoupons(coupons);
	}
	public Company(String name, String email, String password, ArrayList<Coupon> coupons) {
		setName(name);
		setEmail(email);
		setPassword(password);
		setCoupons(coupons);
	}
	
	public String toString() { 
	    return "Company With ID number of "+id+":\n"+
	    		" ID: "+this.id+"\n"+
	    		" Name: "+this.name+"\n"+
	    		" Email: "+this.email+"\n"+
	    		" Password: "+this.password+"\n"+
	    		" Coupons: "+this.coupons+"\n\n";
	} 
	
	public Company() {}

	/**
	 * Get Company's id.
	 * @return Company's id.
	 */
	public int getId() {
		return id;
	}
	/**
	 * Set Company's id.
	 * @param id Company's id.
	 */
	@XmlElement
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * Get Company's name.
	 * @return Company's name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * Set Company's name.
	 * @param name Company's name.
	 */
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Get Company's email.
	 * @return Company's email.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Set Company's email.
	 * @param email Company's email.
	 */
	@XmlElement
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Get Company's password.
	 * @return Company's password.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Set Company's password.
	 * @param password Company's password.
	 */
	@XmlElement
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Get Company's coupons.
	 * @return Company's coupons.
	 */
	public ArrayList<Coupon> getCoupons() {
		return coupons;
	}
	/**
	 * Set Company's coupons' array.
	 * @param coupons Company's Array of coupons .
	 */
	@XmlElement
	public void setCoupons(ArrayList<Coupon> coupons) {
		this.coupons = coupons;
	}
}