package Category;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ac_common.ac_AdministratorPage;
import ac_pages.ac_CategoryPage;
import ac_pages.ac_LoginPage;
import config.Config;


public class TM_JOOMLA_CATEGORY_004 extends ac_CategoryPage {
	
	@BeforeClass
	public void Setup() {
		driver = openBrowser();
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}
	
	@Test (description = "Verify that user can cancel adding action while adding a new create")
	public void TC_JOOMLA_CATEGORY_MANAGER_012()
	{
		CategoryPage = new ac_CategoryPage(driver);
		CategoryPage.navigatemenu(driver, "Content", "Categories", null);
		CategoryPage.clickToolbarButton(driver, "new");
		CategoryPage.fillInfoCategory(category_title, null, null, null);
		CategoryPage.clickToolbarButton(driver, "cancel");
		
		verifyTrue(CategoryPage.doesitemNotExist(driver, category_title));
				
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
	private ac_CategoryPage CategoryPage;
	
	public String category_title = randUniqueString("Test Category");

}

