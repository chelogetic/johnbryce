package myCouponsREST.Service;

import org.json.simple.JSONObject;

public class ErrorToJSONObjectService { // Service made to save code space
	
	@SuppressWarnings("unchecked")
	public static String convert(String exceptionMessage) {
		JSONObject jsonError = new JSONObject();
		jsonError.put("Error", exceptionMessage);
		return jsonError.toJSONString();
	}
}
