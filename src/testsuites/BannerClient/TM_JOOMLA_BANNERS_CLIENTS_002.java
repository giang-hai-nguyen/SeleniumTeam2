package BannerClient;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.server.handler.SwitchToWindow;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import ac_common.ac_AdministratorPage;
import ac_pages.ac_BannerClientPage;
import ac_pages.ac_LoginPage;
import config.Config;
import in_pages.in_BannerClientPage;
import in_pages.in_WeblinksPage;

public class TM_JOOMLA_BANNERS_CLIENTS_002 extends ac_BannerClientPage {
	@BeforeClass
	public void Setup() {
		driver = openBrowser();
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}

	@Test (description = "Verify that user can send a client to trash")
	public void TC_JOOMLA_BANNERS_CLIENTS_006()
	{
		BannerClientPage = new ac_BannerClientPage(driver);
		BannerClientPage.navigatemenu(driver, "Components", "Banners", "Clients");
		BannerClientPage.click(driver, in_BannerClientPage.new_button);
		BannerClientPage.fillInfoBannerClient(bannerclient_title, "Mr John", "John@gmail.com", null, "save & close");
		
		verifyTrue(doesTextPresent(driver, message_create));
		verifyTrue(doesitemExist(driver, bannerclient_title));
	

		BannerClientPage.selectCheckboxItem(driver, bannerclient_title);
		BannerClientPage.click(driver, in_BannerClientPage.trash_button);
	
		verifyTrue(doesTextPresent(driver, message_trash));
		clearText(driver, in_BannerClientPage.filter_textbox);
		selectitems(driver, in_BannerClientPage.status_filter_dropdown, "Trashed");
		verifyTrue(doesitemExist(driver, bannerclient_title));
	}
	
	
//	@Test (description = "Verify that user can browse client help page")
//	public void TC_JOOMLA_BANNERS_CLIENTS_007()
//	{
//		BannerClientPage.click(driver, in_BannerClientPage.help_button);
//		
//		
//		
//	}
	
	@Test (description = "Verify that user can search a client  by using filter textbox")
	public void TC_JOOMLA_BANNERS_CLIENTS_008()
	{
		BannerClientPage.click(driver, in_BannerClientPage.new_button);
		BannerClientPage.fillInfoBannerClient(bannerclient_title, "Mr John", "John@gmail.com", null, "save & close");
		
		verifyTrue(doesTextPresent(driver, message_create));
		verifyTrue(doesitemExist(driver, bannerclient_title));
	

		clearText(driver, in_BannerClientPage.filter_textbox);
		click(driver, in_BannerClientPage.search_button);
		BannerClientPage.searchItem(driver, bannerclient_title);
		verifyTrue(doesitemExist(driver, bannerclient_title));
	}
	
	@Test (description = "Verify that user can search a client by using filter dropdown list")
	public void TC_JOOMLA_BANNERS_CLIENTS_009()
	{
		BannerClientPage.click(driver, in_BannerClientPage.new_button);
		BannerClientPage.fillInfoBannerClient(bannerclient_title, "Mr John", "John@gmail.com", "Unpublished", "save & close");
		
		verifyTrue(doesTextPresent(driver, message_create));
		verifyTrue(doesitemExist(driver, bannerclient_title));
	

		clearText(driver, in_BannerClientPage.filter_textbox);
		click(driver, in_BannerClientPage.search_button);
		selectitems(driver, in_BannerClientPage.status_filter_dropdown, "Unpublished");
		verifyTrue(doesitemExist(driver, bannerclient_title));
	}
	
	@AfterClass
	public void teardown(){
		AdminPage = new ac_AdministratorPage(driver);
		AdminPage.Logout();		
		driver.close();
	}
	
	private WebDriver driver;
	private ac_LoginPage LoginPage;	
	private ac_AdministratorPage AdminPage;
	private ac_BannerClientPage BannerClientPage;

	
	public String bannerclient_title = randUniqueString("Test Banner Client");
	public String message_create = "Client successfully saved";
	public String message_trash = "1 client successfully trashed";
	
}