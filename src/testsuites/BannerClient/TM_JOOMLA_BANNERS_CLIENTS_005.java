package BannerClient;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ac_common.BrowserExecution;
import ac_common.ac_AdministratorPage;
import ac_pages.ac_BannerClientPage;
import ac_pages.ac_LoginPage;
import config.Config;

public class TM_JOOMLA_BANNERS_CLIENTS_005 extends ac_BannerClientPage{
	@BeforeClass
	@Parameters({ "browser" })
	public void Setup(@Optional("firefox") String browser) {
		driver = BrowserExecution.navigateJoomla(browser);
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}
	
	@AfterClass
	public void teardown(){
		AdminPage = new ac_AdministratorPage(driver);
		AdminPage.Logout();		
		BrowserExecution.closeJoomla();
	}
	
	@Test(description = "Verify that user can create many clients by using 'Save & New' button", groups = "regression")
	public void TC_JOOMLA_BANNERS_CLIENTS_011(){
		
		BannerClientPage = new ac_BannerClientPage(driver);
		BannerClientPage.navigatemenu(driver, "Components", "Banners", "Clients");
		BannerClientPage.clickToolbarButton(driver, "new");
		BannerClientPage.fillInfoBannerClient(bannerclient_title1, "Mr John", "John@gmail.com", null);
		BannerClientPage.clickToolbarButton(driver, "save-new");
		
		verifyTrue(BannerClientPage.doesTextPresent(driver, message_create));
		verifyTrue(BannerClientPage.isPageTitle(page_title_editbannerclient));
		
		BannerClientPage.fillInfoBannerClient(bannerclient_title2, "Mr John", "John@gmail.com", null);
		BannerClientPage.clickToolbarButton(driver, "save");
		
		verifyTrue(BannerClientPage.doesTextPresent(driver, message_create));
	}
	@Test(description = "Verify that user can not create a new client after entering invalid email address", dependsOnMethods = "TC_JOOMLA_BANNERS_CLIENTS_011", groups = "regression")
	public void TC_JOOMLA_BANNERS_CLIENTS_015(){
		
		BannerClientPage.clickToolbarButton(driver, "new");
		BannerClientPage.fillInfoBannerClient(bannerclient_title1, "Mr John", "Email test", null);
		BannerClientPage.clickToolbarButton(driver, "save");
		
		verifyTrue(BannerClientPage.doesTextPresent(driver, message_banner_invalid));
		BannerClientPage.clickToolbarButton(driver, "cancel");
	}
	private WebDriver driver;
	private ac_LoginPage LoginPage;	
	private ac_AdministratorPage AdminPage;
	private ac_BannerClientPage BannerClientPage;
	
	public String bannerclient_title1 = randUniqueString("Test Banner Client1");
	private String bannerclient_title2 ="Save as new " + bannerclient_title1;
	public String message_create = "Client successfully saved";
	private String page_title_editbannerclient ="Banners: New Client";
	private String message_banner_invalid = "Contact Email";
}
