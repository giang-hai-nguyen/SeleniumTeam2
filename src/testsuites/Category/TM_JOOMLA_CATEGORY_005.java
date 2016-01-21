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

public class TM_JOOMLA_CATEGORY_005 extends ac_CategoryPage {
	
	@BeforeClass
	@Parameters({ "browser" })
	public void Setup(@Optional("firefox") String browser) {
		driver = BrowserExecution.navigateJoomla(browser);
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}
	
	@Test (description = "Verify that user can creat a new category by using 'Save as Copy' button", groups = "regression")
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
	
	@Test (description = "Verify that user cannot create a new Category without entering the title of the category", groups = "regression")
	public void TC_JOOMLA_CATEGORY_MANAGER_017()
	{
		CategoryPage.clickToolbarButton(driver, "new");
		CategoryPage.fillInfoCategory("", null, null, null);
		CategoryPage.clickToolbarButton(driver, "apply");
		
		verifyTrue(CategoryPage.doesTextPresent(driver, message_category_invalid));
		CategoryPage.clickToolbarButton(driver, "cancel");
		
	}
	@Test (description = "Verify that user can move many articles to another category", dependsOnMethods = "TC_JOOMLA_CATEGORY_MANAGER_013", groups = "regression")
	public void TC_JOOMLA_CATEGORY_MANAGER_014()
	{
		selectCheckboxItemWithoutDiv(driver, category_title1);		
		CategoryPage.clickToolbarButton(driver, "batch");
		CategoryPage.selectCategoryProcess(move_copy_category, "Move");
		
		verifyTrue(CategoryPage.doesTextPresent(driver, bath_message));
		
		selectCheckboxItemWithoutDiv(driver, category_title1);	
		CategoryPage.clickToolbarButton(driver, "edit");
		
		verifyTrue(CategoryPage.verifyParentOfCategory(move_copy_category));
		CategoryPage.clickToolbarButton(driver, "cancel");
	}
	
	@Test (description = "Verify that user can copy many articles to another category", dependsOnMethods = "TC_JOOMLA_CATEGORY_MANAGER_014", groups = "regression")
	public void TC_JOOMLA_CATEGORY_MANAGER_015()
	{
		selectCheckboxItemWithoutDiv(driver, category_title2);		
		CategoryPage.clickToolbarButton(driver, "batch");
		CategoryPage.selectCategoryProcess(move_copy_category, "Copy");
		
		verifyTrue(CategoryPage.doesTextPresent(driver, bath_message));
		
		selectCheckboxItemWithoutDiv(driver, category_title2);	
		CategoryPage.clickToolbarButton(driver, "edit");
		
		verifyTrue(CategoryPage.verifyParentOfCategory(move_copy_category));
		CategoryPage.clickToolbarButton(driver, "cancel");
	}
	
	@Test (description = "Verify that user can change access level to may articles", dependsOnMethods = "TC_JOOMLA_CATEGORY_MANAGER_014", groups = "regression")
	public void TC_JOOMLA_CATEGORY_MANAGER_016()
	{
		selectCheckboxItemWithoutDiv(driver, category_title1);		
		CategoryPage.clickToolbarButton(driver, "batch");
		CategoryPage.selectAccessLevel(access);
		
		verifyTrue(CategoryPage.doesTextPresent(driver, bath_message));
		
		selectCheckboxItemWithoutDiv(driver, category_title1);	
		CategoryPage.clickToolbarButton(driver, "edit");
		
		verifyTrue(CategoryPage.verifyAccessOfCategory(access));
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
	
	private String category_title1 = randUniqueString("Test Category1");
	private String category_title2 = randUniqueString("Test Category2");
	private String message_create = "Category successfully saved";
	private String message_category_invalid = "Title";
	private String move_copy_category = "Uncategorised";
	private String access = "Special";
	private String bath_message = "Batch process completed successfully.";

}

