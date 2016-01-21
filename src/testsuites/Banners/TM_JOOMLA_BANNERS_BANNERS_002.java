package Banners;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import config.Config;
import ac_common.BrowserExecution;
import ac_common.ac_AdministratorPage;
import ac_pages.ac_BannersPage;
import ac_pages.ac_LoginPage;

public class TM_JOOMLA_BANNERS_BANNERS_002 extends ac_BannersPage{

	@BeforeClass
	@Parameters({ "browser" })
	public void Setup(@Optional("firefox") String browser) {
		driver = BrowserExecution.navigateJoomla(browser);
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}
	
	@AfterClass
	public void tearDown() {
		AdminPage = new ac_AdministratorPage(driver);
		AdminPage.Logout();
		BrowserExecution.closeJoomla();
	}
	
	@Test(description = "Verify that user can browse Banner help page", groups = "regression")
	public void TC_JOOMLA_BANNERS_BANNERS_007() {

		BannerPage = new ac_BannersPage(driver);
		navigatemenu(driver, "Components", "Banners", "Banners");
		BannerPage.clickToolbarButton(driver, "help");
		verifyTrue(BannerPage.doesHelpWindowsDisplay());
	}
	
	@Test(description = "Verify that user can search a banner by using filter textbox", groups = "regression")
	public void TC_JOOMLA_BANNERS_BANNERS_008() {

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
	
	@Test(description = "Verify that user can search a banner by using filter dropdown lists", dependsOnMethods = "TC_JOOMLA_BANNERS_BANNERS_008", groups = "regression")
	public void TC_JOOMLA_BANNERS_BANNERS_009() {
		BannerPage.filterArticleByDropdown(state_publish, category_title, null, null);		
		verifyTrue(BannerPage.doesitemExist(driver, banner_name));
	}
	private WebDriver driver;
	private ac_LoginPage LoginPage;	
	private ac_AdministratorPage AdminPage;
	private ac_BannersPage BannerPage;
	
	private String client_name = randUniqueString("Client Test");
	private String contact_name = "Mr John";
	private String contact_email = "John@gmail.com";
	private String category_title = randUniqueString("Category Test");
	private String banner_name = randUniqueString("Banner Test");
	private String message_client_create = "Client successfully saved";
	private String message_category_create = "Category successfully saved";
	private String message_banner_create = "Banner successfully saved";
	private String state_publish = "Published";
}
