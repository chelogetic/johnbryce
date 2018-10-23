package main.Exceptions;

@SuppressWarnings("serial")
public class CannotUpdateCouponCompanyId extends Exception {
	
	
	public String getMessage() {
		return "You cannot update Coupon's company ID.";
	}
}
