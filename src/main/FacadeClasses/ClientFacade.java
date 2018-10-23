package main.FacadeClasses;

import main.DAO.CompaniesDAO;
import main.DAO.CouponsDAO;
import main.DAO.CustomersDAO;

public abstract class ClientFacade {
	
	protected CompaniesDAO companiesDAO;
	protected CustomersDAO customersDAO;
	protected CouponsDAO couponsDAO;
	
}