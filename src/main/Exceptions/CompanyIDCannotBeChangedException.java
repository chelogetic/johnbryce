package main.Exceptions;

@SuppressWarnings("serial")
public class CompanyIDCannotBeChangedException extends Exception {

	public String getMessage() {
		return "CompanyID cannot be changed.";
	}
	
}