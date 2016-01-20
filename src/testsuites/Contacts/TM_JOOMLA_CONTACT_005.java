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

public class TM_JOOMLA_CONTACT_005 extends ac_ContactsPage {

	@BeforeClass
	@Parameters({ "browser" })
	public void Setup(@Optional("firefox") String browser) {
		driver = BrowserExecution.navigateJoomla(browser);
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}
	
	@AfterClass
	public void teardown(){
		AdminPage = new ac_AdministratorPage(driver);
		AdminPage.Logout();
		BrowserExecution.closeJoomla();
	}
	
	@Test (description = "Verify user can change the order of contacts using the Ordering column", groups = "regression")
	public void TC_JOOMLA_CONTACT_014() {
		
		ContactPage = new ac_ContactsPage(driver);		
		ContactPage.createNewContact(title1, category, null, null, null, null, "save & close");
		verifyTrue(ContactPage.doesTextPresent(driver, message_create));
		verifyTrue(ContactPage.doesitemExist(driver, title1));
		
		ContactPage.createNewContact(title2, category, null, null, null, null, "save & close");
		verifyTrue(ContactPage.doesTextPresent(driver, message_create));
		verifyTrue(ContactPage.doesitemExist(driver, title2));
		
		searchItem(driver, search_title);
		verifyTrue(ContactPage.doesOrderTwoContacts(title2, title1));

		ContactPage.clickHeaderOrderButton();
		verifyTrue(ContactPage.doesOrderTwoContacts(title1, title2));

	}
	
	@Test (description = "Verify user can sort the contacts table by ID column", dependsOnMethods = "TC_JOOMLA_CONTACT_014", groups = "regression")
	public void TC_JOOMLA_CONTACT_011() {

		ContactPage.clickContactHeaderID();
		verifyTrue(ContactPage.doesSortingIDAscending());
		ContactPage.clickContactHeaderID();
		verifyTrue(ContactPage.doesSortingIDDescending());
	}
	
	@Test (description = "Verify user can paging the contacts using the paging control", dependsOnMethods = "TC_JOOMLA_CONTACT_011", groups = "regression")
	public void TC_JOOMLA_CONTACT_012() {
		
		verifyTrue(ContactPage.doesPagingNumber(displaypagenumber));
		verifyTrue(ContactPage.doesPagingAll());

	}
	
	private WebDriver driver;
	private ac_LoginPage LoginPage;
	private ac_AdministratorPage AdminPage;
	private ac_ContactsPage ContactPage;
	
	private String message_create = "Contact successfully saved";
	private String title1 = randUniqueString("Contact order1");
	private String title2 = randUniqueString("Contact order2");
	private String search_title = "Contact order";
	private String category = "- Sample Data-Contact";
	private int displaypagenumber = 5;
}
