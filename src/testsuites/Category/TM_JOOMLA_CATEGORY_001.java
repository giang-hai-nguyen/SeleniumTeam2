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


public class TM_JOOMLA_CATEGORY_001 extends ac_CategoryPage {
	
	@BeforeClass
	@Parameters({ "browser" })
	public void Setup(@Optional("firefox") String browser) {
		driver = BrowserExecution.navigateJoomla(browser);
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}
	
	@Test(description = "Verify that user can create a new category")
	public void TC_JOOMLA_CATEGORY_MANAGER_001()
	{
		CategoryPage = new ac_CategoryPage(driver);
		CategoryPage.navigatemenu(driver, "Content", "Categories", null);
		CategoryPage.clickToolbarButton(driver, "new");
		CategoryPage.fillInfoCategory(category_title, null, null, null);
		CategoryPage.clickToolbarButton(driver, "save");
				
		verifyTrue(CategoryPage.doesTextPresent(driver, message_create));
		verifyTrue(CategoryPage.doesitemExist(driver, category_title));
	}
	
	
	@Test (description= "Verify that user can edit a category", dependsOnMethods = {"TC_JOOMLA_CATEGORY_MANAGER_001"})
	public void TC_JOOMLA_CATEGORY_MANAGER_002()
	{
		CategoryPage.selectCheckboxItemWithoutDiv(driver, category_title);
		CategoryPage.clickToolbarButton(driver, "edit");
		CategoryPage.fillInfoCategory(category_title_modified, null, null, null);
		CategoryPage.clickToolbarButton(driver, "save");
		
		verifyTrue(CategoryPage.doesTextPresent(driver, message_create));
		verifyTrue(CategoryPage.doesitemExist(driver, category_title_modified));
	}
	
	
	@Test (description = "Verify that user can unpublish a category", dependsOnMethods = {"TC_JOOMLA_CATEGORY_MANAGER_002"})
	public void TC_JOOMLA_CATEGORY_MANAGER_003()
	{
		CategoryPage.selectCheckboxItemWithoutDiv(driver, category_title_modified);
		CategoryPage.clickToolbarButton(driver, "unpublish");
			
		verifyTrue(CategoryPage.doesTextPresent(driver, message_unpublish));
		verifyTrue(getitemStatus(driver, in_CategoryPage.publish_status_icon, category_title_modified).equals("icon-unpublish"));
		
	}

	@Test (description = "Verify that user can publish a category", dependsOnMethods = {"TC_JOOMLA_CATEGORY_MANAGER_003"})
	public void TC_JOOMLA_CATEGORY_MANAGER_004()
	{
		CategoryPage.selectCheckboxItemWithoutDiv(driver, category_title_modified);
		CategoryPage.clickToolbarButton(driver, "publish");
		
		verifyTrue(CategoryPage.doesTextPresent(driver, message_publish));
		verifyTrue(getitemStatus(driver, in_CategoryPage.publish_status_icon, category_title_modified).equals("icon-publish"));
	}
	
	@Test (description = "Verify that user can archive a category", dependsOnMethods = {"TC_JOOMLA_CATEGORY_MANAGER_004"})
	public void TC_JOOMLA_CATEGORY_MANAGER_005()
	{
		CategoryPage.selectCheckboxItemWithoutDiv(driver, category_title_modified);
		CategoryPage.clickToolbarButton(driver, "archive");
		
		verifyTrue(doesTextPresent(driver, message_archive));
		CategoryPage.click(driver, in_CategoryPage.searchtool_button);
		waitForPageLoad(Config.short_wait_time);
		CategoryPage.filterCategoryByDropdown("Archived", null, null);
		waitForPageLoad(Config.short_wait_time);
		verifyTrue(CategoryPage.doesitemExist(driver, category_title_modified));
	}
	
	@Test (description = "Verify that user can send a category to trash", dependsOnMethods = {"TC_JOOMLA_CATEGORY_MANAGER_005"})
	public void TC_JOOMLA_CATEGORY_MANAGER_006()
	{
		CategoryPage.selectCheckboxItemWithoutDiv(driver, category_title_modified);
		CategoryPage.clickToolbarButton(driver, "trash");
	
		verifyTrue(doesTextPresent(driver, message_trash));
		clearText(driver, in_CategoryPage.filter_textbox);
		CategoryPage.click(driver, in_CategoryPage.search_button);
		CategoryPage.click(driver, in_CategoryPage.searchtool_button);
		waitForPageLoad(Config.short_wait_time);
		CategoryPage.click(driver, in_CategoryPage.clear_button);
		CategoryPage.click(driver, in_CategoryPage.searchtool_button);
		
		CategoryPage.filterCategoryByDropdown("Trashed", null, null);
		verifyTrue(CategoryPage.doesitemExist(driver, category_title_modified));
	}
	
	
	@Test (description = "Verify that user can browse category help page", dependsOnMethods = {"TC_JOOMLA_CATEGORY_MANAGER_006"})
	public void TC_JOOMLA_CATEGORY_MANAGER_007()
	{
		CategoryPage.navigatemenu(driver, "Content", "Categories", null);
		CategoryPage.clickToolbarButton(driver, "help");
		verifyTrue(CategoryPage.doesHelpPageExist(driver, in_CategoryPage.help_text));
	}
	
	
	@AfterClass
	public void teardown(){
		AdminPage = new ac_AdministratorPage(driver);
		AdminPage.Logout();		
		BrowserExecution.closeJoomla();
	}
	
	private WebDriver driver;
	private ac_LoginPage LoginPage;	
	private ac_CategoryPage CategoryPage;
	private ac_AdministratorPage AdminPage;
	
	private String category_title = randUniqueString("Test Category");
	private String category_title_modified = randUniqueString("Test Category modified");
	private String message_create = "Category successfully saved";
	private String message_archive = "1 category successfully archived";
	private String message_checkin = "1 category successfully checked in";
	private String message_trash = "1 category successfully trashed";
	private String message_publish = "1 category successfully published";
	private String message_unpublish = "1 category successfully unpublished";
}
