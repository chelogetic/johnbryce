package myCouponsREST.Filters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;
import myCouponsREST.Service.ErrorToJSONObjectService;

public class SessionFilter implements ResourceFilter, ContainerRequestFilter, ContainerResponseFilter {
	
	@Context
	private HttpServletRequest HttpServletRequest;
	
	public ContainerRequest filter(ContainerRequest request) {
		String json="";
		HttpSession session = HttpServletRequest.getSession(false);
		if(session == null) {
			json = ErrorToJSONObjectService.convert("Logging in required");
	        ResponseBuilder builder = Response.status(Response.Status.UNAUTHORIZED).entity(json);
	        throw new WebApplicationException(builder.build());
		}
		return request;
	}
	
	public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
		return response;
	}

	public ContainerRequestFilter getRequestFilter() {
		return this;
	}

	public ContainerResponseFilter getResponseFilter() {
		return this;
	}
}