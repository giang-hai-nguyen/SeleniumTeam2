package Contacts;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ac_common.*;
import ac_pages.*;
import in_pages.*;
import config.Config;

public class TM_JOOMLA_CONTACT_002 extends ac_ContactsPage
{
	@BeforeClass
	@Parameters({ "browser" })
	public void Setup(@Optional("firefox") String browser) {
		driver = BrowserExecution.navigateJoomla(browser);
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}
	
	@Test (description = "Verify user can check in a contact", groups = "regression")
	@Parameters({ "browser" })
	public void TC_JOOMLA_CONTACTS_006(@Optional("firefox") String browser)
	{
		ContactPage = new ac_ContactsPage(driver);
		ContactPage.navigatemenu(driver, "Components", "Contacts", "Contacts");
		ContactPage.clickToolbarButton(driver, "new");
		ContactPage.fillContactInfo(name, null, null, null, null, null);
		ContactPage.clickToolbarButton(driver, "apply");
		verifyTrue(ContactPage.doesTextPresent(driver, message_create));
		
		driver.close();
		driver = BrowserExecution.navigateJoomla(browser);
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
		
		ContactPage.selectCheckboxItem(driver, name);
		verifyTrue(ContactPage.verifyCheckInStateContact(name, "locked"));
		
		ContactPage.clickToolbarButton(driver, "checkin");
		verifyTrue(ContactPage.doesTextPresent(driver, message_checkin));
		verifyFalse(ContactPage.verifyCheckInStateContact(name, "unlocked"));
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
	private String message_create = "Contact successfully saved";
	private String message_checkin = "1 contact successfully checked in";
	private String state_checkin = "icon-checkedout";
	
}
