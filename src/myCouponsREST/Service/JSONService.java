package myCouponsREST.Service;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import main.Beans.Category;
import main.Beans.Company;
import main.Beans.Coupon;
import main.Beans.Customer;
@SuppressWarnings("unchecked")

public class JSONService { // Service made to save code space
	public static JSONObject companyToJSONObject(Company company) {
		JSONObject companyJSONObject = new JSONObject();
		companyJSONObject.put("id", company.getId());
		companyJSONObject.put("name", company.getName());
		companyJSONObject.put("email", company.getEmail());
		companyJSONObject.put("password", company.getPassword());
		if(company.getCoupons() != null) {
			companyJSONObject.put("coupons", JSONService.couponsToJSONArray(company.getCoupons()));
		}
		return companyJSONObject;
	}
	
	public static JSONArray companiesToJSONArray(ArrayList<Company> companies) {
		JSONArray companiesJSONArray = new JSONArray();
		for(Company listedCompany : companies) {
			JSONObject listedCompanyJSONObject = new JSONObject();
			listedCompanyJSONObject.put("id", listedCompany.getId());
			listedCompanyJSONObject.put("name", listedCompany.getName());
			listedCompanyJSONObject.put("email", listedCompany.getEmail());
			listedCompanyJSONObject.put("password", listedCompany.getPassword());
			if(listedCompany.getCoupons() != null) {
				listedCompanyJSONObject.put("coupons", JSONService.couponsToJSONArray(listedCompany.getCoupons()));
			}
			companiesJSONArray.add(listedCompanyJSONObject);
		}
		return companiesJSONArray;
	}
	
	public static JSONObject customerToJSONObject(Customer customer) {
		JSONObject customerJSONObject = new JSONObject();
		customerJSONObject.put("id", customer.getId());
		customerJSONObject.put("firstName", customer.getFirstName());
		customerJSONObject.put("lastName", customer.getLastName());
		customerJSONObject.put("email", customer.getEmail());
		customerJSONObject.put("password", customer.getPassword());
		if(customer.getCoupons() != null) {
			customerJSONObject.put("coupons", JSONService.couponsToJSONArray(customer.getCoupons()));
		}
		return customerJSONObject;
	}
	
	public static JSONArray customersToJSONArray(ArrayList<Customer> customers) {
		JSONArray customersJSONArray = new JSONArray();
		for(Customer listedCustomer : customers) {
			JSONObject listedCustomerJSONObject = new JSONObject();
			listedCustomerJSONObject.put("id", listedCustomer.getId());
			listedCustomerJSONObject.put("firstName", listedCustomer.getFirstName());
			listedCustomerJSONObject.put("lastName", listedCustomer.getLastName());
			listedCustomerJSONObject.put("email", listedCustomer.getEmail());
			listedCustomerJSONObject.put("password", listedCustomer.getPassword());
			if(listedCustomer.getCoupons() != null) {
				listedCustomerJSONObject.put("coupons", JSONService.couponsToJSONArray(listedCustomer.getCoupons()));
			}
			customersJSONArray.add(listedCustomerJSONObject);
		}
		return customersJSONArray;
	}
	
	public static JSONObject couponToJSONObject(Coupon coupon) {
		JSONObject couponJSONObject = new JSONObject();
		couponJSONObject.put("id", coupon.getId());
		couponJSONObject.put("companyID", coupon.getCompanyID());
		couponJSONObject.put("category", Category.categoryToString(coupon.getCategory()));
		couponJSONObject.put("title", coupon.getTitle());
		couponJSONObject.put("description", coupon.getDescription());
		couponJSONObject.put("startDate", coupon.getStartDate().toString());
		couponJSONObject.put("endDate", coupon.getEndDate().toString());
		couponJSONObject.put("amount", coupon.getAmount());
		couponJSONObject.put("price", coupon.getPrice());
		couponJSONObject.put("image", coupon.getImage());
		return couponJSONObject;
	}
	
	public static JSONArray couponsToJSONArray(ArrayList<Coupon> coupons) {
		JSONArray couponsJSONArray = new JSONArray();
		for(Coupon listedCoupon : coupons) {
			JSONObject listedCouponJSONObject = new JSONObject();
			listedCouponJSONObject.put("id", listedCoupon.getId());
			listedCouponJSONObject.put("companyID", listedCoupon.getCompanyID());
			listedCouponJSONObject.put("category", Category.categoryToString(listedCoupon.getCategory()));
			listedCouponJSONObject.put("title", listedCoupon.getTitle());
			listedCouponJSONObject.put("description", listedCoupon.getDescription());
			listedCouponJSONObject.put("startDate", listedCoupon.getStartDate().toString());
			listedCouponJSONObject.put("endDate", listedCoupon.getEndDate().toString());
			listedCouponJSONObject.put("amount", listedCoupon.getAmount());
			listedCouponJSONObject.put("price", listedCoupon.getPrice());
			listedCouponJSONObject.put("image", listedCoupon.getImage());
			couponsJSONArray.add(listedCouponJSONObject);
		}
		return couponsJSONArray;
	}
}