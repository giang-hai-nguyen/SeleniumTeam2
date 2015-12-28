package BannerClient;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ac_common.ac_AdministratorPage;
import ac_pages.ac_BannerClientPage;
import ac_pages.ac_LoginPage;
import config.Config;
import in_pages.in_BannerClientPage;


public class TM_JOOMLA_BANNERS_CLIENTS_003 extends ac_BannerClientPage {

	@BeforeClass
	public void Setup() {
		driver = openBrowser();
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}
	
	@Test (description = "Verify that user can creat a new client by using 'Save as Copy' button")
	public void TC_JOOMLA_BANNERS_CLIENTS_013()
	{
		BannerClientPage = new ac_BannerClientPage(driver);
		BannerClientPage.navigatemenu(driver, "Components", "Banners", "Clients");
		BannerClientPage.click(driver, in_BannerClientPage.new_button);
		BannerClientPage.fillInfoBannerClient(bannerclient_title1, "Mr John", "John@gmail.com", null, "save");
		
		verifyTrue(doesTextPresent(driver, message_create));
		
		clearText(driver, in_BannerClientPage.clientname_texbox);
		enter(driver, in_BannerClientPage.clientname_texbox, bannerclient_title2);
		click(driver, in_BannerClientPage.saveascopy_button);
		
		verifyTrue(doesTextPresent(driver, message_create));
		click(driver, in_BannerClientPage.cancel_button);
		
		verifyTrue(doesitemExist(driver, bannerclient_title1));
		verifyTrue(doesitemExist(driver, bannerclient_title2));			
	}
	
	//ffff
	@AfterClass
	public void teardown(){
		AdminPage = new ac_AdministratorPage(driver);
		AdminPage.Logout();		
		closeBrowser();
	}
	
	private WebDriver driver;
	private ac_LoginPage LoginPage;	
	private ac_AdministratorPage AdminPage;
	private ac_BannerClientPage BannerClientPage;
	
	public String bannerclient_title1 = randUniqueString("Test Banner Client1");
	public String bannerclient_title2 = randUniqueString("Test Banner Client2");
	public String message_create = "Client successfully saved";

}
