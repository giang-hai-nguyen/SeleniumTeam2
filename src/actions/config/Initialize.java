package config;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
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
	
	public WebDriver getDriver(WebDriver driver){
		//driver = this.driver;
		return driver;
	}

	public WebElement findAnElement (WebDriver driver, String control){
		if (control.contains("byID")) {
			element = driver.findElement(By.id(control));
		}
		else if (control.contains("bytagName")){
			element = driver.findElement(By.tagName(control));
		} else
			element = driver.findElement(By.xpath(control));
		return element;
	}

	WebDriver driver;
	protected WebElement element;

}
