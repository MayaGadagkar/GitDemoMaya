package rahulshettyacademy.PO;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div.col-lg-4")
	List<WebElement> products;

	By productsBy = By.cssSelector("div.col-lg-4");
	By addprodsToCart = By.cssSelector("div.card-body button:last-of-type");
	By toastMsg = By.id("toast-container");
	@FindBy (css =".ng-animating")
	WebElement spinner;
	
	

	public List<WebElement> getProdList() {
		WaitforElementToAppear(productsBy);
		return products;
	}

	/*
	 * public WebElement getProductsByName(String productName) { WebElement prod =
	 * products.stream() .filter(a ->
	 * a.findElement(By.cssSelector("b")).getText().contains(productName)).findFirst
	 * () .orElse(null); return prod; }
	 */
	
	public WebElement getProductsByName(String productName) {
	    // Wait for products to be visible (optional but recommended)
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfAllElements(products));

	    WebElement prod = products.stream()
	            .filter(a -> {
	                try {
	                    String text = a.findElement(By.cssSelector("b")).getText().trim();
	                    return text.equalsIgnoreCase(productName.trim());
	                } catch (NoSuchElementException e) {
	                    return false;
	                }
	            })
	            .findFirst()
	            .orElseThrow(() -> new RuntimeException("Product not found: " + productName));

	    return prod;
	}


	/*
	 * public void addProdToCart(String productName) { WebElement prod =
	 * getProductsByName(productName); prod.findElement(addprodsToCart).click();
	 * WaitforElementToAppear(toastMsg); WaitforElementtoDisappear(spinner);
	 * 
	 * 
	 * }
	 */
	
	public void addProdToCarte(String productName) {
	    WebElement prod;
	    try {
	        prod = getProductsByName(productName); // this now throws RuntimeException if product not found
	    } catch (RuntimeException e) {
	        throw new RuntimeException("Cannot add product to cart. Product not found: " + productName);
	    }

	    // Click "Add to Cart"
	    prod.findElement(addprodsToCart).click();

	    // Wait for toast message and spinner
	    WaitforElementToAppear(toastMsg);
	    WaitforElementtoDisappear(spinner);
	}
	
	
	public void addProdToCart(String productName) {
	    long startTime = System.currentTimeMillis();

	    WebElement prod;
	    try {
	        prod = getProductsByName(productName);
	    } catch (RuntimeException e) {
	        throw new RuntimeException("Cannot add product to cart. Product not found: " + productName);
	    }

	    long productSearchTime = System.currentTimeMillis();
	    System.out.println("Time taken to find product: " + (productSearchTime - startTime) + "ms");

	    prod.findElement(addprodsToCart).click();

	    long clickTime = System.currentTimeMillis();
	    System.out.println("Time taken to click 'Add to Cart': " + (clickTime - productSearchTime) + "ms");

	    WaitforElementToAppear(toastMsg);
	   WaitforElementtoDisappear(spinner);

	    long endTime = System.currentTimeMillis();
	    System.out.println("Time taken for toast and spinner: " + (endTime - clickTime) + "ms");
	    System.out.println("Total time for addProdToCart: " + (endTime - startTime) + "ms");
	}

	



}
