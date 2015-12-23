package Contacts;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ac_common.*;
import ac_pages.*;
import in_pages.*;
import config.Config;

public class TM_JOOMLA_CONTACT_002 extends ac_ContactPage
{
	@BeforeClass
	public void Setup() {
		driver = openBrowser();
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}
		
	/*
	 *Create by: Giang Nguyen
	 *Edit by:				
	 */
	@Test (description = "Verify user can check in a contact")
	public void TC_JOOMLA_CONTACTS_006()
	{
		ContactPage = new ac_ContactPage(driver);
		ContactPage.navigatemenu(driver, "Components", "Contacts", "Contacts");
		ContactPage.clickToolbarButton(driver, "new");
		ContactPage.fillContactInfo(name, category, null, null, null);
		ContactPage.clickToolbarButton(driver, "apply");
		verifyTrue(ContactPage.doesTextPresent(driver, message_create));
		ContactPage.clickToolbarButton(driver, "cancel");
		verifyTrue(ContactPage.doesitemExist(driver, name));
		
		ContactPage.selectCheckboxItem(driver, name);
		ContactPage.clickToolbarButton(driver, "checkin");
		verifyTrue(ContactPage.doesTextPresent(driver, message_checkin));
		verifyTrue(ContactPage.getitemStatus(driver, in_ContactsPage.checkin_status_icon, name).equals(state_checkin));
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
	public String category = "Sample Data-Contact";
	public String state_unpublish = "Unpublished";
	public String state_publish = "Published";
	public String message_checkin = "1 contact successfully checked in";
	public String state_checkin = "state checkedout";
	
}
