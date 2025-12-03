package rahulshettyacademy.PO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement userEmailId;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(css = "#login")
	WebElement loginBttn;

	@FindBy(css = ".ng-trigger-flyInOut")
	WebElement erroMsg;

	public ProductCatalogue LoginApplication(String emailId, String password) {
		userEmailId.sendKeys(emailId);
		userPassword.sendKeys(password);
		loginBttn.click();
		ProductCatalogue prodCat = new ProductCatalogue(driver);
		return prodCat;
	}

	public String getErrorMsg() {
		
		WaitforWebElementToAppear(erroMsg);
		System.out.println(erroMsg.getText());
		return erroMsg.getText();
	 
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

}
