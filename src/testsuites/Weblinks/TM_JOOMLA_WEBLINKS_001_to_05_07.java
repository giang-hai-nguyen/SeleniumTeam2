package Weblinks;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ac_common.*;
import ac_pages.*;
import config.*;
import in_common.*;
import in_pages.*;

public class TM_JOOMLA_WEBLINKS_001_to_05_07 extends ac_WeblinksPage{
	private WebDriver driver;
	private ac_LoginPage LoginPage;	
	private ac_AdministratorPage AdminPage;
	private ac_WeblinksPage WeblinksPage;
	
	public String weblinks_title = randUniqueString("Test Weblink ");
	public String weblinks_title_modified = randUniqueString("Test Weblink modified ");
	public String weblinks_url = "http://www.joomla.org";
	public String weblinks_url_modified = "http://www.google.com";
	public String message_create = "Weblink successfully saved";
	public String message_archive = "1 weblink successfully archived";
	public String message_checkin = "1 weblink successfully checked in";
	public String message_trash = "1 weblink successfully trashed";
	public String status_unpublish = "Unpublished";
	public String status_publish = "Published";
	public String message_publish = "1 weblink successfully published";
	public String message_unpublish = "1 weblink successfully unpublished";
	
	
	@BeforeClass
	public void Setup() {
		driver = openBrowser();
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}

	@Test(description = "Verify user can create new web link with valid information")
	public void TC_JOOMLA_WEBLINKS_001()
	{
		WeblinksPage = new ac_WeblinksPage(driver);
		WeblinksPage.navigatemenu(driver, "Components", "Weblinks", null);
		WeblinksPage.click(driver, in_WeblinksPage.new_button);
		WeblinksPage.fillInfoWeblinks(weblinks_title, weblinks_url, null, "save & close");
		/*
		 * VP
		 * 1. "Weblink successfully saved" message is displayed
		 * 2. Created weblink is displayed on the weblinks table
		 */
		verifyTrue(doesTextPresent(driver, message_create));
		verifyTrue(doesitemExist(driver, weblinks_title));
	}
	
	
	@Test (description= "Verify user can edit a web link")
	public void TC_JOOMLA_WEBLINKS_002()
	{
		WeblinksPage.selectCheckboxItem(driver, weblinks_title);
		WeblinksPage.click(driver, in_WeblinksPage.edit_button);
		WeblinksPage.fillInfoWeblinks(weblinks_title_modified, weblinks_url_modified, null, "save & close");
		
		/*
		 * VP
		 * 1. "Weblink successfully saved" message is displayed
         * 2. Edited weblink is displayed on the weblinks table
		 */
		verifyTrue(doesTextPresent(driver, message_create));
		verifyTrue(doesitemExist(driver, weblinks_title_modified));
	}
	
	
	@Test (description = "Verify user can unpublish a published web link")
	public void TC_JOOMLA_WEBLINKS_003()
	{
		WeblinksPage.selectCheckboxItem(driver, weblinks_title_modified);
		WeblinksPage.clickToolbarButton(driver, in_AdminstratorPage.toolbar_unpublish);
		/*
		 * VP
		 * 1. The icon of the selected item is showed as 'Unpublish'. 
		 * 2. The "1 weblink successfully unpublished" message is displayed
		 */		
		verifyTrue(doesTextPresent(driver, message_unpublish));
		verifyTrue(getitemStatus(driver, in_WeblinksPage.publish_status_icon, weblinks_title_modified).equals("state unpublish"));
		
	}

	
	@Test (description = "Verify user can publish an unpublished web link")
	public void TC_JOOMLA_WEBLINKS_004()
	{
		WeblinksPage.selectCheckboxItem(driver, weblinks_title_modified);
		WeblinksPage.clickToolbarButton(driver, in_AdminstratorPage.toolbar_publish);
		/*
		 * VP
		 * 1. "Article successfully saved" message is displayed
		 * 2. Verify the article is unpublished successfully
		 */
		verifyTrue(doesTextPresent(driver, message_publish));
		verifyTrue(getitemStatus(driver, in_WeblinksPage.publish_status_icon, weblinks_title_modified).equals("state publish"));
	}
	
	
	
	@Test (description = "Verify user can move a web link to the archive")
	public void TC_JOOMLA_WEBLINKS_005()
	{
		WeblinksPage.selectCheckboxItem(driver, weblinks_title_modified);
		WeblinksPage.click(driver, in_WeblinksPage.archive_button);
		/*
		 * VP
		 * 1. The "1 article archived" message is displayed
		 * 2. The archived article is displayed on the table grid
		 */
		verifyTrue(doesTextPresent(driver, message_archive));
		selectitems(driver, in_WeblinksPage.status_filter_dropdown, "Archived");
		verifyTrue(doesitemExist(driver, weblinks_title_modified));
	}
	
	@Test (description = "Verify user can check-in a weblink")
	public void TC_JOOMLA_WEBLINKS_006()
	{
		WeblinksPage.selectCheckboxItem(driver, weblinks_title_modified);
		WeblinksPage.click(driver, in_WeblinksPage.checkin_button);
		/*
		 * VP
		 * 1. The "1 article successfully trashed" message is displayed
		 * 2. The deleted article is displayed on the table grid
		 */
		verifyTrue(doesTextPresent(driver, message_checkin));
		clearText(driver, in_WeblinksPage.filter_textbox);
//		selectitems(driver, int_WeblinksPage.status_filter_dropdown, "Trashed");
//		verifyTrue(doesitemExist(driver, weblinks_title_modified));
	}
	
	
	@Test (description = "Verify user can move a web link to trash section")
	public void TC_JOOMLA_WEBLINKS_007()
	{
		WeblinksPage.selectCheckboxItem(driver, weblinks_title_modified);
		WeblinksPage.click(driver, in_WeblinksPage.trash_button);
		/*
		 * VP
		 * 1. The "1 article successfully trashed" message is displayed
		 * 2. The deleted article is displayed on the table grid
		 */
		verifyTrue(doesTextPresent(driver, message_trash));
		clearText(driver, in_WeblinksPage.filter_textbox);
		selectitems(driver, in_WeblinksPage.status_filter_dropdown, "Trashed");
		verifyTrue(doesitemExist(driver, weblinks_title_modified));
	}
	
	
	@Test (description = "Verify user can access weblink's help section")
	public void TC_JOOMLA_WEBLINKS_008()
	{
		WeblinksPage.click(driver, in_WeblinksPage.help_button);
		/*
		 * VP
		 * 1. The weblink's help window is displayed
		*/
		
		
	}
	
	//@AfterClass
	public void teardown(){
		AdminPage = new ac_AdministratorPage(driver);
		AdminPage.Logout();		
		driver.close();
	}
}
