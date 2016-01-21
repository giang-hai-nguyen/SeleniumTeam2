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

public class TM_JOOMLA_BANNERS_BANNERS_004 extends ac_BannersPage {
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
	
	@Test(description = "Verify that user can create a new banner by using Save as Copy button",dependsOnMethods = "TC_JOOMLA_BANNERS_BANNERS_016", groups = "regression")
	public void TC_JOOMLA_BANNERS_BANNERS_013() {		
		
		BannerPage.navigatemenu(driver, "Components", "Banners", "Clients");
		BannerPage.clickToolbarButton(driver, "new");
		BannerPage.createNewClient(client_name, contact_name, contact_email, "save & close");
		verifyTrue(BannerPage.doesTextPresent(driver, message_client_create));
		verifyTrue(BannerPage.doesitemExist(driver, client_name));
		
		BannerPage.navigatemenu(driver, "Components", "Banners", "Categories");
		BannerPage.clickToolbarButton(driver, "new");
		BannerPage.createNewCategory(category_title, "save & close");
		verifyTrue(BannerPage.doesTextPresent(driver, message_category_create));
		verifyTrue(BannerPage.doesitemExist(driver, category_title));
		
		BannerPage.navigatemenu(driver, "Components", "Banners", "Banners");
		BannerPage.clickToolbarButton(driver, "new");
		BannerPage.createNewBanner(banner_name,"- "+ category_title, null, client_name, null, "save");
		verifyTrue(BannerPage.doesTextPresent(driver, message_banner_create));
		verifyTrue(BannerPage.isPageTitle(page_title_editbanner));
		
		BannerPage.createNewBanner(banner_name_copy,"- "+ category_title, state_unpublish, client_name, null, "save & copy");
		verifyTrue(BannerPage.doesTextPresent(driver, message_banner_create));
		BannerPage.clickToolbarButton(driver, "cancel");
		
		verifyTrue(BannerPage.doesitemExist(driver, banner_name));
		verifyTrue(BannerPage.doesitemExist(driver, banner_name_copy));
	}
	
	@Test(description = "Verify that user cannot create a new banner without entering the name of the banner", dependsOnMethods = "TC_JOOMLA_BANNERS_BANNERS_013", groups = "regression")
	public void TC_JOOMLA_BANNERS_BANNERS_014() {

		BannerPage.clickToolbarButton(driver, "new");
		BannerPage.createNewBanner("","- "+ category_title, null, client_name, null, "save & close");
		
		verifyTrue(BannerPage.doesTextPresent(driver, message_banner_invalid));
		BannerPage.clickToolbarButton(driver, "cancel");
	}
	
	@Test(description = "Verify that user can sort items displayed in banner table by ID", groups={"regression"})
	public void TC_JOOMLA_BANNERS_BANNERS_016() {

		BannerPage = new ac_BannersPage(driver);
		BannerPage.navigatemenu(driver, "Components", "Banners", "Banners");
		BannerPage.clickBannerHeaderID();
		verifyTrue(BannerPage.doesSortingIDAscend());
		BannerPage.clickBannerHeaderID();
		verifyTrue(BannerPage.doesSortingIDDescend());

	}
	@Test(description = "Verify that user can change the quantity of items displayed in banner table", dependsOnMethods = "TC_JOOMLA_BANNERS_BANNERS_016", groups = "regression")
	public void TC_JOOMLA_BANNERS_BANNERS_015() {

		verifyTrue(BannerPage.doesPagingNumber(displaypagenumber));

	}
	
	@Test(description = "Verify that user can access 'Banner clients' page while browsing 'Banner' page", dependsOnMethods = "TC_JOOMLA_BANNERS_BANNERS_015", groups = "regression")
	public void TC_JOOMLA_BANNERS_BANNERS_017() {

		BannerPage.clicksubmenulink();
		verifyTrue(BannerPage.isPageTitle(page_title_bannerclient));

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
	private String banner_name_copy ="Save and copy " + banner_name;
	private String message_client_create = "Client successfully saved";
	private String message_category_create = "Category successfully saved";
	private String message_banner_create = "Banner successfully saved";
	private String state_unpublish = "Unpublished";
	private String message_banner_invalid = "Name";
	private int displaypagenumber = 10;
	private String page_title_editbanner ="Banners: Edit";
	private String page_title_bannerclient ="Banners: Clients";
	
}
