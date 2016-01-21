package Weblinks;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ac_common.*;
import ac_pages.*;
import config.*;
import in_pages.in_WeblinksPage;

public class TM_JOOMLA_WEBLINKS_001 extends ac_WeblinksPage{
	
	@BeforeClass
	@Parameters({ "browser" })
	public void Setup(@Optional("firefox") String browser) {
		driver = BrowserExecution.navigateJoomla(browser);
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}

	@Test(description = "Verify user can create new web link with valid information", groups = "regression")
	public void TC_JOOMLA_WEBLINKS_001()
	{
		WeblinksPage = new ac_WeblinksPage(driver);
		WeblinksPage.navigatemenu(driver, "Components", "Weblinks", null);
		WeblinksPage.clickToolbarButton(driver, "new");
		WeblinksPage.fillInfoWeblinks (weblinks_title, weblinks_url, null, null, null);
		WeblinksPage.clickToolbarButton(driver, "save");
		
		verifyTrue(WeblinksPage.doesTextPresent(driver, message_create));
		verifyTrue(WeblinksPage.doesitemExist(driver, weblinks_title));
	}
	
	
	@Test (description= "Verify user can edit a web link", groups = "regression")
	public void TC_JOOMLA_WEBLINKS_002()
	{
		WeblinksPage.selectCheckboxItemWithoutDiv(driver, weblinks_title);
		WeblinksPage.clickToolbarButton(driver, "edit");
		WeblinksPage.fillInfoWeblinks (weblinks_title_modified, weblinks_url_modified, null, null, null);
		WeblinksPage.clickToolbarButton(driver, "save");
		
		verifyTrue(doesTextPresent(driver, message_create));
		verifyTrue(doesitemExist(driver, weblinks_title_modified));
	}
	
	
	@Test (description = "Verify user can unpublish a published web link", groups = "regression")
	public void TC_JOOMLA_WEBLINKS_003()
	{
		WeblinksPage.selectCheckboxItemWithoutDiv(driver, weblinks_title_modified);
		WeblinksPage.clickToolbarButton(driver, "unpublish");
			
		verifyTrue(doesTextPresent(driver, message_unpublish));
		verifyTrue(getitemStatus(driver, in_WeblinksPage.publish_status_icon, weblinks_title_modified).equals("icon-unpublish"));	
	}

	@Test (description = "Verify user can publish an unpublished web link", groups = "regression")
	public void TC_JOOMLA_WEBLINKS_004()
	{
		WeblinksPage.selectCheckboxItemWithoutDiv(driver, weblinks_title_modified);
		WeblinksPage.clickToolbarButton(driver, "publish");
		
		verifyTrue(doesTextPresent(driver, message_publish));
		verifyTrue(getitemStatus(driver, in_WeblinksPage.publish_status_icon, weblinks_title_modified).equals("icon-publish"));
	}
	
	@Test (description = "Verify user can move a web link to the archive", groups = "regression")
	public void TC_JOOMLA_WEBLINKS_005()
	{
		WeblinksPage.selectCheckboxItemWithoutDiv(driver, weblinks_title_modified);
		WeblinksPage.clickToolbarButton(driver, "archive");
		
		verifyTrue(doesTextPresent(driver, message_archive));
		WeblinksPage.filterWeblinksByDropdown("Archived", null, null);
		verifyTrue(WeblinksPage.doesitemExist(driver, weblinks_title_modified));	
	}
	
	
	@Test (description = "Verify user can move a web link to trash section", groups = "regression")
	public void TC_JOOMLA_WEBLINKS_007()
	{
		WeblinksPage.selectCheckboxItemWithoutDiv(driver, weblinks_title_modified);
		WeblinksPage.clickToolbarButton(driver, "trash");
	
		verifyTrue(doesTextPresent(driver, message_trash));
		WeblinksPage.filterWeblinksByDropdown("Trashed", null, null);
		verifyTrue(doesitemExist(driver, weblinks_title_modified));
	}
	
	
	@Test (description = "Verify user can access weblink's help section", groups = "regression")
	public void TC_JOOMLA_WEBLINKS_008()
	{
		WeblinksPage.navigatemenu(driver, "Components", "Weblinks", null);
		WeblinksPage.clickToolbarButton(driver, "help");
		verifyTrue(WeblinksPage.doesHelpPageExist(driver, in_WeblinksPage.help_text));	
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
	private ac_WeblinksPage WeblinksPage;
	
	private String weblinks_title = randUniqueString("Test Weblink");
	private String weblinks_title_modified = randUniqueString("Test Weblink modified");
	private String weblinks_url = "http://www.joomla.org";
	private String weblinks_url_modified = "http://www.google.com";
	private String message_create = "Web link successfully saved";
	private String message_archive = "1 web link successfully archived";
	private String message_trash = "1 web link successfully trashed";
	private String message_publish = "1 web link successfully published";
	private String message_unpublish = "1 web link successfully unpublished";
}
