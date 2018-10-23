package myCouponsREST.AccountService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
@Path("logout")
public class LogoutService {
	@Context
	private HttpServletRequest request;
	@GET
	public Response loggout() throws ServletException {
		HttpSession session = request.getSession();
		session.invalidate();
		return Response.noContent().status(HttpServletResponse.SC_NO_CONTENT).build();
	}
}