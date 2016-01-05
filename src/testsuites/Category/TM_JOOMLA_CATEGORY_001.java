package Category;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ac_common.ac_AdministratorPage;
import ac_pages.ac_CategoryPage;
import ac_pages.ac_LoginPage;
import config.Config;
import in_common.in_AdminstratorPage;
import in_pages.in_BannerClientPage;
import in_pages.in_CategoryPage;


public class TM_JOOMLA_CATEGORY_001 extends ac_CategoryPage {
	
	@BeforeClass
	public void Setup() {
		driver = openBrowser();
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
				
		verifyTrue(doesTextPresent(driver, message_create));
		verifyTrue(doesitemExist(driver, category_title));
	}
	
	
	@Test (description= "Verify that user can edit a category", dependsOnMethods = {"TC_JOOMLA_CATEGORY_MANAGER_001"})
	public void TC_JOOMLA_CATEGORY_MANAGER_002()
	{
		CategoryPage.selectCheckboxItemWithoutDiv(driver, category_title);
		CategoryPage.clickToolbarButton(driver, "edit");
		CategoryPage.fillInfoCategory(category_title_modified, null, null, null);
		CategoryPage.clickToolbarButton(driver, "save");
		
		verifyTrue(doesTextPresent(driver, message_create));
		verifyTrue(doesitemExist(driver, category_title_modified));
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
		verifyTrue(CategoryPage.doesitemExist(driver, category_title_modified));
	}
	
//	@Test (description = "Verify that user can send a category to trash")
//	public void TC_JOOMLA_CATEGORY_MANAGER_006()
//	{
//		CategoryPage.selectCheckboxItem(driver, category_title_modified);
//		CategoryPage.click(driver, in_CategoryPage.trash_button);
//	
//		verifyTrue(doesTextPresent(driver, message_trash));
//		clearText(driver, in_CategoryPage.filter_textbox);
//		selectitems(driver, in_CategoryPage.status_filter_dropdown, "Trashed");
//		verifyTrue(doesitemExist(driver, category_title_modified));
//	}
//	
//	
//	@Test (description = "Verify that user can browse category help page")
//	public void TC_JOOMLA_CATEGORY_MANAGER_007()
//	{
//		CategoryPage.navigatemenu(driver, "Content", "Category Manager", null);
//		CategoryPage.click(driver, in_CategoryPage.help_button);
//		verifyTrue(CategoryPage.doesHelpPageExist(driver, in_CategoryPage.help_text));
//	}
//	
//	
//	@AfterClass
//	public void teardown(){
//		AdminPage = new ac_AdministratorPage(driver);
//		AdminPage.Logout();		
//		closeBrowser();
//	}
	
	private WebDriver driver;
	private ac_LoginPage LoginPage;	
	private ac_AdministratorPage AdminPage;
	private ac_CategoryPage CategoryPage;
	
	public String category_title = randUniqueString("Test Category");
	public String category_title_modified = randUniqueString("Test Category modified");
	public String message_create = "Category successfully saved.";
	public String message_archive = "1 category successfully archived.";
	public String message_checkin = "1 category successfully checked in.";
	public String message_trash = "1 category successfully trashed.";
	public String status_unpublish = "Unpublished";
	public String status_publish = "Published";
	public String message_publish = "1 category successfully published.";
	public String message_unpublish = "1 category successfully unpublished.";
}
