package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.PO.LandingPage;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;
	
    
	public WebDriver initialiseDriver() throws IOException {
		Properties prop = new Properties();
		// C:\\Users\\Reneuit\\eclipse-workspace\\NewEclipse\\SeleniumAutomation
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\GlobalData.properties");
		prop.load(file);
		WebDriver driver = null;
		String browsername = prop.getProperty("browser");
		if (browsername.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browsername.equals("safari")) {
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
		}

		else if (browsername.equals("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}
	public String getScreenShot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts =(TakesScreenshot)driver;
		File src =ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png");
		file.getParentFile().mkdirs();
		FileUtils.copyFile(src, file);
		return System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		
	}
	
	public List<HashMap<String,String>> getJsonDatatoMap(String filePath) throws IOException
	{
		//Read json to String
		String jsonContent = FileUtils.readFileToString(new File (filePath),StandardCharsets.UTF_8);
		
		//String to HashMap jackson databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data=  mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
			return data;
	}
	
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException
	
	{
		driver =initialiseDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	@AfterMethod(alwaysRun = true)
public void tearDown()
{
	driver.quit();
}
}
