package main.FacadeClasses;

import java.util.ArrayList;

import main.Beans.Company;
import main.Beans.Customer;
import main.DAO.CompaniesDBDAO;
import main.DAO.CustomersDBDAO;
import main.Exceptions.CompanyEmailAlreadyExistsException;
import main.Exceptions.CompanyNameAlreadyExistsException;
import main.Exceptions.CompanyNameCannotBeChangedException;
import main.Exceptions.CustomerIDCannotBeChangedException;
import main.Exceptions.EmailAddressAlreadyExistsException;

public class AdminFacade extends ClientFacade{

	public AdminFacade() {
		companiesDAO = new CompaniesDBDAO();
		customersDAO = new CustomersDBDAO();
	}

	public  boolean login(String email, String password) {
		return email.equals("admin@admin.com") && password.equals("admin");		
	}

	public void addCompany(Company company) throws Exception {
		ArrayList<Company> allCompanies = companiesDAO.getAllCompanies();
		ArrayList<Customer> allCustomers = customersDAO.getAllCustomers();
		for (Company listedCompany : allCompanies) {
			if(listedCompany.getName().equals(company.getName())) {
				throw new CompanyNameAlreadyExistsException();
			}
			if(listedCompany.getEmail().equals(company.getEmail())) {
				throw new EmailAddressAlreadyExistsException();
			}
		}
		for(Customer listedCustomer : allCustomers) {
			if(listedCustomer.getEmail().equals(company.getEmail())) {
				throw new CompanyEmailAlreadyExistsException();
			}
		}
		companiesDAO.addCompany(company);
	}

	public void updateCompany(Company company) throws Exception {

		Company listedCompany = companiesDAO.getOneCompany(company.getId());

		if(!company.getName().equals(listedCompany.getName())) {

			throw new CompanyNameCannotBeChangedException();
		}
		companiesDAO.updateCompany(company);

	}

	public void deleteCompany(int companyID) throws Exception {
		companiesDAO.deleteCompany(companyID);
	}

	public ArrayList<Company> getAllCompanies() throws Exception {
		return companiesDAO.getAllCompanies();
	}

	public Company getOneCompany(int companyID) throws Exception {
		return companiesDAO.getOneCompany(companyID);
	}

	public void addCustomer(Customer customer) throws Exception {
		ArrayList<Customer> allCustomers = customersDAO.getAllCustomers();
		ArrayList<Company> allCompanies = companiesDAO.getAllCompanies();
		for (Customer listedCustomer : allCustomers) {
			if(listedCustomer.getEmail().equals(customer.getEmail())){
				throw new EmailAddressAlreadyExistsException();
			}
		}
		for(Company listedCompany : allCompanies) {
			if(listedCompany.getEmail().equals(customer.getEmail())){
				throw new EmailAddressAlreadyExistsException();
			}
		}
		customersDAO.addCustomer(customer);
	}

	public void updateCustomer(Customer customer) throws Exception {

		Customer listedCustomer = customersDAO.getOneCustomer(customer.getId());

		if(customer.getId() != listedCustomer.getId()){
			throw new CustomerIDCannotBeChangedException();
		}
		customersDAO.updateCustomer(customer);
	}

	public void deleteCustomer(int customerID) throws Exception {
		customersDAO.deleteCustomer(customerID);
	}

	public ArrayList<Customer> getAllCustomers() throws Exception {
		return customersDAO.getAllCustomers();
	}

	public Customer getOneCustomer(int customerID) throws Exception {
		return customersDAO.getOneCustomer(customerID);
	}
}