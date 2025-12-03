package rahulshettyacademy.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
public class StandAlone {

	public static void main(String[] args) throws InterruptedException {
		String prodNeeded = "ZARA COAT 3";
		String expectedMsg = "Thankyou for the order.";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("Gadagkar@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Automation@1");
		driver.findElement(By.cssSelector("#login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.col-lg-4")));
		List<WebElement> allproducts = driver.findElements(By.cssSelector("div.col-lg-4"));

		WebElement desiredProd = allproducts.stream()
				.filter(a -> a.findElement(By.cssSelector("b")).getText().contains(prodNeeded)).findFirst()
				.orElse(null);
		desiredProd.findElement(By.cssSelector("div.card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		System.out.println(driver.findElement(By.id("toast-container")).getText());
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> validateCart = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = validateCart.stream()
				.anyMatch(validateCarts -> validateCarts.getText().equalsIgnoreCase(prodNeeded));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		WebElement country = driver.findElement(By.cssSelector(".form-group input"));
		Actions act = new Actions(driver);
		act.sendKeys(country, "India").build().perform();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-item:last-of-type")).click();

		WebElement element = driver.findElement(By.cssSelector(".actions a"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500); // slight delay before clicking
		element.click();
		String confirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase(expectedMsg));
		System.out.println("Your order is placed");
		driver.quit();

	}

}
