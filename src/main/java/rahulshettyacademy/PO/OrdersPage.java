package rahulshettyacademy.PO;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {

	WebDriver driver;
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy (css="tr td:nth-child(3)")
	List<WebElement> orderList;
	
		public Boolean getOrderHistory(String productName) {
		Boolean  match = orderList.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
		}
	
	

}
