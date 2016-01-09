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
import in_pages.in_BannerClientPage;


public class TM_JOOMLA_BANNERS_CLIENTS_003 extends ac_BannerClientPage {

	@BeforeClass
	@Parameters({ "browser" })
	public void Setup(@Optional("firefox") String browser) {
		driver = BrowserExecution.navigateJoomla(browser);
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}
	
	@Test (description = "Verify that user can creat a new client by using 'Save as Copy' button")
	public void TC_JOOMLA_BANNERS_CLIENTS_013()
	{
		BannerClientPage = new ac_BannerClientPage(driver);
		BannerClientPage.navigatemenu(driver, "Components", "Banners", "Clients");
		BannerClientPage.clickToolbarButton(driver, "new");
		BannerClientPage.fillInfoBannerClient(bannerclient_title1, "Mr John", "John@gmail.com", null);
		BannerClientPage.clickToolbarButton(driver, "apply");
		
		verifyTrue(BannerClientPage.doesTextPresent(driver, message_create));
		
		clearText(driver, in_BannerClientPage.clientname_texbox);
		enter(driver, in_BannerClientPage.clientname_texbox, bannerclient_title2);
		BannerClientPage.clickToolbarButton(driver, "save-copy");
		
		verifyTrue(BannerClientPage.doesTextPresent(driver, message_create));
		BannerClientPage.clickToolbarButton(driver, "cancel");
		
		verifyTrue(BannerClientPage.doesitemExist(driver, bannerclient_title1));
		verifyTrue(BannerClientPage.doesitemExist(driver, bannerclient_title2));			
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
	private ac_BannerClientPage BannerClientPage;
	
	public String bannerclient_title1 = randUniqueString("Test Banner Client1");
	public String bannerclient_title2 = randUniqueString("Test Banner Client2");
	public String message_create = "Client successfully saved";

}
