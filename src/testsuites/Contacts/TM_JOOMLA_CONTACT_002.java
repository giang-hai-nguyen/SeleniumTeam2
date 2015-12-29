package Contacts;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ac_common.*;
import ac_pages.*;
import in_pages.*;
import config.Config;

public class TM_JOOMLA_CONTACT_002 extends ac_ContactsPage
{
	@BeforeClass
	public void Setup() {
		driver = openBrowser();
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}
	
	@Test (description = "Verify user can check in a contact")
	public void TC_JOOMLA_CONTACTS_006()
	{
		ContactPage = new ac_ContactsPage(driver);
		ContactPage.navigatemenu(driver, "Components", "Contacts", "Contacts");
		ContactPage.clickToolbarButton(driver, "new");
		ContactPage.fillContactInfo(name, null, null, null, null);
		ContactPage.clickToolbarButton(driver, "apply");
		verifyTrue(ContactPage.doesTextPresent(driver, message_create));
		ContactPage.clickToolbarButton(driver, "cancel");
		verifyTrue(ContactPage.doesitemExist(driver, name));
		
		ContactPage.selectCheckboxItem(driver, name);
		ContactPage.clickToolbarButton(driver, "checkin");
		verifyTrue(ContactPage.doesTextPresent(driver, message_checkin));
		verifyTrue(ContactPage.getitemStatus(driver, in_ContactsPage.checkin_status_icon, name).equals(state_checkin));
	}
	
	@Test (description = "Verify user can access contact's help section")
	public void TC_JOOMLA_CONTACTS_008()
	{
		ContactPage.navigatemenu(driver, "Components", "Contacts", "Contacts");
		clickToolbarButton(driver, "help");
		verifyTrue(ContactPage.doesHelpPageExist(driver, in_ContactsPage.help_text));
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
	private ac_ContactsPage ContactPage;
		
	private String name = randUniqueString("Test Contact");
	private String message_create = "Contact successfully saved";
	private String message_checkin = "1 contact successfully checked in";
	private String state_checkin = "state checkedout";
	
}
