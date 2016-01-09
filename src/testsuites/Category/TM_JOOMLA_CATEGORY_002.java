package Category;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ac_common.BrowserExecution;
import ac_common.ac_AdministratorPage;
import ac_pages.ac_LoginPage;
import ac_pages.ac_CategoryPage;
import config.Config;
import in_pages.in_CategoryPage;


public class TM_JOOMLA_CATEGORY_002 extends ac_CategoryPage {
	@BeforeClass
	@Parameters({ "browser" })
	public void Setup(@Optional("firefox") String browser) {
		driver = BrowserExecution.navigateJoomla(browser);
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}
	
	@Test (description = "Verify that user can search a category by using filter textbox")
	public void TC_JOOMLA_CATEGORY_MANAGER_008()
	{
		CategoryPage = new ac_CategoryPage(driver);
		CategoryPage.navigatemenu(driver, "Content", "Categories", null);
		CategoryPage.clickToolbarButton(driver, "new");
		CategoryPage.fillInfoCategory(category_title, null, null, null);
		CategoryPage.clickToolbarButton(driver, "save");
		
		verifyTrue(CategoryPage.doesitemExist(driver, category_title));
		
		clearText(driver, in_CategoryPage.filter_textbox);
		click(driver, in_CategoryPage.search_button);
		CategoryPage.searchItem(driver, category_title);
		verifyTrue(CategoryPage.doesitemExist(driver, category_title));
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
	private String category_title_modified = randUniqueString("Test Category modified");
}
