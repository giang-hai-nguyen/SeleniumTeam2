package Category;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ac_common.ac_AdministratorPage;
import ac_pages.ac_LoginPage;
import ac_pages.ac_CategoryPage;
import config.Config;
import in_pages.in_CategoryPage;


public class TM_JOOMLA_CATEGORY_002 extends ac_CategoryPage {
	@BeforeClass
	public void Setup() {
		driver = openBrowser();
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}
	
	@Test (description = "Verify that user can search a category by using filter textbox")
	public void TC_JOOMLA_CATEGORY_MANAGER_008()
	{
		CategoryPage = new ac_CategoryPage(driver);
		CategoryPage.navigatemenu(driver, "Content", "Category Manager", null);
		CategoryPage.click(driver, in_CategoryPage.new_button);
		CategoryPage.fillInfoCategory(category_title, null, null, "save & close");
		
		verifyTrue(doesitemExist(driver, category_title));
		clearText(driver, in_CategoryPage.filter_textbox);
		click(driver, in_CategoryPage.search_button);
		
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
	private ac_CategoryPage CategoryPage;
	
	public String category_title = randUniqueString("Test Category");
	public String category_title_modified = randUniqueString("Test Category modified");
}
