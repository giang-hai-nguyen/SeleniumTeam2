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
import in_pages.in_WeblinksPage;

public class TM_JOOMLA_WEBLINKS_006 extends ac_WeblinksPage{
	
	@BeforeClass
	@Parameters({ "browser" })
	public void Setup(@Optional("firefox") String browser) {
		driver = BrowserExecution.navigateJoomla(browser);
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}

	@Test(description = "User can edit the Publish Date of a weblink")
	public void TC_JOOMLA_WEBLINKS_017()
	{
		WeblinksPage = new ac_WeblinksPage(driver);
		WeblinksPage.navigatemenu(driver, "Components", "Weblinks", null);
		WeblinksPage.clickToolbarButton(driver, "new");
		WeblinksPage.fillInfoWeblinks (weblinks_title1, weblinks_url1, null, null, "1/13/2016");
		WeblinksPage.clickToolbarButton(driver, "save");
		verifyTrue(WeblinksPage.doesTextPresent(driver, message_create));
		verifyTrue(WeblinksPage.doesitemExist(driver, weblinks_title1));
		
		WeblinksPage.selectCheckboxItemWithoutDiv(driver, weblinks_title1);
		WeblinksPage.clickToolbarButton(driver, "edit");
		WeblinksPage.fillInfoWeblinks (weblinks_title2, weblinks_url2, null, null, "1/14/2016");
		WeblinksPage.clickToolbarButton(driver, "save");
		verifyTrue(WeblinksPage.doesTextPresent(driver, message_create));
		verifyTrue(WeblinksPage.doesitemExist(driver, weblinks_title2));
		
		WeblinksPage.selectCheckboxItemWithoutDiv(driver, weblinks_title2);
		WeblinksPage.clickToolbarButton(driver, "edit");
		selecttabs(driver, in_WeblinksPage.otherInfo_tabs, "Publishing");
		
		verifyTrue(getValueitem(driver, in_WeblinksPage.publish_date_textbox).equals("2016-01-14" + " 00:00:00"));	
		WeblinksPage.clickToolbarButton(driver, "cancel");
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