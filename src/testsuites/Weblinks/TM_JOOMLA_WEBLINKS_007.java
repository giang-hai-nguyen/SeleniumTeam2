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


public class TM_JOOMLA_WEBLINKS_007 extends ac_WeblinksPage{
	
	@BeforeClass
	@Parameters({ "browser" })
	public void Setup(@Optional("firefox") String browser) {
		driver = BrowserExecution.navigateJoomla(browser);
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}

	@Test(description = "Verify user can change the order of weblinks using the Ordering column", groups={"regression"})
	public void TC_JOOMLA_WEBLINKS_014()
	{
		WeblinksPage = new ac_WeblinksPage(driver);
		WeblinksPage.navigatemenu(driver, "Components", "Weblinks", null);
		WeblinksPage.clickToolbarButton(driver, "new");
		WeblinksPage.fillInfoWeblinks (weblinks_title1, weblinks_url1, null, null, null);
		WeblinksPage.clickToolbarButton(driver, "save");
		
		WeblinksPage.navigatemenu(driver, "Components", "Weblinks", null);
		WeblinksPage.clickToolbarButton(driver, "new");
		WeblinksPage.fillInfoWeblinks (weblinks_title2, weblinks_url2, null, null, null);
		WeblinksPage.clickToolbarButton(driver, "save");
		
		searchItem(driver, "Test Weblinks");
		verifyTrue(WeblinksPage.doesOrderTwoWeblinks(weblinks_title2, weblinks_title1));

		WeblinksPage.clickHeaderOrderButton();
		verifyTrue(WeblinksPage.doesOrderTwoWeblinks(weblinks_title1, weblinks_title2));
	}
	
	@Test (description = "Verify user can sort the weblinks table by ID column", dependsOnMethods = "TC_JOOMLA_WEBLINKS_014", groups = "regression")
	public void TC_JOOMLA_WEBLINKS_011() {

		WeblinksPage.clickWeblinksHeaderID();
		verifyTrue(WeblinksPage.doesSortingIDAscending());
		WeblinksPage.clickWeblinksHeaderID();
		verifyTrue(WeblinksPage.doesSortingIDDescending());
	}
	
	@Test (description = "Verify user can paging the weblinks using the paging control", dependsOnMethods = "TC_JOOMLA_ARTICLE_011", groups = "regression")
	public void TC_JOOMLA_WEBLINKS_012() {
		
		verifyTrue(WeblinksPage.doesPagingNumber(displaypagenumber));
		verifyTrue(WeblinksPage.doesPagingAll());

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
		
		private String weblinks_title1 = randUniqueString("Test Weblinks1");
		private String weblinks_url1 = "http://www.joomla.org";
		
		private String weblinks_title2 = randUniqueString("Test Weblinks2");
		private String weblinks_url2 = "http://www.google.com";
		private int displaypagenumber = 5;
		
	
	}