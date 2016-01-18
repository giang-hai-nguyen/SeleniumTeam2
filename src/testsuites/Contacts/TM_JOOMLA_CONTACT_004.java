package Contacts;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ac_common.BrowserExecution;
import ac_common.ac_AdministratorPage;
import ac_pages.ac_ContactsPage;
import ac_pages.ac_LoginPage;
import config.Config;

public class TM_JOOMLA_CONTACT_004 extends ac_ContactsPage {
	
	@BeforeClass
	@Parameters({ "browser" })
	public void Setup(@Optional("firefox") String browser) {
		driver = BrowserExecution.navigateJoomla(browser);
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
		ContactPage = new ac_ContactsPage(driver);
		ContactPage.createNewContact(name, category, status, null, null, "save & close");
		verifyTrue(ContactPage.doesTextPresent(driver, message_create));
		verifyTrue(ContactPage.doesitemExist(driver, name));
	}
	
	@Test (description = "Verify user can search for contacts using the filter text field")
	public void TC_JOOMLA_CONTACTS_009()
	{
		
		ContactPage.filterContactByTextbox(name);
		verifyTrue(ContactPage.doesContactExist(name));
	}
	
	@Test (description = "User can search for contacts using the filter dropdown lists")
	public void TC_JOOMLA_CONTACTS_010()
	{	
		ContactPage.filterContactByDropdown(status, filter_category, null, null);
		verifyTrue(ContactPage.doesitemExist(driver, name));
		
	}
	
	@AfterClass
	public void teardown(){
		AdminPage = new ac_AdministratorPage(driver);
		AdminPage.Logout();
		BrowserExecution.closeJoomla();
	}
		
	@AfterMethod
	public void cleanSearchOption()
	{
		ContactPage.cleanSearch();
	}
	
	
	
	private WebDriver driver;
	private ac_LoginPage LoginPage;	
	private ac_AdministratorPage AdminPage;
	private ac_ContactsPage ContactPage;
		
	private String name = randUniqueString("Test Contact");
	private String message_create = "Contact successfully saved";
	private String status = "Published";
	private String category = "- Sample Data-Contact";
	private String filter_category = "Sample Data-Contact";

}
