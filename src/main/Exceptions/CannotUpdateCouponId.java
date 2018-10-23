package main.Exceptions;

@SuppressWarnings("serial")
public class CannotUpdateCouponId extends Exception {
	
	
	public String getMessage() {
		return "You cannot update Coupon's ID.";
	}
}
