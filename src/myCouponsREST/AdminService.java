package myCouponsREST;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.spi.container.ResourceFilters;

import myCouponsREST.Filters.SessionFilter;
import myCouponsREST.MicroServiceProxy.BusinessDelegate;
import myCouponsREST.Service.ErrorToJSONObjectService;
import myCouponsREST.Service.JSONService;
import main.Beans.Company;
import main.Beans.Customer;
import main.FacadeClasses.AdminFacade;

@Path("admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ResourceFilters(SessionFilter.class)
public class AdminService {
	
	@Context
	private HttpServletRequest request;
	
	@GET
	@Path("companies")
	public Response getAllCompanies() {
		String json = "";
		try {
			HttpSession session = request.getSession();
			AdminFacade adminFacade = (AdminFacade) session.getAttribute("adminFacade");
			ArrayList<Company> listedCompanies = adminFacade.getAllCompanies();
			json = JSONService.companiesToJSONArray(listedCompanies).toJSONString();
			return Response.ok(json).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			json = ErrorToJSONObjectService.convert(ex.getMessage());
			return Response.serverError().entity(json).build();
		}		
	}
	
	@GET
	@Path("companies/{companyID}")
	public Response getCompany(@PathParam("companyID")int companyID) {
		String json="";
		try {
			HttpSession session = request.getSession();
			AdminFacade adminFacade = (AdminFacade) session.getAttribute("adminFacade");
			Company listedCompany = adminFacade.getOneCompany(companyID);
			json = JSONService.companyToJSONObject(listedCompany).toJSONString();
			return Response.ok(json).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			json = ErrorToJSONObjectService.convert(ex.getMessage());
			return Response.serverError().entity(json).build();
		}		
	}
	
	@POST
	@Path("companies")
	public Response createCompany(Company desiredCompany) {
		String json="";
		try {
			HttpSession session = request.getSession();
			AdminFacade adminFacade = (AdminFacade) session.getAttribute("adminFacade");
			Company company = new Company(desiredCompany.getName(),desiredCompany.getEmail(),desiredCompany.getPassword(),null);
			adminFacade.addCompany(company);
			json = JSONService.companyToJSONObject(company).toJSONString();
			return Response.ok(json).status(HttpServletResponse.SC_CREATED).build();
		}
		catch(Exception ex) {
			json = ErrorToJSONObjectService.convert(ex.getMessage());
			return Response.serverError().entity(json).build();
		}		
	}
	
	@PUT
	@Path("companies/{companyID}")
	public Response updateCompany(@PathParam("companyID")int companyID, Company desiredCompany) {
		String json = "";
		try {
			HttpSession session = request.getSession();
			AdminFacade adminFacade = (AdminFacade) session.getAttribute("adminFacade");
			Company listedCompany = adminFacade.getOneCompany(companyID);
			listedCompany.setEmail(desiredCompany.getEmail());
			listedCompany.setPassword(desiredCompany.getPassword());
			adminFacade.updateCompany(listedCompany);
			json = JSONService.companyToJSONObject(listedCompany).toJSONString();
			return Response.ok(json).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			json = ErrorToJSONObjectService.convert(ex.getMessage());
			return Response.serverError().entity(json).build();
		}		
	}
	
	@DELETE
	@Path("companies/{companyID}")
	public Response deleteCompany(@PathParam("companyID")int companyID) {
		String json="";
		try {
			HttpSession session = request.getSession();
			AdminFacade adminFacade = (AdminFacade) session.getAttribute("adminFacade");
			adminFacade.deleteCompany(companyID);
			return Response.noContent().status(HttpServletResponse.SC_NO_CONTENT).build();
		}
		catch(Exception ex) {
			json = ErrorToJSONObjectService.convert(ex.getMessage());
			return Response.serverError().entity(json).build();
		}		
	}
	
	@GET
	@Path("customers")
	public Response getAllCustomers() {
		String json = "";
		try {
			HttpSession session = request.getSession();
			AdminFacade adminFacade = (AdminFacade) session.getAttribute("adminFacade");
			ArrayList<Customer> listedCustomers = adminFacade.getAllCustomers();
			json = JSONService.customersToJSONArray(listedCustomers).toJSONString();
			return Response.ok(json).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			json = ErrorToJSONObjectService.convert(ex.getMessage());
			return Response.serverError().entity(json).build();
		}		
	}
	
	@GET
	@Path("customers/{customerID}")
	public Response getCustomer(@PathParam("customerID")int customerID) {
		String json="";
		try {
			HttpSession session = request.getSession();
			AdminFacade adminFacade = (AdminFacade) session.getAttribute("adminFacade");
			Customer listedCustomer = adminFacade.getOneCustomer(customerID);
			json = JSONService.customerToJSONObject(listedCustomer).toJSONString();
			return Response.ok(json).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			json = ErrorToJSONObjectService.convert(ex.getMessage());
			return Response.serverError().entity(json).build();
		}		
	}
	
	@POST
	@Path("customers")
	public Response createCustomer(Customer desiredCustomer) {
		String json="";
		try {
			HttpSession session = request.getSession();
			AdminFacade adminFacade = (AdminFacade) session.getAttribute("adminFacade");
			Customer customer = new Customer(desiredCustomer.getFirstName(),desiredCustomer.getLastName(),
					desiredCustomer.getEmail(),desiredCustomer.getPassword(),null);
			adminFacade.addCustomer(customer);
			json = JSONService.customerToJSONObject(customer).toJSONString();
			return Response.ok(json).status(HttpServletResponse.SC_CREATED).build();
		}
		catch(Exception ex) {
			json = ErrorToJSONObjectService.convert(ex.getMessage());
			return Response.serverError().entity(json).build();
		}		
	}
	
	@PUT
	@Path("customers/{customerID}")
	public Response updateCustomer(@PathParam("customerID")int customerID, Customer desiredCustomer) {
		String json = "";
		try {
			HttpSession session = request.getSession();
			AdminFacade adminFacade = (AdminFacade) session.getAttribute("adminFacade");
			Customer listedCustomer = adminFacade.getOneCustomer(customerID);
			listedCustomer.setFirstName(desiredCustomer.getFirstName());
			listedCustomer.setLastName(desiredCustomer.getLastName());
			listedCustomer.setEmail(desiredCustomer.getEmail());
			listedCustomer.setPassword(desiredCustomer.getPassword());
			adminFacade.updateCustomer(listedCustomer);
			json = JSONService.customerToJSONObject(listedCustomer).toJSONString();
			return Response.ok(json).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			json = ErrorToJSONObjectService.convert(ex.getMessage());
			return Response.serverError().entity(json).build();
		}		
	}
	
	@DELETE
	@Path("customers/{customerID}")
	public Response deleteCustomer(@PathParam("customerID")int customerID) {
		String json="";
		try {
			HttpSession session = request.getSession();
			AdminFacade adminFacade = (AdminFacade) session.getAttribute("adminFacade");
			adminFacade.deleteCustomer(customerID);
			return Response.noContent().status(HttpServletResponse.SC_NO_CONTENT).build();
		}
		catch(Exception ex) {
			json = ErrorToJSONObjectService.convert(ex.getMessage());
			return Response.serverError().entity(json).build();
		}		
	}
	
	@GET
	@Path("incomes")
	public Response getAllIncomes() {
		String json = "";
		try {
			BusinessDelegate businessDelegate = new BusinessDelegate();
			Response resp = businessDelegate.getAllIncomes();
			json = resp.getEntity().toString();
			return Response.ok(json).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			json = ErrorToJSONObjectService.convert(ex.getMessage());
			return Response.serverError().entity(json).build();
		}		
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("incomes")
	public Response getIncomeByEmail(Customer customer) {
		String json = "";
		try {
			BusinessDelegate businessDelegate = new BusinessDelegate();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("email", customer.getEmail());
			String jsonEmail = jsonObject.toJSONString();
			Response resp = businessDelegate.getAllIncomesByEmail(jsonEmail);
			json = resp.getEntity().toString();
			return Response.ok(json).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			json = ErrorToJSONObjectService.convert(ex.getMessage());
			return Response.serverError().entity(json).build();
		}		
	}
}
