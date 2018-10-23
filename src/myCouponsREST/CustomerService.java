package myCouponsREST;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;
import com.sun.jersey.spi.container.ResourceFilters;
import main.FacadeClasses.CustomerFacade;
import myCouponsREST.Filters.SessionFilter;
import myCouponsREST.MicroServiceProxy.BusinessDelegate;
import myCouponsREST.Service.ErrorToJSONObjectService;
import myCouponsREST.Service.JSONService;
import main.Beans.Category;
import main.Beans.Coupon;
import main.Beans.Customer;

@Path("customer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ResourceFilters(SessionFilter.class)
@SuppressWarnings("unchecked")
public class CustomerService {

	@Context
	private HttpServletRequest request;

	@GET
	@Path("coupons")
	public Response getAllPurchasedCoupons() {
		String json = "";
		try {
			HttpSession session = request.getSession();
			CustomerFacade customerFacade = (CustomerFacade) session.getAttribute("customerFacade");
			ArrayList<Coupon> listedCoupons = customerFacade.getCustomerCoupons();
			json = JSONService.couponsToJSONArray(listedCoupons).toJSONString();
			return Response.ok(json).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			json = ErrorToJSONObjectService.convert(ex.getMessage());
			return Response.serverError().entity(json).build();
		}		
	}

	@GET
	@Path("coupons/sort/{category}")
	public Response getAllPurchasedCouponsByCategory(@PathParam("category") String category) {
		String json = "";
		try {
			HttpSession session = request.getSession();
			CustomerFacade customerFacade = (CustomerFacade) session.getAttribute("customerFacade");
			ArrayList<Coupon> listedCoupons = customerFacade.getCustomerCoupons();
			ArrayList<Coupon> listedCouponsByCategory = new ArrayList<>();

			for(Coupon listedCoupon : listedCoupons) {
				if(listedCoupon.getCategory().equals(Category.stringToCategory(category))) {
					listedCouponsByCategory.add(listedCoupon);
				}
			}
			json = JSONService.couponsToJSONArray(listedCouponsByCategory).toJSONString();
			return Response.ok(json).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			json = ErrorToJSONObjectService.convert(ex.getMessage());
			return Response.serverError().entity(json).build();
		}		
	}

	@GET
	@Path("coupons/{price}")
	public Response getAllPurchasedCouponsByHighestPrice(@PathParam("price") double price) {
		String json = "";
		try {
			HttpSession session = request.getSession();
			CustomerFacade customerFacade = (CustomerFacade) session.getAttribute("customerFacade");
			ArrayList<Coupon> listedCoupons = customerFacade.getCustomerCoupons();
			ArrayList<Coupon> listedCouponsByPrice = new ArrayList<>();

			for(Coupon listedCoupon : listedCoupons) {
				if(listedCoupon.getPrice() <= price) {
					listedCouponsByPrice.add(listedCoupon);
				}
			}
			json = JSONService.couponsToJSONArray(listedCouponsByPrice).toJSONString();
			return Response.ok(json).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			json = ErrorToJSONObjectService.convert(ex.getMessage());
			return Response.serverError().entity(json).build();
		}		
	}

	@GET
	@Path("coupons/purchase/{couponID}")
	public Response purchaseCoupon(@PathParam("couponID")int couponID) {
		String json="";
		try {
			HttpSession session = request.getSession();
			CustomerFacade customerFacade = (CustomerFacade) session.getAttribute("customerFacade");
			Coupon coupon = new Coupon();
			coupon.setId(couponID);
			customerFacade.purchaseCoupon(coupon);
			BusinessDelegate businessDelegate = new BusinessDelegate();
			Customer customer = customerFacade.getCustomerDetailes();
			ArrayList<Coupon> allPurchasedCoupons = customerFacade.getCustomerCoupons();
			for(Coupon listedCoupon : allPurchasedCoupons) {
				if(listedCoupon.getId() == couponID) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("email", customer.getEmail());
					jsonObject.put("description", "CUSTOMER_PURCHASE");
					jsonObject.put("amount", listedCoupon.getPrice());
					String jsonIncome = jsonObject.toJSONString();
					businessDelegate.storeIncome(jsonIncome);
					json = JSONService.couponToJSONObject(listedCoupon).toJSONString();
				}
			}
			return Response.ok(json).status(HttpServletResponse.SC_CREATED).build();

		} catch(Exception ex) {
			json = ErrorToJSONObjectService.convert(ex.getMessage());
			return Response.serverError().entity(json).build();
		}	
	}
}