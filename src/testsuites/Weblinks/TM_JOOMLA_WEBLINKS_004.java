package Weblinks;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ac_common.*;
import ac_pages.*;
import config.*;

public class TM_JOOMLA_WEBLINKS_004 extends ac_WeblinksPage{
	
	@BeforeClass
	@Parameters({ "browser" })
	public void Setup(@Optional("firefox") String browser) {
		driver = BrowserExecution.navigateJoomla(browser);
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}

	@Test(description = "Verify user can add image to weblink's description")
	public void TC_JOOMLA_WEBLINKS_013()
	{
		WeblinksPage = new ac_WeblinksPage(driver);
		WeblinksPage.navigatemenu(driver, "Components", "Weblinks", null);
		WeblinksPage.clickToolbarButton(driver, "new");
		WeblinksPage.fillInfoWeblinks (weblinks_title, weblinks_url, null, "powered_by.png");
		WeblinksPage.clickToolbarButton(driver, "save");
		
		verifyTrue(WeblinksPage.doesTextPresent(driver, message_create));
		verifyTrue(WeblinksPage.doesitemExist(driver, weblinks_title));
		
	}	
	
	@AfterClass
	public void teardown(){
		AdminPage = new ac_AdministratorPage(driver);
		AdminPage.Logout();		
		BrowserExecution.closeJoomla();
	}
	
	private WebDriver driver;
	private ac_LoginPage LoginPage;	
	private ac_AdministratorPage AdminPage;
	private ac_WeblinksPage WeblinksPage;
	
	private String weblinks_title = randUniqueString("Test Weblink");
	private String weblinks_url = "http://www.joomla.org";
	private String message_create = "Web link successfully saved";
	
}