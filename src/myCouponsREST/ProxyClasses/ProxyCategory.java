package myCouponsREST.ProxyClasses;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class ProxyCategory {
	private String category;
	public ProxyCategory() {}
	public ProxyCategory(String category) {
		setCategory(category);
	}
	public String getCategory() {
		return category;
	}
	@XmlElement
	public void setCategory(String category) {
		this.category = category;
	}
}