package Category;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ac_common.ac_AdministratorPage;
import ac_pages.ac_CategoryPage;
import ac_pages.ac_LoginPage;
import config.Config;
import in_pages.in_CategoryPage;


public class TM_JOOMLA_CATEGORY_003 extends ac_CategoryPage {
	@BeforeClass
	public void Setup() {
		driver = openBrowser();
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}
	
	@Test (description = "Verify that user can search a category by using filter dropdown lists")
	public void TC_JOOMLA_CATEGORY_MANAGER_009()
	{
		CategoryPage = new ac_CategoryPage(driver);
		CategoryPage.navigatemenu(driver, "Content", "Category Manager", null);
		CategoryPage.click(driver, in_CategoryPage.new_button);
		CategoryPage.fillInfoCategory(category_title, "Unpublished", "Registered", "English (UK)", "save & close");
		
		verifyTrue(doesitemExist(driver, category_title));
		clearText(driver, in_CategoryPage.filter_textbox);
		click(driver, in_CategoryPage.search_button);
		
		CategoryPage.selectitems(driver, in_CategoryPage.status_filter_dropdown, "Unpublished");
		CategoryPage.selectitems(driver, in_CategoryPage.access_filter_dropdown, "Registered");
		CategoryPage.selectitems(driver, in_CategoryPage.language_filter_dropdown, "English (UK)");
	
		verifyTrue(doesitemExist(driver, category_title));
		
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
	public String category_title_modified = randUniqueString("Test Category modified");
}


