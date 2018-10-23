package main.Exceptions;

@SuppressWarnings("serial")
public class OutOfCouponsException extends Exception {

	public String getMessage() {
		return "There are no coupons left for you to buy.";
	}
	
}