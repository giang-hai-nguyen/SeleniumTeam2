package Contacts;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ac_common.*;
import ac_pages.*;
import config.*;
import in_pages.*;


public class TM_JOOMLA_CONTACT_001 extends ac_ContactsPage
{
	
	@BeforeClass
	@Parameters({ "browser" })
	public void Setup(@Optional("firefox") String browser) {
		driver = BrowserExecution.navigateJoomla(browser);
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}
	
	@Test (description = "Verify user can create new contact with valid information", groups = "regression")
	public void TC_JOOMLA_CONTACTS_001()
	{
		ContactPage = new ac_ContactsPage(driver);
		ContactPage.navigatemenu(driver, "Components", "Contacts", "Contacts");
		ContactPage.clickToolbarButton(driver, "new");
		ContactPage.fillContactInfo(name, category, state_unpublish, featured, null, null);
		ContactPage.clickToolbarButton(driver, "save");
		verifyTrue(ContactPage.doesTextPresent(driver, message_create));
		verifyTrue(ContactPage.doesitemExist(driver, name));
	}
	
	@Test (description = "Verify user can edit a contact", dependsOnMethods = {"TC_JOOMLA_CONTACTS_001"}, groups = "regression")
	public void TC_JOOMLA_CONTACTS_002()
	{
		ContactPage.selectCheckboxItem(driver, name);
		ContactPage.clickToolbarButton(driver,"edit");
		ContactPage.fillContactInfo(name_modified, category_modified, null, null, null, null);
		ContactPage.clickToolbarButton(driver, "save");
		verifyTrue(ContactPage.doesTextPresent(driver, message_create));
		verifyTrue(ContactPage.doesitemExist(driver, name_modified));
	}
	
	@Test (description = "Verify user can publish an unpublished contact", dependsOnMethods = {"TC_JOOMLA_CONTACTS_002"}, groups = "regression")
	public void TC_JOOMLA_CONTACTS_003()
	{
		ContactPage.selectCheckboxItem(driver, name_modified);
		ContactPage.clickToolbarButton(driver, "publish");
		verifyTrue(ContactPage.doesTextPresent(driver, message_publish));
		verifyTrue(getitemStatus(driver, in_ContactsPage.publish_status_icon, name_modified).equals("icon-publish"));
	}
	
	@Test (description = "Verify user can unpublish a published contact", dependsOnMethods = {"TC_JOOMLA_CONTACTS_003"}, groups = "regression")
	public void TC_JOOMLA_CONTACTS_004()
	{
		ContactPage.selectCheckboxItem(driver, name_modified);
		ContactPage.clickToolbarButton(driver, "unpublish");
		verifyTrue(ContactPage.doesTextPresent(driver, message_unpublish));
		verifyTrue(getitemStatus(driver, in_ContactsPage.publish_status_icon, name_modified).equals("icon-unpublish"));
	}
	
	@Test (description = "Verify user can change the status of contacts using the Status column", dependsOnMethods = {"TC_JOOMLA_CONTACTS_004"}, groups = "regression")
	public void TC_JOOMLA_CONTACTS_015()
	{
		ContactPage.selectCheckboxItem(driver, name_modified);
		ContactPage.clickStatusIconInTheList(driver, name_modified, in_ContactsPage.publish_status_icon);
		verifyTrue(ContactPage.doesTextPresent(driver, message_publish));
		verifyTrue(getitemStatus(driver, in_ContactsPage.publish_status_icon, name_modified).equals("icon-publish"));
		
		ContactPage.selectCheckboxItem(driver, name_modified);
		ContactPage.clickStatusIconInTheList(driver, name_modified, in_ContactsPage.publish_status_icon);
		verifyTrue(ContactPage.doesTextPresent(driver, message_unpublish));
		verifyTrue(getitemStatus(driver, in_ContactsPage.publish_status_icon, name_modified).equals("icon-unpublish"));
	}
	
	@Test (description = "Verify user can change the feature property of contacts using the Featured column", dependsOnMethods = {"TC_JOOMLA_CONTACTS_002"}, groups = "regression")
	public void TC_JOOMLA_CONTACTS_016()
	{
		ContactPage.selectCheckboxItem(driver, name_modified);
		ContactPage.clickStatusIconInTheList(driver, name_modified, in_ContactsPage.featured_status_icon);
		verifyTrue(getitemStatus(driver, in_ContactsPage.featured_status_icon, name_modified).equals("icon-unfeatured"));
		
		ContactPage.selectCheckboxItem(driver, name_modified);
		ContactPage.clickStatusIconInTheList(driver, name_modified, in_ContactsPage.featured_status_icon);
		verifyTrue(getitemStatus(driver, in_ContactsPage.featured_status_icon, name_modified).equals("icon-featured"));
	}
	
	@Test (description = "Verify user can move a contact to the archive", dependsOnMethods = {"TC_JOOMLA_CONTACTS_002"}, groups = "regression")
	public void TC_JOOMLA_CONTACTS_005()
	{
		ContactPage.selectCheckboxItem(driver, name_modified);
		ContactPage.clickToolbarButton(driver, "archive");
		verifyTrue(ContactPage.doesTextPresent(driver, message_archive));
		ContactPage.filterContactByDropdown("Archived", null, null, null);
		verifyTrue(ContactPage.doesitemExist(driver, name_modified));
	}
	
	@Test (description ="Verify user can move a contact to trash section", dependsOnMethods = {"TC_JOOMLA_CONTACTS_002"}, groups = "regression")
	public void TC_JOOMLA_CONTACTS_007()
	{
		ContactPage.selectCheckboxItem(driver, name_modified);
		ContactPage.clickToolbarButton(driver, "trash");
		verifyTrue(ContactPage.doesTextPresent(driver, message_trash));
		ContactPage.filterContactByDropdown("Trashed", null, null, null);
		verifyTrue(ContactPage.doesitemExist(driver, name_modified));
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
	private ac_ContactsPage ContactPage;
	
	private String name = randUniqueString("Test Contact");
	private String name_modified = randUniqueString("Test Contact modified");
	private String message_create = "Contact successfully saved";
	private String category = "- Sample Data-Contact";
	private String category_modified = "- - Park Site";
	private String featured = "Yes";
	private String state_unpublish = "Unpublished";
	private String message_publish = "1 contact successfully published";
	private String message_unpublish = "1 contact successfully unpublished";
	private String message_archive = "1 contact successfully archived";
	private String message_trash = "1 contact successfully trashed";
}