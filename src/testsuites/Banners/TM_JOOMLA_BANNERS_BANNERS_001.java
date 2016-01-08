package Banners;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ac_common.*;
import ac_pages.*;
import config.*;
import in_pages.*;

public class TM_JOOMLA_BANNERS_BANNERS_001 extends ac_BannersPage {
	@BeforeClass
	public void Setup() {
		driver = openBrowser();
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}

	@Test (description = "Verify that user can create new banner", groups={"regression"})
	public void TC_JOOMLA_BANNERS_BANNERS_001()
	{
		BannerPage = new ac_BannersPage(driver);
		navigatemenu(driver, "Components", "Banners", "Clients");
		BannerPage.clickToolbarButton(driver, "new");
		BannerPage.createNewClient(client_name, contact_name, contact_email, "save & close");
		verifyTrue(BannerPage.doesTextPresent(driver, message_client_create));
		verifyTrue(BannerPage.doesitemExist(driver, client_name));
		
		navigatemenu(driver, "Components", "Banners", "Categories");
		BannerPage.clickToolbarButton(driver, "new");
		BannerPage.createNewCategory(category_title, "save & close");
		verifyTrue(BannerPage.doesTextPresent(driver, message_category_create));
		verifyTrue(BannerPage.doesitemExist(driver, category_title));
		
		BannerPage.navigatemenu(driver, "Components", "Banners", "Banners");
		BannerPage.clickToolbarButton(driver, "new");
		BannerPage.createNewBanner(banner_name,"- "+ category_title, null, client_name, null, "save & close");
		verifyTrue(BannerPage.doesTextPresent(driver, message_banner_create));
		verifyTrue(BannerPage.doesitemExist(driver, banner_name));
	}
	
	@Test (description = "Verify that user can edit a banner", dependsOnMethods = "TC_JOOMLA_BANNERS_BANNERS_001", groups={"regression"})
	public void TC_JOOMLA_BANNERS_BANNERS_002()
	{
		BannerPage.selectCheckboxItem(driver, banner_name);
		BannerPage.clickToolbarButton(driver, "edit");
		BannerPage.createNewBanner(banner_name_edit, null, null, null, null, "save & close");
		verifyTrue(BannerPage.doesTextPresent(driver, message_banner_create));
		BannerPage.selectCheckboxItem(driver, banner_name_edit);
		BannerPage.clickToolbarButton(driver, "edit");
		verifyTrue(BannerPage.verifyDataOfBanner(banner_name_edit, category_name_edit, state_publish, client_name));
		BannerPage.clickToolbarButton(driver, "cancel");
	}
	
	@Test (description = "Verify that user can create a new banner with 'unpublished' status", dependsOnMethods = "TC_JOOMLA_BANNERS_BANNERS_002", groups={"regression"})
	public void TC_JOOMLA_BANNERS_BANNERS_003()
	{
		BannerPage.selectCheckboxItem(driver, banner_name_edit);
		BannerPage.clickToolbarButton(driver, "edit");
		BannerPage.createNewBanner(banner_name_edit, null, state_unpublish, null, null, "save & close");
		verifyTrue(BannerPage.doesTextPresent(driver, message_banner_create));
		verifyTrue(BannerPage.verifyDataOfBanner(banner_name_edit, category_name_edit, state_unpublish, client_name_edit));
		BannerPage.clickToolbarButton(driver, "cancel");
	}
	
//	@Test (description = "Verify that user can archive a banner")
//	public void TC_JOOMLA_BANNERS_BANNERS_005()
//	{
//		BannerPage.selectCheckboxItem(driver, name_modified);
//		BannerPage.clickToolbarButton(driver, "archive");
//		verifyTrue(BannerPage.doesTextPresent(driver, message_archive));
//		BannerPage.selectitems(driver, in_ContactsPage.filter_state_dropdown, "Archived");
//		verifyTrue(BannerPage.doesitemExist(driver, name_modified));
//	}
//	
//	@Test (description ="Verify that user can send a banner to trash")
//	public void TC_JOOMLA_BANNERS_BANNERS_007()
//	{
//		BannerPage.selectCheckboxItem(driver, name_modified);
//		BannerPage.clickToolbarButton(driver, "trash");
//		verifyTrue(BannerPage.doesTextPresent(driver, message_trash));
//		BannerPage.selectitems(driver, in_ContactsPage.filter_state_dropdown, "Trashed");
//		verifyTrue(BannerPage.doesitemExist(driver, name_modified));
//	}
//	
	@AfterClass
	public void teardown(){
		AdminPage = new ac_AdministratorPage(driver);
		AdminPage.Logout();		
		driver.close();
	}
	
	private WebDriver driver;
	private ac_LoginPage LoginPage;	
	private ac_AdministratorPage AdminPage;
	private ac_BannersPage BannerPage;
	
	private String client_name = randUniqueString("Client Test");
	private String client_name_edit ="update " + client_name;
	private String contact_name = "Mr John";
	private String contact_email = "John@gmail.com";
	private String category_title = randUniqueString("Category Test");
	private String category_name_edit ="update " + category_title;
	private String banner_name = randUniqueString("Banner Test");
	private String banner_name_edit ="update " + banner_name;
	private String message_client_create = "Client successfully saved";
	private String message_category_create = "Category successfully saved";
	private String message_banner_create = "Banner successfully saved";
	
	private String state_unpublish = "Unpublished";
	private String state_publish = "Published";
	private String message_publish = "1 contact successfully published";
	private String message_unpublish = "1 contact successfully unpublished";
	private String message_archive = "1 contact archived";
	private String message_trash = "1 contact successfully trashed";
}
