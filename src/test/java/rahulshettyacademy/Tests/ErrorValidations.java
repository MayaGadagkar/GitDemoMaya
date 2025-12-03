package rahulshettyacademy.Tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import rahulshettyacademy.PO.CartPO;
import rahulshettyacademy.PO.ProductCatalogue;
import rahulshettyacademy.TestComponents.BaseTest;

public class ErrorValidations extends BaseTest {
	@Test(groups = { "ErrorValidations" },retryAnalyzer =Retry.class)
	public void NegativeTest() {

		ProductCatalogue prodCat = landingPage.LoginApplication("Gadagkar@gmail.com", "Automation@01");
		Assert.assertEquals("Incorrect email password.", landingPage.getErrorMsg());

	}

	@Test
	public void submitOrderInvalidItem() throws InterruptedException, IOException {
		String productName = "ZARA COAT 33";
		// String expectedMsg = "Thankyou for the order.";
		ProductCatalogue prodCat = landingPage.LoginApplication("Gadagkar@gmail.com", "Automation@1");
		// List<WebElement> products =
		prodCat.getProdList();
		prodCat.addProdToCart(productName);
		CartPO cart = prodCat.goToCartPage();
		Boolean match = cart.ValidateCartIftheProductexists(productName);
		Assert.assertTrue(match);
	}

}
