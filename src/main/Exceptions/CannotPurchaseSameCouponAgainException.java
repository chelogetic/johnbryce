package main.Exceptions;

@SuppressWarnings("serial")
public class CannotPurchaseSameCouponAgainException  extends Exception{


	public String getMessage() {
		return "You can't purchase the same coupon again.";
	}
	
}
