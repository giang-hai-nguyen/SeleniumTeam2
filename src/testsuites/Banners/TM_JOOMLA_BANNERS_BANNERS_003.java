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
	public void Setup(@Optional("chrome") String browser) {
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
	
	@Test(description = "Verify that user can browse Banner help page", groups={"regression"})
	public void TO_JOOMLA_BANNERS_BANNERS_007() {

		BannerPage = new ac_BannersPage(driver);
		navigatemenu(driver, "Components", "Banners", "Banners");
		BannerPage.clickToolbarButton(driver, "help");
		verifyTrue(BannerPage.doesHelpWindowsDisplay());
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
	private String banner_name_edit ="Update " + banner_name;
	private String message_client_create = "Client successfully saved";
	private String message_category_create = "Category successfully saved";
	private String message_banner_create = "Banner successfully saved";
	
	private String state_unpublish = "Unpublished";
	private String state_publish = "Published";
	private String message_unpublish = "1 banner successfully unpublished";
	private String message_archive = "1 banner successfully archived";
	private String message_trash = "1 banner successfully trashed";

}
