package Banners;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import config.Config;
import ac_common.BrowserExecution;
import ac_common.ac_AdministratorPage;
import ac_pages.ac_BannersPage;
import ac_pages.ac_LoginPage;


public class TM_JOOMLA_BANNERS_BANNERS_003 extends ac_BannersPage  {
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
	
	@Parameters({ "browser" })
	@Test(description = "Verify that user can check in a banner", groups={"regression"})
	public void TC_JOOMLA_BANNERS_BANNERS_010(@Optional("firefox") String browser){
		
		BannerPage = new ac_BannersPage(driver);
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

		BrowserExecution.closeJoomla();
		Setup(browser);

		BannerPage = new ac_BannersPage(driver);
		BannerPage.navigatemenu(driver, "Components", "Banners", "Banners");
		BannerPage.searchItem(driver, banner_name);
		verifyTrue(BannerPage.verifyCheckInStateBanner(banner_name, "locked"));

		BannerPage.checkinBanner(banner_name);
		verifyTrue(BannerPage.verifyCheckInStateBanner(banner_name, "unlocked"));

	}

	@Test(description = "Verify that user can create many banners by using Save & New button", dependsOnMethods = "TC_JOOMLA_BANNERS_BANNERS_010" , groups={"regression"})
	public void TC_JOOMLA_BANNERS_BANNERS_011() {

		BannerPage = new ac_BannersPage(driver);
		BannerPage.navigatemenu(driver, "Components", "Banners", "Banners");
		BannerPage.clickToolbarButton(driver, "new");
		BannerPage.createNewBanner(banner_name1,"- "+ category_title, null, client_name, null, "save & new");
		verifyTrue(BannerPage.doesTextPresent(driver, message_banner_create));
		
		BannerPage.createNewBanner(banner_name2,"- "+ category_title, null, client_name, null, "save & close");
		verifyTrue(BannerPage.doesTextPresent(driver, message_banner_create));

		BannerPage.selectCheckboxItem(driver, banner_name1);
		BannerPage.clickToolbarButton(driver, "edit");
		verifyTrue(BannerPage.verifyDataOfBanner(banner_name1, category_title, state_publish, client_name));
		BannerPage.clickToolbarButton(driver, "cancel");
		
		BannerPage.selectCheckboxItem(driver, banner_name2);
		BannerPage.clickToolbarButton(driver, "edit");
		verifyTrue(BannerPage.verifyDataOfBanner(banner_name2, category_title, state_publish, client_name));
		BannerPage.clickToolbarButton(driver, "cancel");
	}
	
	@Test(description = "Verify that user can browse New Banner help page in New banner page", dependsOnMethods = "TC_JOOMLA_BANNERS_BANNERS_011" , groups={"regression"})
	public void TC_JOOMLA_BANNERS_BANNERS_012() {
		
		//BannerPage = new ac_BannersPage(driver);
		BannerPage.navigatemenu(driver, "Components", "Banners", "Banners");
		BannerPage.clickToolbarButton(driver, "new");
		BannerPage.clickToolbarButton(driver, "help");
		verifyTrue(BannerPage.doesHelpWindowsDisplay());
		BannerPage.clickToolbarButton(driver, "cancel");

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
	private String banner_name1 = randUniqueString("Banner Test 1");
	private String banner_name2 = randUniqueString("Banner Test 2");
	private String message_client_create = "Client successfully saved";
	private String message_category_create = "Category successfully saved";
	private String message_banner_create = "Banner successfully saved";
	private String state_publish = "Published";


}
