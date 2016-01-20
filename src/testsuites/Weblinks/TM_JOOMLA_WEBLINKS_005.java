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

public class TM_JOOMLA_WEBLINKS_005 extends ac_WeblinksPage{
	
	@BeforeClass
	@Parameters({ "browser" })
	public void Setup(@Optional("firefox") String browser) {
		driver = BrowserExecution.navigateJoomla(browser);
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}

	@Test(description = "Verify user can create a copied version of an existed weblink", groups={"regression"})
	public void TC_JOOMLA_WEBLINKS_016()
	{
		WeblinksPage = new ac_WeblinksPage(driver);
		WeblinksPage.navigatemenu(driver, "Components", "Weblinks", null);
		WeblinksPage.clickToolbarButton(driver, "new");
		WeblinksPage.fillInfoWeblinks (weblinks_title1, weblinks_url1, null, null, null);
		WeblinksPage.clickToolbarButton(driver, "save");
		verifyTrue(WeblinksPage.doesTextPresent(driver, message_create));
		verifyTrue(WeblinksPage.doesitemExist(driver, weblinks_title1));
		
		WeblinksPage.selectCheckboxItemWithoutDiv(driver, weblinks_title1);
		WeblinksPage.clickToolbarButton(driver, "edit");
		WeblinksPage.fillInfoWeblinks (weblinks_title2, weblinks_url2, null, null, null);
		WeblinksPage.clickToolbarButton(driver, "save-copy");
		verifyTrue(WeblinksPage.doesTextPresent(driver, message_create));
		WeblinksPage.clickToolbarButton(driver, "cancel");
		verifyTrue(WeblinksPage.doesitemExist(driver, weblinks_title2 + " (2)"));
		
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
	
	private String weblinks_title1 = randUniqueString("Test Weblink");
	private String weblinks_url1 = "http://www.joomla.org";
	private String message_create = "Web link successfully saved";
	private String weblinks_title2 = randUniqueString("Test Weblink");
	private String weblinks_url2 = "http://www.google.com";
}