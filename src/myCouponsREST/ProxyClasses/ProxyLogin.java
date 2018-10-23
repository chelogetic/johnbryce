package myCouponsREST.ProxyClasses;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class ProxyLogin {
	private String email;
	private String password;
	private String clientType;
	public ProxyLogin() {}
	public ProxyLogin(String email, String password, String clientType) {
		setEmail(email);
		setPassword(password);
		setClientType(clientType);
	}
	public String getEmail() {
		return email;
	}
	@XmlElement
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	@XmlElement
	public void setPassword(String password) {
		this.password = password;
	}
	public String getClientType() {
		return clientType;
	}
	@XmlElement
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
}