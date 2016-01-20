package Category;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ac_common.BrowserExecution;
import ac_common.ac_AdministratorPage;
import ac_pages.ac_CategoryPage;
import ac_pages.ac_LoginPage;
import config.Config;
import in_pages.in_CategoryPage;


public class TM_JOOMLA_CATEGORY_004 extends ac_CategoryPage {
	
	@BeforeClass
	@Parameters({ "browser" })
	public void Setup(@Optional("firefox") String browser) {
		driver = BrowserExecution.navigateJoomla(browser);
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}
	
	@Test (description = "Verify that user can cancel adding action while adding a new create", groups = "regression")
	public void TC_JOOMLA_CATEGORY_MANAGER_012()
	{
		CategoryPage = new ac_CategoryPage(driver);
		CategoryPage.navigatemenu(driver, "Content", "Categories", null);
		CategoryPage.clickToolbarButton(driver, "new");
		CategoryPage.fillInfoCategory(category_title, null, null, null);
		CategoryPage.clickToolbarButton(driver, "cancel");
		
		verifyTrue(CategoryPage.doesitemNotExist(driver, category_title));
				
	}
	
	@Test (description = "Verify that user can search a category by using filter dropdown lists", dependsOnMethods = "TC_JOOMLA_CATEGORY_MANAGER_012", groups = "regression")
	public void TC_JOOMLA_CATEGORY_MANAGER_010()
	{
		CategoryPage = new ac_CategoryPage(driver);
		CategoryPage.navigatemenu(driver, "Content", "Categories", null);
		CategoryPage.clickToolbarButton(driver, "new");
		CategoryPage.fillInfoCategory(category_title, "Unpublished", "Registered", "English (UK)");
		CategoryPage.clickToolbarButton(driver, "save-new");
		
		verifyTrue(CategoryPage.doesTextPresent(driver, message_create));
				
		CategoryPage.fillInfoCategory(category_title_save_new, "Unpublished", "Registered", "English (UK)");
		CategoryPage.clickToolbarButton(driver, "save");
				
		verifyTrue(CategoryPage.doesTextPresent(driver, message_create));
		
		verifyTrue(CategoryPage.doesitemExist(driver, category_title));
		verifyTrue(CategoryPage.doesitemExist(driver, category_title_save_new));
	}
	
	@Test (description = "Verify that user can search a category by using filter dropdown lists", dependsOnMethods = "TC_JOOMLA_CATEGORY_MANAGER_010", groups = "regression")
	public void TC_JOOMLA_CATEGORY_MANAGER_011()
	{
		CategoryPage.clickToolbarButton(driver, "new");
		CategoryPage.clickToolbarButton(driver, "help");
		verifyTrue(CategoryPage.doesHelpWindowsDisplay());
		CategoryPage.clickToolbarButton(driver, "cancel");
	}
	
	@AfterClass
	public void teardown(){
		AdminPage = new ac_AdministratorPage(driver);
		AdminPage.Logout();		
		BrowserExecution.closeJoomla();
	}
	
	private WebDriver driver;
	private ac_LoginPage LoginPage;	
	private ac_AdministratorPage AdminPage;
	private ac_CategoryPage CategoryPage;
	
	private String category_title = randUniqueString("Test Category");
	private String category_title_save_new = "Save new " + category_title;
	private String message_create = "Category successfully saved";
	private String page_title_editbannerclient ="Category Manager: Add A New Articles Category";

}

