package config;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Initialize {	
	
	public WebDriver openBrowser() {
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(Config.default_site);
        driver.manage().window().maximize();
        
        return driver;
	} 
public WebDriver closeBrowser() {
		
		driver.close();
		driver.quit();
        
        return driver;
	} 
	public WebDriver getDriver(WebDriver driver){
		//driver = this.driver;
		return driver;
	}

	WebDriver driver;
	protected WebElement element;

}
