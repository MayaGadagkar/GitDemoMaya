package rahulshettyacademy.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.PO.CartPO;
import rahulshettyacademy.PO.ConfirmationPage;
import rahulshettyacademy.PO.OrdersPage;
import rahulshettyacademy.PO.PaymentPage;
import rahulshettyacademy.PO.ProductCatalogue;
import rahulshettyacademy.TestComponents.BaseTest;

public class SubmitOrderForm extends BaseTest {
	//String productName = "ZARA COAT 3";
	@Test (dataProvider ="getData", groups = "Purchase" )
	public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException {
		
		String expectedMsg = "Thankyou for the order.";
		ProductCatalogue prodCat = landingPage.LoginApplication(input.get("emailId"),input.get("password"));
		// List<WebElement> products =
		prodCat.getProdList();
		prodCat.addProdToCart(input.get("productName"));
		CartPO cart = prodCat.goToCartPage();
		Boolean match = cart.ValidateCartIftheProductexists(input.get("productName"));
		Assert.assertTrue(match);
		PaymentPage paymentPage = cart.clickCheckout();
		ConfirmationPage confirmationPage = paymentPage.AddDetailsAndPlaceOrder("India");
		String msgConfirmation = confirmationPage.getConfirmation();
		Assert.assertTrue(msgConfirmation.equalsIgnoreCase(expectedMsg));
		System.out.println("Your order is placed successfully !!!");
		

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistory() {
		String productName = "ZARA COAT 3";
		ProductCatalogue prodCat = landingPage.LoginApplication("Gadagkar@gmail.com", "Automation@1");
		OrdersPage ordersPage = prodCat.clickOnOrders();
		Assert.assertTrue(ordersPage.getOrderHistory(productName));

	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	
	{ 
		/*
		 * HashMap<String,String> map= new HashMap<String,String>();
		 * map.put("emailId","Gadagkar@gmail.com"); map.put("password", "Automation@1");
		 * map.put("productName","ZARA COAT 3"); HashMap<String,String> map1= new
		 * HashMap<String,String>(); map1.put("emailId","ridhisha@gmail.com");
		 * map1.put("password", "Ridhisha@1");
		 * map1.put("productName","ADIDAS ORIGINAL");
		 */
		
		//C:\Users\Reneuit\eclipse-workspace\NewEclipse\SeleniumAutomation\src\test\java\rahulshettyacademy\TestData\PurchaseOrder.json
		List<HashMap<String,String>> data= getJsonDatatoMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\TestData\\PurchaseOrder.json");
		return new Object[][] { {data.get(0)},{data.get(1)}};
		
		//DataProvider
		/*
		 * public object[][] getData() { return Object[][]
		 * {{"Gadagkar@gmail.com","Automation@1","ZARA COAT 3"},{"ridhisha@gmail.com",
		 * "Ridhisha@1","ADIDAS ORIGINAL"} };
		 */
		
	}
	
	
}
