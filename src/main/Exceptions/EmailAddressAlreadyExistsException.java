package main.Exceptions;


@SuppressWarnings("serial")
public class EmailAddressAlreadyExistsException extends Exception {

	public String getMessage() {
		return "Cannot add email address as it is already used.";
	}

}