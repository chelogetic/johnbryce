package main.FacadeClasses;

import java.time.LocalDate;
import java.util.ArrayList;

import main.Beans.Category;
import main.Beans.Coupon;
import main.Beans.Customer;
import main.DAO.CompaniesDBDAO;
import main.DAO.CouponsDBDAO;
import main.DAO.CustomersDBDAO;
import main.Exceptions.CannotPurchaseSameCouponAgainException;
import main.Exceptions.CouponHasBeenExpiredAndCannotBeBought;
import main.Exceptions.OutOfCouponsException;

public class CustomerFacade extends ClientFacade{

	private int customerID;

	public CustomerFacade() {
		companiesDAO = new CompaniesDBDAO();
		customersDAO = new CustomersDBDAO();
		couponsDAO = new CouponsDBDAO();
	}
	
	public  boolean login(String email, String password) throws Exception {

		boolean isCustomerExists = customersDAO.isCustomerExists(email, password);

		if(isCustomerExists == true) {	
			customerID = customersDAO.getCustomerIDByEmail(email);	
		}
		return isCustomerExists == true;
	}

	public void purchaseCoupon(Coupon coupon) throws Exception {

		Customer customer = customersDAO.getOneCustomer(customerID);
		ArrayList<Coupon> coupons = customer.getCoupons();
		
		Coupon couponFromDB = couponsDAO.getOneCoupon(coupon.getId());
		for(Coupon listedCoupon : coupons) {
			if(listedCoupon.getTitle().equals(couponFromDB.getTitle())) {
				throw new CannotPurchaseSameCouponAgainException();
			}
		}

		if(couponFromDB.getAmount() == 0) {
			throw new OutOfCouponsException();
		}

		if(couponFromDB.getEndDate().isBefore(LocalDate.now())) {
			throw new CouponHasBeenExpiredAndCannotBeBought();
		}

		couponsDAO.addCouponPurchase(customerID, coupon.getId());

		int couponsAmount = couponFromDB.getAmount();
		couponFromDB.setAmount(--couponsAmount);
		couponsDAO.updateCoupon(couponFromDB);
	}

	public ArrayList<Coupon> getCustomerCoupons() throws Exception {

		Customer customer = customersDAO.getOneCustomer(customerID);
		return customer.getCoupons();
	}

	public ArrayList<Coupon> getCustomerCoupons(Category category) throws Exception {

		Customer customer = customersDAO.getOneCustomer(customerID);
		ArrayList<Coupon> coupons = customer.getCoupons();

		ArrayList<Coupon> categoryCoupons = new ArrayList<>();

		for(Coupon listedCoupon : coupons) {
			if(listedCoupon.getCategory().equals(category)) {
				categoryCoupons.add(listedCoupon);
			}
		}
		return categoryCoupons;
	}

	public ArrayList<Coupon> getCustomerCoupons(double maxPrice) throws Exception {

		Customer customer = customersDAO.getOneCustomer(customerID);
		ArrayList<Coupon> coupons = customer.getCoupons();

		ArrayList<Coupon> maxPriceCoupons = new ArrayList<>();

		for(Coupon listedCoupon : coupons) {
			if(listedCoupon.getPrice() <= (maxPrice)) {
				maxPriceCoupons.add(listedCoupon);
			}
		}
		return maxPriceCoupons;
	}

	public Customer getCustomerDetailes() throws Exception {

		Customer customer = customersDAO.getOneCustomer(customerID);
		return customer;
	}
}