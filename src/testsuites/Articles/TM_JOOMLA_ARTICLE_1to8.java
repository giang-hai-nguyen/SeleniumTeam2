package Articles;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import ac_common.*;
import ac_pages.*;
import config.Config;
import in_pages.*;

public class TM_JOOMLA_ARTICLE_1to8 extends ac_ArticlePage {
	
	private WebDriver driver;
	private ac_LoginPage LoginPage;
	private ac_AdministratorPage AdminPage;
	private ac_ArticlePage ArticlePage;
	
	public String title = randUniqueString("Test Article ");
	public String title_modified = randUniqueString("Test Article modified ");
	public String message_create = "Article successfully saved";
	public String arttext = "this is article content";
	public String arttext_modified = "this is article content modified";
	public String category = "- - Sample Data-Articles";
	public String message_archive = "1 article archived.";
	public String message_trash = "1 article trashed.";
	public String state_unpublish = "Unpublished";
	public String state_publish = "Published";
	public String message_publish = "1 article published.";
	public String message_unpublish = "1 article unpublished.";
	public String access_public = "Public";
	
	
	@BeforeClass
	public void Setup() {
		driver = openAUT();
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}
	
	@Test (description = "Verify user can create new article with valid information")
	public void TC_JOOMLA_ARTICLE_001()
	{
		ArticlePage = new ac_ArticlePage(driver);
		ArticlePage.navigatemenu(driver, "Content", "Article Manager", null);
		ArticlePage.clickToolbarButton(driver, "new");
		ArticlePage.fillArticleInfo(title, category, state_unpublish, null, arttext);
		ArticlePage.clickToolbarButton(driver, "save");
		verifyTrue(ArticlePage.doesTextPresent(driver, message_create));
		verifyTrue(ArticlePage.doesitemExist(driver, title));
	}
	/*
	 *Create by:Tuan nguyen 12-03
	 *Edit by: Hang Tran				
	 */
	@Test (description = "Verify user can publish an unpublished article")
	public void TC_JOOMLA_ARTICLE_003()
	{
		ArticlePage.selectCheckboxItem(driver, title);
		ArticlePage.clickToolbarButton(driver, "publish");
		verifyTrue(ArticlePage.doesTextPresent(driver, message_publish));
		verifyTrue(getitemStatus(driver, in_ArticlesPage.publish_status_icon, title).equals(state_publish));
		
	}
	
	@Test (description = "Verify user can edit an article")
	public void TC_JOOMLA_ARTICLE_002()
	{
		ArticlePage.selectCheckboxItem(driver, title);
		ArticlePage.clickToolbarButton(driver, "edit");
		ArticlePage.fillArticleInfo(title_modified, category, state_publish, null, arttext_modified);
		ArticlePage.clickToolbarButton(driver, "save");
		verifyTrue(ArticlePage.doesTextPresent(driver, message_create));
		verifyTrue(ArticlePage.doesitemExist(driver, title_modified));
	}
	
	/*
	 *Create by:Tuan nguyen 12-03
	 *Edit by: Hang Tran	 				
	 */
	@Test (description = "Verify user can unpublish an published article")
	public void TC_JOOMLA_ARTICLE_004()
	{
		ArticlePage.selectCheckboxItem(driver, title_modified);
		ArticlePage.clickToolbarButton(driver, "unpublish");
		verifyTrue(ArticlePage.doesTextPresent(driver, message_unpublish));
		verifyTrue(getitemStatus(driver, in_ArticlesPage.publish_status_icon, title_modified).equals(state_unpublish));
	}
	
	@Test (description = "Verify user can move an article to the archive")
	public void TC_JOOMLA_ARTICLE_005()
	{
		ArticlePage.selectCheckboxItem(driver, title_modified);
		ArticlePage.clickToolbarButton(driver, "archive");
		verifyTrue(ArticlePage.doesTextPresent(driver, message_archive));
		ArticlePage.selectitems(driver, in_ContactsPage.filter_state_dropdown, "Archived");
		verifyTrue(ArticlePage.doesitemExist(driver, title_modified));
	}
	
	
	@Test (description = "Verify user can move an article to trash section")
	public void TC_JOOMLA_ARTICLE_007()
	{
		ArticlePage.selectCheckboxItem(driver, title_modified);
		ArticlePage.clickToolbarButton(driver, "trash");
		verifyTrue(ArticlePage.doesTextPresent(driver, message_trash));
		ArticlePage.selectitems(driver, in_ContactsPage.filter_state_dropdown, "Trash");
		verifyTrue(ArticlePage.doesitemExist(driver, title_modified));
	}
	
	
	@Test (description = "Verify user can change the status of articles using the Status column")
	public void TC_JOOMLA_ARTICLE_015()
	{
		ArticlePage.selectCheckboxItem(driver, title_modified);
		ArticlePage.clickStatusIconInTheList(driver, title_modified, in_ArticlesPage.publish_status_icon);
		verifyTrue(ArticlePage.doesTextPresent(driver, message_publish));
		verifyTrue(getitemStatus(driver, in_ArticlesPage.publish_status_icon, title_modified).equals(state_publish));
		
		ArticlePage.selectCheckboxItem(driver, title_modified);
		ArticlePage.clickStatusIconInTheList(driver, title_modified, in_ArticlesPage.publish_status_icon);
		verifyTrue(ArticlePage.doesTextPresent(driver, message_unpublish));
		verifyTrue(getitemStatus(driver, in_ArticlesPage.publish_status_icon, title_modified).equals(state_unpublish));
	}
		
	@Test (description = "Verify user can create a new article with 'Public' Access Level property")
	public void TC_JOOMLA_ARTICLE_017()
	{
		ArticlePage = new ac_ArticlePage(driver);
		ArticlePage.navigatemenu(driver, "Content", "Article Manager", null);
		ArticlePage.clickToolbarButton(driver, "new");
		ArticlePage.fillArticleInfo(title, category, null, access_public, arttext);
		ArticlePage.clickToolbarButton(driver, "save");
		verifyTrue(ArticlePage.doesTextPresent(driver, message_create));
		verifyTrue(ArticlePage.doesitemExist(driver, title));
		verifyTrue(getTextitem(driver, in_ArticlesPage.access_status, title).contains(access_public));
	}
	
	@AfterClass
	public void teardown(){
		AdminPage = new ac_AdministratorPage(driver);
		AdminPage.Logout();		
		driver.close();
	}
}
