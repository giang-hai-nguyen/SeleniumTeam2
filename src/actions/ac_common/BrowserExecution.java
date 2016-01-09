package ac_common;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

public class BrowserExecution {

	private static WebDriver driver = null;

	public static WebDriver getDriver() { 
		return driver; 
	}

	public static WebDriver navigateJoomla(String useBrowser) {
		driver = getBrowser(useBrowser);
		driver.get(config.Config.default_site);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	public static void closeJoomla()
	{
		driver.close();
		try {
			Thread.sleep(config.Config.short_wait_time);
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static WebDriver getBrowser(String useBrowser) {
		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		System.setProperty("webdriver.ie.driver", "drivers\\IEDriverServer.exe");

		switch (useBrowser) {
		case "chrome":
			killDriverProcess("chrome");
			driver = new ChromeDriver();
			break;
		case "ie":
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING,false);
			dc.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
			dc.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, true);
			dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true); 
			dc.setJavascriptEnabled(true); 
			killDriverProcess("ie");
			driver = new InternetExplorerDriver(dc); 
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		default:
			Reporter.log("Invalid browser");
			break;
		}
		return driver;
	}
	
	/**
	 * Kill Driver processes after running
	 * @param useBrowser
	 */
	public static void killDriverProcess(String useBrowser) {
		Process process = null;
		try {
			if (useBrowser == "ie") {
				process = Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
				process.destroy();
			}
			else if (useBrowser == "chrome") {
				process = Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
				
			}
			try {
				Thread.sleep(config.Config.short_wait_time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		process.destroy();
	}
}