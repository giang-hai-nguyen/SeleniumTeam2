package Category;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ac_common.ac_AdministratorPage;
import ac_pages.ac_CategoryPage;
import ac_pages.ac_LoginPage;
import config.Config;
import in_common.in_AdminstratorPage;
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
		CategoryPage.navigatemenu(driver, "Content", "Category Manager", null);
		CategoryPage.click(driver, in_CategoryPage.new_button);
		CategoryPage.fillInfoCategory(category_title, null, null, "save & close");
		
		verifyTrue(doesTextPresent(driver, message_create));
		verifyTrue(doesitemExist(driver, category_title));
	}
	
	
	@Test (description= "Verify that user can edit a category")
	public void TC_JOOMLA_CATEGORY_MANAGER_002()
	{
		CategoryPage.selectCheckboxItem(driver, category_title);
		CategoryPage.click(driver, in_CategoryPage.edit_button);
		CategoryPage.fillInfoCategory(category_title_modified, null, null, "save & close");
		
		verifyTrue(doesTextPresent(driver, message_create));
		verifyTrue(doesitemExist(driver, category_title_modified));
	}
	
	
	@Test (description = "Verify that user can unpublish a category")
	public void TC_JOOMLA_CATEGORY_MANAGER_003()
	{
		CategoryPage.selectCheckboxItem(driver, category_title_modified);
		CategoryPage.clickToolbarButton(driver, in_AdminstratorPage.toolbar_unpublish);
			
		verifyTrue(doesTextPresent(driver, message_unpublish));
		verifyTrue(getitemStatus(driver, in_CategoryPage.publish_status_icon, category_title_modified).equals("state unpublish"));
		
	}

	@Test (description = "Verify that user can publish a category")
	public void TC_JOOMLA_CATEGORY_MANAGER_004()
	{
		CategoryPage.selectCheckboxItem(driver, category_title_modified);
		CategoryPage.clickToolbarButton(driver, in_AdminstratorPage.toolbar_publish);
		
		verifyTrue(doesTextPresent(driver, message_publish));
		verifyTrue(getitemStatus(driver, in_CategoryPage.publish_status_icon, category_title_modified).equals("state publish"));
	}
	
	@Test (description = "Verify that user can archive a category")
	public void TC_JOOMLA_CATEGORY_MANAGER_005()
	{
		CategoryPage.selectCheckboxItem(driver, category_title_modified);
		CategoryPage.click(driver, in_CategoryPage.archive_button);
		
		verifyTrue(doesTextPresent(driver, message_archive));
		selectitems(driver, in_CategoryPage.status_filter_dropdown, "Archived");
		verifyTrue(doesitemExist(driver, category_title_modified));
	}
	
	@Test (description = "Verify that user can send a category to trash")
	public void TC_JOOMLA_CATEGORY_MANAGER_006()
	{
		CategoryPage.selectCheckboxItem(driver, category_title_modified);
		CategoryPage.click(driver, in_CategoryPage.trash_button);
	
		verifyTrue(doesTextPresent(driver, message_trash));
		clearText(driver, in_CategoryPage.filter_textbox);
		selectitems(driver, in_CategoryPage.status_filter_dropdown, "Trashed");
		verifyTrue(doesitemExist(driver, category_title_modified));
	}
	
	
	@Test (description = "Verify that user can browse category help page")
	public void TC_JOOMLA_CATEGORY_MANAGER_007()
	{
		CategoryPage.click(driver, in_CategoryPage.help_button);
		
	
		
	}
	
	//@AfterClass
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
	public String message_create = "Category successfully saved";
	public String message_archive = "1 category successfully archived";
	public String message_checkin = "1 category successfully checked in";
	public String message_trash = "1 category successfully trashed";
	public String status_unpublish = "Unpublished";
	public String status_publish = "Published";
	public String message_publish = "1 category successfully published";
	public String message_unpublish = "1 category successfully unpublished";
}
