package main.FacadeClasses;

import java.util.ArrayList;

import main.Beans.Category;
import main.Beans.Company;
import main.Beans.Coupon;
import main.DAO.CompaniesDBDAO;
import main.DAO.CouponsDBDAO;
import main.DAO.CustomersDBDAO;
import main.Exceptions.CannotAddCouponWithTheSameTitleOfTheSameCompanyException;
import main.Exceptions.CannotUpdateCouponCompanyId;

public class CompanyFacade extends ClientFacade{

	private int companyID;

	public CompanyFacade() {	
		companiesDAO = new CompaniesDBDAO();
		customersDAO = new CustomersDBDAO();
		couponsDAO = new CouponsDBDAO();
	}
	
	public  boolean login(String email, String password) throws Exception {

		boolean isCompanyExists = companiesDAO.isCompanyExists(email, password);
		if(isCompanyExists == true) {	
			companyID = companiesDAO.getCompanyIDByEmail(email);	
		}
		return isCompanyExists == true;
	}

	public void addCoupon(Coupon coupon) throws Exception {

		Company listedCompany = companiesDAO.getOneCompany(companyID);

		for(Coupon listedCoupon : listedCompany.getCoupons()) {
			if(listedCoupon.getTitle().equals(coupon.getTitle())) {
				throw new CannotAddCouponWithTheSameTitleOfTheSameCompanyException();
			}
		}
		couponsDAO.addCoupon(coupon);
	}


	public void updateCoupon(Coupon coupon) throws Exception {

		Company listedCompany = companiesDAO.getOneCompany(companyID);

		for(Coupon listedCompanyCoupon : listedCompany.getCoupons()) {
			if(listedCompanyCoupon.getId() == coupon.getId()) {
				if( listedCompanyCoupon.getCompanyID() != coupon.getCompanyID()) {
					throw new CannotUpdateCouponCompanyId();
				}
			}
			couponsDAO.updateCoupon(coupon);
		}
	}

	public void deleteCoupon(int couponID) throws Exception {
		ArrayList<Coupon>allCompanyCoupons = getCompanyCoupons();
		for(Coupon listedCoupon : allCompanyCoupons) {
			if(listedCoupon.getId() == couponID) {
				couponsDAO.deleteCoupon(couponID);
			}
		}
	}

	public ArrayList<Coupon> getCompanyCoupons() throws Exception {

		Company company = companiesDAO.getOneCompany(companyID);
		return company.getCoupons();
	}

	public ArrayList<Coupon> getCompanyCoupons(Category category) throws Exception {

		Company company = companiesDAO.getOneCompany(companyID);
		ArrayList<Coupon> coupons = company.getCoupons();

		ArrayList<Coupon> categoryCoupons = new ArrayList<>();

		for(Coupon listedCoupon : coupons) {
			if(listedCoupon.getCategory().equals(category)) {
				categoryCoupons.add(listedCoupon);
			}
		}

		return categoryCoupons;
	}

	public ArrayList<Coupon> getCompanyCoupons(double maxPrice) throws Exception { // IS IT OKAY?

		Company company = companiesDAO.getOneCompany(companyID);
		ArrayList<Coupon> coupons = company.getCoupons();

		ArrayList<Coupon> maxPriceCoupons = new ArrayList<>();

		for(Coupon listedCoupon : coupons) {
			if(listedCoupon.getPrice() <= (maxPrice)) {
				maxPriceCoupons.add(listedCoupon);
			}
		}
		return maxPriceCoupons;
	}

	public Company getCompanyDetailes() throws Exception {

		Company company = companiesDAO.getOneCompany(companyID);
		return company;
	}
}
