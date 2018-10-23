package main.Exceptions;

@SuppressWarnings("serial")
public class CompanyNameAlreadyExistsException extends Exception {

	public String getMessage() {
		return "Company name already exists.";
	}
	
}