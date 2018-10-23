package myCouponsREST;

import java.time.LocalDate;

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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;

import com.sun.jersey.spi.container.ResourceFilters;

import main.Beans.Category;
import main.Beans.Company;
import main.Beans.Coupon;
import main.FacadeClasses.CompanyFacade;
import myCouponsREST.Filters.SessionFilter;
import myCouponsREST.MicroServiceProxy.BusinessDelegate;
import myCouponsREST.ProxyClasses.ProxyCoupon;
import myCouponsREST.Service.ErrorToJSONObjectService;
import myCouponsREST.Service.JSONService;

@Path("company")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ResourceFilters(SessionFilter.class)
public class CompanyService {
	
	@Context
	private HttpServletRequest request;
	
	@GET
	@Path("coupons")
	public Response getAllCoupons() {
		String json="";
		try {
			HttpSession session = request.getSession();
			CompanyFacade companyFacade = (CompanyFacade) session.getAttribute("companyFacade");
			ArrayList<Coupon> listedCoupons = companyFacade.getCompanyCoupons();
			json = JSONService.couponsToJSONArray(listedCoupons).toJSONString();
			return Response.ok(json).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			json = ErrorToJSONObjectService.convert(ex.getMessage());
			return Response.serverError().entity(json).build();
		}		
	}
	
	@GET
	@Path("coupons/{couponID}")
	public Response getCoupon(@PathParam("couponID")int couponID) {
		String json="";
		try {
			HttpSession session = request.getSession();
			CompanyFacade companyFacade = (CompanyFacade) session.getAttribute("companyFacade");
			ArrayList<Coupon> allCompanyCoupons = companyFacade.getCompanyCoupons();
			for(Coupon listedCoupon : allCompanyCoupons) {
				if(listedCoupon.getId() == couponID) {
					json = JSONService.couponToJSONObject(listedCoupon).toJSONString();
				}
			}
			return Response.ok(json).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			json = ErrorToJSONObjectService.convert(ex.getMessage());
			return Response.serverError().entity(json).build();
		}		
	}
	
	@GET
	@Path("coupons/sort/{category}")
	public Response getCouponsByCategory(@PathParam("category")String category) {
		String json="";
		try {
			HttpSession session = request.getSession();
			CompanyFacade companyFacade = (CompanyFacade) session.getAttribute("companyFacade");
			ArrayList<Coupon> allCompanyCoupons = companyFacade.getCompanyCoupons();
			Category formedCategory = Category.stringToCategory(category);
			ArrayList<Coupon> allCouponsWithCategoryChosen = new ArrayList<>();
			for(Coupon listedCoupon : allCompanyCoupons) {
				if(listedCoupon.getCategory() == formedCategory) {
					allCouponsWithCategoryChosen.add(listedCoupon);
				}
			}
			json = JSONService.couponsToJSONArray(allCouponsWithCategoryChosen).toJSONString();
			return Response.ok(json).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			json = ErrorToJSONObjectService.convert(ex.getMessage());
			return Response.serverError().entity(json).build();
		}		
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("coupons")
	public Response createCoupon(ProxyCoupon angularCoupon) {
		String json="";
		try {
			HttpSession session = request.getSession();
			CompanyFacade companyFacade = (CompanyFacade) session.getAttribute("companyFacade");
			Company companyLoggedIn = companyFacade.getCompanyDetailes();
			Coupon coupon = new Coupon(companyLoggedIn.getId(), Category.stringToCategory(angularCoupon.getCategory()), angularCoupon.getTitle(),
					angularCoupon.getDescription(), LocalDate.parse(angularCoupon.getStartDate()), LocalDate.parse(angularCoupon.getEndDate()),
					angularCoupon.getAmount(), angularCoupon.getPrice(), angularCoupon.getImage());
			companyFacade.addCoupon(coupon);
			BusinessDelegate businessDelegate = new BusinessDelegate();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("email", companyLoggedIn.getEmail());
			jsonObject.put("description", "COMPANY_NEW_COUPON");
			jsonObject.put("amount", 100);
			String jsonIncome = jsonObject.toJSONString();
			businessDelegate.storeIncome(jsonIncome);
			json = JSONService.couponToJSONObject(coupon).toJSONString();
			return Response.ok(json).status(HttpServletResponse.SC_CREATED).build();
		} catch(Exception ex) {
			json = ErrorToJSONObjectService.convert(ex.getMessage());
			return Response.serverError().entity(json).build();
		}	
	}
	
	@SuppressWarnings("unchecked")
	@PUT
	@Path("coupons/{couponID}")
	public Response updateCoupon(@PathParam("couponID")int couponID, ProxyCoupon angularCoupon) {
		String json = "";
		try {
			HttpSession session = request.getSession();
			CompanyFacade companyFacade = (CompanyFacade) session.getAttribute("companyFacade");
			Company companyLoggedIn = companyFacade.getCompanyDetailes();
			ArrayList<Coupon> listedCoupons = companyFacade.getCompanyCoupons();
			for(Coupon listedCoupon : listedCoupons) {
				if(listedCoupon.getId() == couponID) {
					listedCoupon.setCategory(Category.stringToCategory(angularCoupon.getCategory()));
					listedCoupon.setTitle(angularCoupon.getTitle());
					listedCoupon.setDescription(angularCoupon.getDescription());
					listedCoupon.setStartDate(LocalDate.parse(angularCoupon.getStartDate()));
					listedCoupon.setEndDate(LocalDate.parse(angularCoupon.getEndDate()));
					listedCoupon.setAmount(angularCoupon.getAmount());
					listedCoupon.setPrice(angularCoupon.getPrice());
					listedCoupon.setImage(angularCoupon.getImage());
					companyFacade.updateCoupon(listedCoupon);
					BusinessDelegate businessDelegate = new BusinessDelegate();
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("email", companyLoggedIn.getEmail());
					jsonObject.put("description", "COMPANY_UPDATE_COUPON");
					jsonObject.put("amount", 10);
					String jsonIncome = jsonObject.toJSONString();
					businessDelegate.storeIncome(jsonIncome);
					json = JSONService.couponToJSONObject(listedCoupon).toJSONString();
				}
			}
			return Response.ok(json).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			json = ErrorToJSONObjectService.convert(ex.getMessage());
			return Response.serverError().entity(json).build();
		}		
	}
	
	@DELETE
	@Path("coupons/{couponID}")
	public Response deleteCoupon(@PathParam("couponID")int couponID) {
		String json="";
		try {
			HttpSession session = request.getSession();
			CompanyFacade companyFacade = (CompanyFacade) session.getAttribute("companyFacade");
			companyFacade.deleteCoupon(couponID);
			return Response.noContent().status(HttpServletResponse.SC_NO_CONTENT).build();
		}
		catch(Exception ex) {
			json = ErrorToJSONObjectService.convert(ex.getMessage());
			return Response.serverError().entity(json).build();
		}		
	}
	
	@SuppressWarnings("unchecked")
	@GET
	@Path("incomes")
	public Response getAllIncomes() {
		String json = "";
		try {
			HttpSession session = request.getSession();
			CompanyFacade companyFacade = (CompanyFacade) session.getAttribute("companyFacade");
			Company companyLoggedIn = companyFacade.getCompanyDetailes();
			BusinessDelegate businessDelegate = new BusinessDelegate();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("email", companyLoggedIn.getEmail());
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
