package Contacts;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ac_common.BrowserExecution;
import ac_common.ac_AdministratorPage;
import ac_pages.ac_ContactsPage;
import ac_pages.ac_LoginPage;
import config.Config;
import in_pages.in_ContactsPage;

public class TM_JOOMLA_CONTACT_003 extends ac_ContactsPage {
	
	@BeforeClass
	@Parameters({ "browser" })
	public void Setup(@Optional("firefox") String browser) {
		driver = BrowserExecution.navigateJoomla(browser);
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}
	
	@Test (description = "Verify user can access contact's help section")
	public void TC_JOOMLA_CONTACTS_008()
	{
		ContactPage = new ac_ContactsPage(driver);
		ContactPage.navigatemenu(driver, "Components", "Contacts", "Contacts");
		clickToolbarButton(driver, "help");
		verifyTrue(ContactPage.doesHelpPageExist(driver, in_ContactsPage.help_text));
	}
	
	@Test (description = "Verify user can add image to contact's information")
	public void TC_JOOMLA_CONTACTS_013()
	{
		//ContactPage = new ac_ContactsPage(driver);
		ContactPage.navigatemenu(driver, "Components", "Contacts", "Contacts");
		ContactPage.clickToolbarButton(driver, "new");
		ContactPage.fillContactInfo(name, category, null, null, null, "powered_by.png");
		ContactPage.clickToolbarButton(driver, "save");
		verifyTrue(ContactPage.doesTextPresent(driver, message_create));
		verifyTrue(ContactPage.doesitemExist(driver, name));
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
	private String category = "- Sample Data-Contact";
	private String name = randUniqueString("Test Contact");
	private String message_create = "Contact successfully saved";

}
