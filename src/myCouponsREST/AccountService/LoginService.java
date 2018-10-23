package myCouponsREST.AccountService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import main.FacadeClasses.AdminFacade;
import main.FacadeClasses.CompanyFacade;
import main.FacadeClasses.CustomerFacade;
import main.Login.ClientType;
import main.Login.LoginManager;
import myCouponsREST.ProxyClasses.ProxyLogin;
import myCouponsREST.Service.ErrorToJSONObjectService;
@Path("login")
public class LoginService {
	
	@Context
	private HttpServletRequest request;
	
	@POST
	public Response login(ProxyLogin login) {
		
		String json = "";
		
		try {
			
			LoginManager loginInstance = LoginManager.getInstance();
			String clientType = (login.getClientType()).toString();
			
			if(clientType.equals("ADMINISTRATOR")) {
				
				AdminFacade adminFacade = (AdminFacade) loginInstance.login(login.getEmail(), login.getPassword(),
						ClientType.valueOf(login.getClientType()));

				if(adminFacade == null) {
					json = ErrorToJSONObjectService.convert("Access Denied");
					return Response.serverError().entity(json).build();
				}
				
				HttpSession session = request.getSession();
				session.setAttribute("adminFacade", adminFacade);
				return Response.ok().build();
			}
			
			else if(clientType.equals("COMPANY")) {
				
				CompanyFacade companyFacade = (CompanyFacade) loginInstance.login(login.getEmail(),
						login.getPassword(), ClientType.valueOf(login.getClientType()));
				
				if(companyFacade == null) {
					json = ErrorToJSONObjectService.convert("Access Denied");
					return Response.serverError().entity(json).build();
				}
				
				HttpSession session = request.getSession();
				session.setAttribute("companyFacade", companyFacade);
				return Response.ok().build();
			}
			
			else if(clientType.equals("CUSTOMER")) {
				
				CustomerFacade customerFacade = (CustomerFacade) loginInstance.login(login.getEmail(),
						login.getPassword(), ClientType.valueOf(login.getClientType()));
				
				if(customerFacade == null) {
					json = ErrorToJSONObjectService.convert("Access Denied");
					return Response.serverError().entity(json).build();
				}
				
				HttpSession session = request.getSession();
				session.setAttribute("customerFacade", customerFacade);
				return Response.ok().build();
			}
			else {
				json = ErrorToJSONObjectService.convert("Something's wrong with your input");
				return Response.serverError().entity(json).build();
			}

		}catch(Exception ex) {
			json = ErrorToJSONObjectService.convert(ex.getMessage());
			return Response.serverError().entity(json).build();
		}
	}
}