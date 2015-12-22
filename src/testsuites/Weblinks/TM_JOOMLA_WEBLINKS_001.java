package testsuite_Weblinks;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import actions.ac_AdministratorPage;
import actions.ac_ArticlePage;
import actions.ac_LoginPage;
import actions.ac_WeblinksPage;
import commons.Config;
import interfaces.*;

public class TM_JOOMLA_WEBLINKS_001 extends ac_WeblinksPage{
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
		driver = openAUT();
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}

	@Test(description = "Verify user can create new web link with valid information")
	public void TC_JOOMLA_WEBLINKS_001()
	{
		WeblinksPage = new ac_WeblinksPage(driver);
		WeblinksPage.navigatemenu(driver, "Components", "Weblinks", null);
		WeblinksPage.click(driver, int_WeblinksPage.new_button);
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
		WeblinksPage.click(driver, int_WeblinksPage.edit_button);
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
		WeblinksPage.clickToolbarButton(driver, int_AdminstratorPage.toolbar_unpublish);
		/*
		 * VP
		 * 1. The icon of the selected item is showed as 'Unpublish'. 
		 * 2. The "1 weblink successfully unpublished" message is displayed
		 */		
		verifyTrue(doesTextPresent(driver, message_unpublish));
		verifyTrue(getitemStatus(driver, int_WeblinksPage.publish_status_icon, weblinks_title_modified).equals("state unpublish"));
		
	}

	
	@Test (description = "Verify user can publish an unpublished web link")
	public void TC_JOOMLA_WEBLINKS_004()
	{
		WeblinksPage.selectCheckboxItem(driver, weblinks_title_modified);
		WeblinksPage.clickToolbarButton(driver, int_AdminstratorPage.toolbar_publish);
		/*
		 * VP
		 * 1. "Article successfully saved" message is displayed
		 * 2. Verify the article is unpublished successfully
		 */
		verifyTrue(doesTextPresent(driver, message_publish));
		verifyTrue(getitemStatus(driver, int_WeblinksPage.publish_status_icon, weblinks_title_modified).equals("state publish"));
	}
	
	
	
	@Test (description = "Verify user can move a web link to the archive")
	public void TC_JOOMLA_WEBLINKS_005()
	{
		WeblinksPage.selectCheckboxItem(driver, weblinks_title_modified);
		WeblinksPage.click(driver, int_WeblinksPage.archive_button);
		/*
		 * VP
		 * 1. The "1 article archived" message is displayed
		 * 2. The archived article is displayed on the table grid
		 */
		verifyTrue(doesTextPresent(driver, message_archive));
		selectitems(driver, int_WeblinksPage.status_filter_dropdown, "Archived");
		verifyTrue(doesitemExist(driver, weblinks_title_modified));
	}
	
	@Test (description = "Verify user can check-in a weblink")
	public void TC_JOOMLA_WEBLINKS_006()
	{
		WeblinksPage.selectCheckboxItem(driver, weblinks_title_modified);
		WeblinksPage.click(driver, int_WeblinksPage.checkin_button);
		/*
		 * VP
		 * 1. The "1 article successfully trashed" message is displayed
		 * 2. The deleted article is displayed on the table grid
		 */
		verifyTrue(doesTextPresent(driver, message_checkin));
		clearText(driver, int_WeblinksPage.filter_textbox);
//		selectitems(driver, int_WeblinksPage.status_filter_dropdown, "Trashed");
//		verifyTrue(doesitemExist(driver, weblinks_title_modified));
	}
	
	
	@Test (description = "Verify user can move a web link to trash section")
	public void TC_JOOMLA_WEBLINKS_007()
	{
		WeblinksPage.selectCheckboxItem(driver, weblinks_title_modified);
		WeblinksPage.click(driver, int_WeblinksPage.trash_button);
		/*
		 * VP
		 * 1. The "1 article successfully trashed" message is displayed
		 * 2. The deleted article is displayed on the table grid
		 */
		verifyTrue(doesTextPresent(driver, message_trash));
		clearText(driver, int_WeblinksPage.filter_textbox);
		selectitems(driver, int_WeblinksPage.status_filter_dropdown, "Trashed");
		verifyTrue(doesitemExist(driver, weblinks_title_modified));
	}
	
	
//	@Test
		//Verify user can change the status of articles using the Status column
//		public void TC_JOOMLA_ARTICLE_015()
//		{
//			ArticlePage.selectCheckboxItem(driver, title_modified);
//			ArticlePage.clickStatusIconInTheList(driver, title_modified, int_ArticlesPage.publish_status_icon);
//			/*
//			 * VP
//			 * 1. The icon of the selected item is showed as 'unpublish'. 
//			 * 2. The "1 article unpublished" message is displayed
//			 */
//			ArticlePage.checkArticlePublishStatus(message_unpublish, title_modified, "state unpublish");
//			
//			ArticlePage.selectCheckboxItem(driver, title_modified);
//			ArticlePage.clickStatusIconInTheList(driver, title_modified, int_ArticlesPage.publish_status_icon);
//			/*
//			 * VP
//			 * 1. The icon of the selected item is showed as 'Publish'. 
//			 * 2. The "1 article published" message is displayed
//			 */
//			ArticlePage.checkArticlePublishStatus(message_publish, title_modified, "state publish");
//		}
		
//		@Test
		//Verify user can create a new article with 'Public' Access Level property
//		public void TC_JOOMLA_ARTICLE_017()
//		{
//			ArticlePage = new ac_ArticlePage(driver);
//			ArticlePage.navigatemenu(driver, "Content", "Article Manager", null);
//			ArticlePage.click(driver, int_ArticlesPage.new_button);
//			ArticlePage.fillInfoArticle(title, category, null, access_public, arttext, "save & close");
			/*
			 * VP
			 * 1. "Article successfully saved" message is displayed
			 * 2. Created article is displayed on the articles table
			 * 3. The Access Level of the article is displayed as 'Public'
			 */
//			ArticlePage.checkArticleExist(message_create, title);
//			ArticlePage.checkArticleIsPublic(title, "Public");
//		}
	
	//@AfterClass
	public void teardown(){
		AdminPage = new ac_AdministratorPage(driver);
		AdminPage.Logout();		
		driver.close();
	}
}
