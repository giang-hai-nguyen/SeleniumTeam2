package BannerClient;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ac_common.ac_AdministratorPage;
import ac_pages.ac_BannerClientPage;
import ac_pages.ac_LoginPage;
import config.Config;
import in_common.in_AdminstratorPage;
import in_pages.in_BannerClientPage;;


public class TM_JOOMLA_BANNERS_CLIENTS_001 extends ac_BannerClientPage {
	@BeforeClass
	public void Setup() {
		driver = openBrowser();
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}

	@Test(description = "Verify that user can create a new client")
	public void TC_JOOMLA_BANNERS_CLIENTS_001()
	{
		BannerClientPage = new ac_BannerClientPage(driver);
		BannerClientPage.navigatemenu(driver, "Components", "Banners", "Clients");
		BannerClientPage.click(driver, in_BannerClientPage.new_button);
		BannerClientPage.fillInfoBannerClient(bannerclient_title, "Mr John", "John@gmail.com", null, "save & close");
		
		verifyTrue(doesTextPresent(driver, message_create));
		verifyTrue(doesitemExist(driver, bannerclient_title));
	}
	
	
	@Test (description= "Verify that user can edit a client")
	public void TC_JOOMLA_BANNERS_CLIENTS_002()
	{
		BannerClientPage.selectCheckboxItem(driver, bannerclient_title);
		BannerClientPage.click(driver, in_BannerClientPage.edit_button);
		BannerClientPage.fillInfoBannerClient(bannerclient_title_modified, null, null, null, "save & close");
		
		verifyTrue(doesTextPresent(driver, message_create));
		verifyTrue(doesitemExist(driver, bannerclient_title_modified));
	}
	
	
	@Test (description = "Verify that user can unpublish a client")
	public void TC_JOOMLA_BANNERS_CLIENTS_003()
	{
		BannerClientPage.selectCheckboxItem(driver, bannerclient_title_modified);
		BannerClientPage.clickToolbarButton(driver, in_AdminstratorPage.toolbar_unpublish);
			
		verifyTrue(doesTextPresent(driver, message_unpublish));
		verifyTrue(getitemStatus(driver, in_BannerClientPage.publish_status_icon, bannerclient_title_modified).equals("state unpublish"));
		
	}

	@Test (description = "Verify that user can publish a client")
	public void TC_JOOMLA_CATEGORY_MANAGER_004()
	{
		BannerClientPage.selectCheckboxItem(driver, bannerclient_title_modified);
		BannerClientPage.clickToolbarButton(driver, in_AdminstratorPage.toolbar_publish);
		
		verifyTrue(doesTextPresent(driver, message_publish));
		verifyTrue(getitemStatus(driver, in_BannerClientPage.publish_status_icon, bannerclient_title_modified).equals("state publish"));
	}
	
	@Test (description = "Verify that user can archive a client")
	public void TC_JOOMLA_CATEGORY_MANAGER_005()
	{
		BannerClientPage.selectCheckboxItem(driver, bannerclient_title_modified);
		BannerClientPage.click(driver, in_BannerClientPage.archive_button);
		
		verifyTrue(doesTextPresent(driver, message_archive));
		selectitems(driver, in_BannerClientPage.status_filter_dropdown, "Archived");
		verifyTrue(doesitemExist(driver, bannerclient_title_modified));
	}
	

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

	
	public String bannerclient_title = randUniqueString("Test Banner Client");
	public String bannerclient_title_modified = randUniqueString("Test Banner Client modified");
	public String message_create = "Client successfully saved";
	public String message_archive = "1 client successfully archived";
	
	public String status_unpublish = "Unpublished";
	public String status_publish = "Published";
	public String message_publish = "1 client successfully published";
	public String message_unpublish = "1 client successfully unpublished";
}
