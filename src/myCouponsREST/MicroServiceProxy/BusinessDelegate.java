package myCouponsREST.MicroServiceProxy;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import myCouponsREST.Service.ErrorToJSONObjectService;

public class BusinessDelegate {
	
	private String SpringGETMicroServiceURL = "http://localhost:8888/incomes";
	private String SpringPOSTMicroServiceURL = "http://localhost:8888/incomes/sort";

	public synchronized Response storeIncome(String jsonIncome) {
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(SpringGETMicroServiceURL);
			ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class,jsonIncome);
			if (response.getStatus() >= 300) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			String output = response.getEntity(String.class);
			return Response.ok(output).status(HttpServletResponse.SC_CREATED).build();
		}
		catch(Exception ex) {
			String json = ErrorToJSONObjectService.convert(ex.getMessage());
			return Response.serverError().entity(json).build();
		}
	}
	
	public synchronized Response getAllIncomesByEmail(String jsonEmail) {
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(SpringPOSTMicroServiceURL);
			ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class,jsonEmail);
			if (response.getStatus() >= 300) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			String output = response.getEntity(String.class);
			return Response.ok(output).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			String json = ErrorToJSONObjectService.convert(ex.getMessage());
			return Response.serverError().entity(json).build();
		}
	}
	
	public synchronized Response getAllIncomes() {
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(SpringGETMicroServiceURL);
			ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
			if (response.getStatus() >= 300) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			String output = response.getEntity(String.class);
			return Response.ok(output).status(HttpServletResponse.SC_OK).build();
		}
		catch(Exception ex) {
			String json = ErrorToJSONObjectService.convert(ex.getMessage());
			return Response.serverError().entity(json).build();
		}
	}
}