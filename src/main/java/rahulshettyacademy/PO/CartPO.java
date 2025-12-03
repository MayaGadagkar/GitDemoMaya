package rahulshettyacademy.PO;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class CartPO extends AbstractComponent {

	WebDriver driver;

	public CartPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".totalRow button")
	WebElement checkoutBttn;

	@FindBy(css = ".cartSection h3")
	List<WebElement> validateCart;

	public Boolean ValidateCartIftheProductexists(String productName) throws InterruptedException {
		Thread.sleep(2000);
		Boolean match = validateCart.stream()
				.anyMatch(validateCarts -> validateCarts.getText().equalsIgnoreCase(productName));
		return match;
	}

	public PaymentPage clickCheckout() {
		checkoutBttn.click();
		PaymentPage paymentPage = new PaymentPage(driver);
		return paymentPage;
	}

}
