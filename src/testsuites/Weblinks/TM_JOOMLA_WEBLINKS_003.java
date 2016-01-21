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

public class TM_JOOMLA_WEBLINKS_003 extends ac_WeblinksPage{
	
	@BeforeClass
	@Parameters({ "browser" })
	public void Setup(@Optional("firefox") String browser) {
		driver = BrowserExecution.navigateJoomla(browser);
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}

	@Test(description = "Verify user can check-in a weblink", groups = "regression")
	public void TC_JOOMLA_WEBLINKS_010()
	{
		WeblinksPage = new ac_WeblinksPage(driver);
		WeblinksPage.navigatemenu(driver, "Components", "Weblinks", null);
		WeblinksPage.clickToolbarButton(driver, "new");
		WeblinksPage.fillInfoWeblinks (weblinks_title, weblinks_url, null,null, null);
		WeblinksPage.clickToolbarButton(driver, "save");
		
		verifyTrue(WeblinksPage.doesTextPresent(driver, message_create));
		verifyTrue(WeblinksPage.doesitemExist(driver, weblinks_title));
		
		WeblinksPage.selectCheckboxItemWithoutDiv(driver, weblinks_title);
		WeblinksPage.clickToolbarButton(driver, "checkin");
		
		verifyTrue(doesTextPresent(driver, message_checkin));
		
		BrowserExecution.closeJoomla();
		
		driver = openBrowser();
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
		WeblinksPage.navigatemenu(driver, "Components", "Weblinks", null);
		
		WeblinksPage.searchItem(driver, weblinks_title);
		verifyTrue(WeblinksPage.verifyCheckInStateBanner(weblinks_title, "locked"));
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
	private String message_checkin = "1 web link successfully checked in";
	
}