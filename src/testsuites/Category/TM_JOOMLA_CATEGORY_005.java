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

public class TM_JOOMLA_CATEGORY_005 extends ac_CategoryPage {
	
	@BeforeClass
	public void Setup() {
		driver = openBrowser();
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}
	
	@Test (description = "Verify that user can creat a new category by using 'Save as Copy' button")
	public void TC_JOOMLA_CATEGORY_MANAGER_013()
	{
		CategoryPage = new ac_CategoryPage(driver);
		CategoryPage.navigatemenu(driver, "Content", "Categories", null);
		CategoryPage.clickToolbarButton(driver, "new");
		CategoryPage.fillInfoCategory(category_title1, null, null, null);
		CategoryPage.clickToolbarButton(driver, "apply");
		
		verifyTrue(CategoryPage.doesTextPresent(driver, message_create));
		
		clearText(driver, in_CategoryPage.title_texbox);
		enter(driver, in_CategoryPage.title_texbox, category_title2);
		CategoryPage.clickToolbarButton(driver, "save-copy");
		verifyTrue(doesTextPresent(driver, message_create));
		CategoryPage.clickToolbarButton(driver, "cancel");
		
		verifyTrue(CategoryPage.doesitemExist(driver, category_title1));
		verifyTrue(CategoryPage.doesitemExist(driver, category_title2));			
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
	
	public String category_title1 = randUniqueString("Test Category1");
	public String category_title2 = randUniqueString("Test Category2");
	public String message_create = "Category successfully saved";

}

