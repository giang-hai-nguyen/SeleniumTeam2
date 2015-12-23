package Contacts;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ac_common.*;
import ac_pages.*;
import config.*;
import in_pages.*;


public class TM_JOOMLA_CONTACT_001 extends ac_ContactPage
{
	
	@BeforeClass
	public void Setup() {
		driver = openBrowser();
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}
	
	@Test (description = "Verify user can create new contact with valid information")
	public void TC_JOOMLA_CONTACTS_001()
	{
		ContactPage = new ac_ContactPage(driver);
		ContactPage.navigatemenu(driver, "Components", "Contacts", "Contacts");
		ContactPage.clickToolbarButton(driver, "new");
		ContactPage.fillContactInfo(name, category, state_unpublish, null, null);
		ContactPage.clickToolbarButton(driver, "save");
		verifyTrue(ContactPage.doesTextPresent(driver, message_create));
		verifyTrue(ContactPage.doesitemExist(driver, name));
	}
	
	@Test (description = "Verify user can edit a contact")
	public void TC_JOOMLA_CONTACTS_002()
	{
		ContactPage.selectCheckboxItem(driver, name);
		ContactPage.clickToolbarButton(driver,"edit");
		ContactPage.fillContactInfo(name_modified, category_modified, null, null, null);
		ContactPage.clickToolbarButton(driver, "save");
		verifyTrue(ContactPage.doesTextPresent(driver, message_create));
		verifyTrue(ContactPage.doesitemExist(driver, name_modified));
	}
	
	@Test (description = "Verify user can publish an unpublished contact")
	public void TC_JOOMLA_CONTACTS_003()
	{
		ContactPage.selectCheckboxItem(driver, name_modified);
		ContactPage.clickToolbarButton(driver, "publish");
		verifyTrue(ContactPage.doesTextPresent(driver, message_publish));
		verifyTrue(getitemStatus(driver, in_ContactsPage.publish_status_icon, name_modified).equals("state publish"));
	}
	
	@Test (description = "Verify user can unpublish a published contact")
	public void TC_JOOMLA_CONTACTS_004()
	{
		ContactPage.selectCheckboxItem(driver, name_modified);
		ContactPage.clickToolbarButton(driver, "unpublish");
		verifyTrue(ContactPage.doesTextPresent(driver, message_unpublish));
		verifyTrue(getitemStatus(driver, in_ContactsPage.publish_status_icon, name_modified).equals("state unpublish"));
	}
	
	@Test (description = "Verify user can move a contact to the archive")
	public void TC_JOOMLA_CONTACTS_005()
	{
		ContactPage.selectCheckboxItem(driver, name_modified);
		ContactPage.clickToolbarButton(driver, "archive");
		verifyTrue(ContactPage.doesTextPresent(driver, message_archive));
		ContactPage.selectitems(driver, in_ContactsPage.filter_state_dropdown, "Archived");
		verifyTrue(ContactPage.doesitemExist(driver, name_modified));
	}
	
	@Test (description ="Verify user can move a contact to trash section")
	public void TC_JOOMLA_CONTACTS_007()
	{
		ContactPage.selectCheckboxItem(driver, name_modified);
		ContactPage.clickToolbarButton(driver, "trash");
		verifyTrue(ContactPage.doesTextPresent(driver, message_trash));
		ContactPage.selectitems(driver, in_ContactsPage.filter_state_dropdown, "Trashed");
		verifyTrue(ContactPage.doesitemExist(driver, name_modified));
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
	private ac_ContactPage ContactPage;
	
	public String name = randUniqueString("Test Contact ");
	public String name_modified = randUniqueString("Test Contact modified ");
	public String message_create = "Contact successfully saved";
	public String category = "- Sample Data-Contact";
	public String category_modified = "- - Park Site";
	public String state_unpublish = "Unpublished";
	public String state_publish = "Published";
	public String message_publish = "1 contact successfully published";
	public String message_unpublish = "1 contact successfully unpublished";
	public String message_archive = "1 contact archived";
	public String message_trash = "1 contact successfully trashed";
}