package main.Exceptions;

@SuppressWarnings("serial")
public class CannotAddCouponWithTheSameTitleOfTheSameCompanyException extends Exception{
	public String getMessage() {
		return "You cannot add a coupon with the same title as there is a coupon with this title that exists in companies' coupons.";
	}
}
