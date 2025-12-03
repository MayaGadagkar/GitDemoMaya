package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.PO.CartPO;
import rahulshettyacademy.PO.OrdersPage;

public class AbstractComponent {
	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void WaitforElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void WaitforWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void WaitforElementtoDisappear(WebElement  ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	@FindBy(css ="[routerlink*='cart']")
	WebElement cartLink;
	
	@FindBy(css ="[routerlink*='myorders']")
	WebElement orderLink;
	

public CartPO goToCartPagee()
{
	cartLink.click();
	CartPO cart = new CartPO(driver);
	return cart;
}

public CartPO goToCartPage() {
    long startTime = System.currentTimeMillis();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    wait.until(ExpectedConditions.elementToBeClickable(cartLink));
    long endTime = System.currentTimeMillis();
    System.out.println("Time taken for cartLink to be clickable: " + (endTime - startTime) + "ms");
    cartLink.click();
    return new CartPO(driver);
}

public CartPO goToCartPageee() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    wait.until(ExpectedConditions.elementToBeClickable(cartLink));
    cartLink.click();
    CartPO cart = new CartPO(driver);
    return cart;
}
public OrdersPage clickOnOrders() 
{ 
	orderLink.click();
	OrdersPage ordersPage = new OrdersPage(driver);
	return ordersPage;
	
}
}
