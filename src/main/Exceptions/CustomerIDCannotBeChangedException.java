package main.Exceptions;

@SuppressWarnings("serial")
public class CustomerIDCannotBeChangedException extends Exception {

	public String getMessage() {
		return "CustomerID cannot be changed.";
	}
	
}