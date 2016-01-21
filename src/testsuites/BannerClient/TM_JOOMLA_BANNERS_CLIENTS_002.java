package BannerClient;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import ac_common.BrowserExecution;
import ac_common.ac_AdministratorPage;
import ac_pages.ac_BannerClientPage;
import ac_pages.ac_LoginPage;
import config.Config;
import in_pages.in_BannerClientPage;

public class TM_JOOMLA_BANNERS_CLIENTS_002 extends ac_BannerClientPage {
	@BeforeClass
	@Parameters({ "browser" })
	public void Setup(@Optional("firefox") String browser) {
		driver = BrowserExecution.navigateJoomla(browser);
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}

	@Test (description = "Verify that user can send a client to trash", groups = "regression")
	public void TC_JOOMLA_BANNERS_CLIENTS_006()
	{
		BannerClientPage = new ac_BannerClientPage(driver);
		BannerClientPage.navigatemenu(driver, "Components", "Banners", "Clients");
		BannerClientPage.clickToolbarButton(driver, "new");
		BannerClientPage.fillInfoBannerClient(bannerclient_title, "Mr John", "John@gmail.com", null);
		BannerClientPage.clickToolbarButton(driver, "save");
		verifyTrue(BannerClientPage.doesTextPresent(driver, message_create));
		verifyTrue(BannerClientPage.doesitemExist(driver, bannerclient_title));
	

		BannerClientPage.selectCheckboxItem(driver, bannerclient_title);
		BannerClientPage.clickToolbarButton(driver, "trash");
		verifyTrue(BannerClientPage.doesTextPresent(driver, message_trash));
			
		clearText(driver, in_BannerClientPage.filter_textbox);
		click(driver, in_BannerClientPage.search_button);
		click(driver, in_BannerClientPage.searchtool_button);
		BannerClientPage.filterBannerClientByDropdown("Trashed");
		verifyTrue(BannerClientPage.doesitemExist(driver, bannerclient_title));
	}
	
	
	@Test (description = "Verify that user can browse client help page", groups = "regression")
	public void TC_JOOMLA_BANNERS_CLIENTS_007()
	{
		BannerClientPage.navigatemenu(driver, "Components", "Banners", "Clients");
		BannerClientPage.clickToolbarButton(driver, "help");
		verifyTrue(BannerClientPage.doesHelpPageExist(driver, in_BannerClientPage.help_text));
		
	}
	
	
	@Test (description = "Verify that user can search a client  by using filter textbox", groups = "regression")
	public void TC_JOOMLA_BANNERS_CLIENTS_008()
	{
		BannerClientPage.clickToolbarButton(driver, "new");
		BannerClientPage.fillInfoBannerClient(bannerclient_title, "Mr John", "abc@gmail.com", null);
		BannerClientPage.clickToolbarButton(driver, "save");
		
		verifyTrue(BannerClientPage.doesTextPresent(driver, message_create));
		verifyTrue(BannerClientPage.doesitemExist(driver, bannerclient_title));
	

		clearText(driver, in_BannerClientPage.filter_textbox);
		click(driver, in_BannerClientPage.search_button);
		BannerClientPage.searchItem(driver, bannerclient_title);
		verifyTrue(BannerClientPage.doesitemExist(driver, bannerclient_title));
	}
	
	
	@Test (description = "Verify that user can search a client by using filter dropdown list", groups = "regression")
	public void TC_JOOMLA_BANNERS_CLIENTS_009()
	{
		BannerClientPage.clickToolbarButton(driver, "new");
		BannerClientPage.fillInfoBannerClient(bannerclient_title, "Mr John", "abc@gmail.com", "Unpublished");
		BannerClientPage.clickToolbarButton(driver, "save");
		
		verifyTrue(BannerClientPage.doesTextPresent(driver, message_create));
		verifyTrue(BannerClientPage.doesitemExist(driver, bannerclient_title));
	

		clearText(driver, in_BannerClientPage.filter_textbox);
		click(driver, in_BannerClientPage.search_button);
		click(driver, in_BannerClientPage.clear_button);
		click(driver, in_BannerClientPage.searchtool_button);
		BannerClientPage.filterBannerClientByDropdown("Unpublished");
		verifyTrue(BannerClientPage.doesitemExist(driver, bannerclient_title));
	}
	
	
	@AfterClass
	public void teardown(){
		AdminPage = new ac_AdministratorPage(driver);
		AdminPage.deleteItem(driver, bannerclient_title, "Yes");
		AdminPage.Logout();		
		BrowserExecution.closeJoomla();
	}
	
	private WebDriver driver;
	private ac_LoginPage LoginPage;	
	private ac_AdministratorPage AdminPage;
	private ac_BannerClientPage BannerClientPage;

	
	public String bannerclient_title = randUniqueString("Test Banner Client");
	public String message_create = "Client successfully saved";
	public String message_trash = "1 client successfully trashed";
	
}