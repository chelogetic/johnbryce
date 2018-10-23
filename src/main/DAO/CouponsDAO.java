package main.DAO;
import java.util.ArrayList;

import main.Beans.Coupon;

public interface CouponsDAO {
	
	void addCoupon(Coupon coupon) throws Exception;
	void updateCoupon(Coupon coupon) throws Exception;
	void deleteCoupon(int couponID) throws Exception;
	ArrayList<Coupon> getAllCoupons() throws Exception;
	Coupon getOneCoupon(int couponID) throws Exception;
	void deleteCouponPurchase(int customerID, int couponID) throws Exception;
	void addCouponPurchase(int customerID, int couponID) throws Exception;
}