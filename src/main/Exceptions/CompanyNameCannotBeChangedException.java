package main.Exceptions;


@SuppressWarnings("serial")
public class CompanyNameCannotBeChangedException extends Exception {

	public String getMessage() {
		return "Company name cannot be changed.";
	}

}