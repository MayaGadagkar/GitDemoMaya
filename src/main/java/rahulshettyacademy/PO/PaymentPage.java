package rahulshettyacademy.PO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class PaymentPage extends AbstractComponent {

	WebDriver driver;

	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	By countryResults = By.cssSelector(".ta-results");

	@FindBy(css = ".form-group input")
	WebElement selectCountry;

	@FindBy(css = ".ta-item:last-of-type")
	WebElement dropDownCountry;

	@FindBy(css = ".actions a")
	WebElement placeOrderBttn;

	public ConfirmationPage AddDetailsAndPlaceOrder(String countryName) throws InterruptedException {
		Actions act = new Actions(driver);
		act.sendKeys(selectCountry, countryName).build().perform();
		WaitforElementToAppear(countryResults);
		dropDownCountry.click();
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", placeOrderBttn);
		Thread.sleep(2000);
		placeOrderBttn.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;

	}

}
