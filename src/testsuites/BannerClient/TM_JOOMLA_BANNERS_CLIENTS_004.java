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

public class TM_JOOMLA_BANNERS_CLIENTS_004 extends ac_BannerClientPage {
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
	
	@Parameters({ "browser" })
	@Test(description = "Verify that user can check in a client", groups={"regression"})
	public void TC_JOOMLA_BANNERS_CLIENTS_010(@Optional("firefox") String browser){
		
		BannerClientPage = new ac_BannerClientPage(driver);
		BannerClientPage.navigatemenu(driver, "Components", "Banners", "Clients");
		BannerClientPage.clickToolbarButton(driver, "new");
		BannerClientPage.fillInfoBannerClient(bannerclient_title1, "Mr John", "John@gmail.com", null);
		BannerClientPage.clickToolbarButton(driver, "apply");
		
		verifyTrue(BannerClientPage.doesTextPresent(driver, message_create));
		
		BrowserExecution.closeJoomla();
		Setup(browser);
		
		BannerClientPage = new ac_BannerClientPage(driver);
		BannerClientPage.navigatemenu(driver, "Components", "Banners", "Clients");
		BannerClientPage.searchItem(driver, bannerclient_title1);
		verifyTrue(BannerClientPage.verifyCheckInStateBannerClient(bannerclient_title1, "locked"));
		
		BannerClientPage.checkinBannerClient(bannerclient_title1);
		verifyTrue(BannerClientPage.verifyCheckInStateBannerClient(bannerclient_title1, "unlocked"));
	}
	
	@Test(description = "Verify that user can browse 'New Client help'", dependsOnMethods = "TC_JOOMLA_BANNERS_CLIENTS_010" , groups={"regression"})
	public void TC_JOOMLA_BANNERS_CLIENTS_012(){
		
		BannerClientPage.clickToolbarButton(driver, "help");
		verifyTrue(BannerClientPage.doesHelpWindowsDisplay());

	}
	
	@Test(description = "Verify that user can not create a new client without entering the name of the client", dependsOnMethods = "TC_JOOMLA_BANNERS_CLIENTS_012" , groups={"regression"})
	public void TC_JOOMLA_BANNERS_CLIENTS_014(){
		
		BannerClientPage.clickToolbarButton(driver, "new");
		BannerClientPage.fillInfoBannerClient("", "Mr John", "John@gmail.com", null);
		BannerClientPage.clickToolbarButton(driver, "save");
		
		verifyTrue(BannerClientPage.doesTextPresent(driver, message_banner_invalid));
		BannerClientPage.clickToolbarButton(driver, "cancel");
	}
	
	@Test(description = "Verify that user can change the quantity of items displayed in client table", dependsOnMethods = "TC_JOOMLA_BANNERS_CLIENTS_014" , groups={"regression"})
	public void TC_JOOMLA_BANNERS_CLIENTS_016(){
		
		verifyTrue(BannerClientPage.doesPagingNumber(displaypagenumber));
		
	}

	private WebDriver driver;
	private ac_LoginPage LoginPage;	
	private ac_AdministratorPage AdminPage;
	private ac_BannerClientPage BannerClientPage;
	
	public String bannerclient_title1 = randUniqueString("Test Banner Client1");
	public String message_create = "Client successfully saved";
	private String message_banner_invalid = "Name";
	private int displaypagenumber = 10;
}
