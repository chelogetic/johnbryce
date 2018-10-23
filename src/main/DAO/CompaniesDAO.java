package main.DAO;
import java.util.ArrayList;

import main.Beans.Company;

public interface CompaniesDAO {
	
	boolean isCompanyExists(String email, String password) throws Exception;
	void addCompany(Company company) throws Exception;
	void updateCompany(Company company) throws Exception;
	void deleteCompany(int companyID) throws Exception;
	ArrayList<Company> getAllCompanies() throws Exception;
	Company getOneCompany(int companyID) throws Exception;
	int getCompanyIDByEmail(String email) throws Exception;
	boolean isCompanyIDExists(int id) throws Exception;
	boolean isCompanyNameExists(String name) throws Exception;
}