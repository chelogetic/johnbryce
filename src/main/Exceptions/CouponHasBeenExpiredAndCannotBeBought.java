package main.Exceptions;

@SuppressWarnings("serial")
public class CouponHasBeenExpiredAndCannotBeBought  extends Exception{


	public String getMessage() {
		return "You cannot buy this coupon because it has been expired.";
	}
	
}
