package Articles;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import ac_common.*;
import ac_pages.*;
import config.Config;
import in_pages.*;

public class TM_JOOMLA_ARTICLE_001 extends ac_ArticlesPage 
{
	@BeforeClass	
	@Parameters({ "browser" })
	public void Setup(@Optional("firefox") String browser) {
		driver = BrowserExecution.navigateJoomla(browser);
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}
	
	@Test (description = "Verify user can create new article with valid information")
	public void TC_JOOMLA_ARTICLE_001()
	{
		ArticlePage = new ac_ArticlesPage(driver);
		ArticlePage.navigatemenu(driver, "Content", "Articles", null);
		ArticlePage.clickToolbarButton(driver, "new");
		ArticlePage.fillArticleInfo(title, category, state_unpublish, null, arttext);
		ArticlePage.clickToolbarButton(driver, "save");
		verifyTrue(ArticlePage.doesTextPresent(driver, message_create));
		verifyTrue(ArticlePage.doesitemExist(driver, title));
	}
	
	@Test (description = "Verify user can edit an article")
	public void TC_JOOMLA_ARTICLE_002()
	{
		ArticlePage.selectCheckboxItem(driver, title);
		ArticlePage.clickToolbarButton(driver, "edit");
		ArticlePage.fillArticleInfo(title_modified, category, null, null, arttext_modified);
		ArticlePage.clickToolbarButton(driver, "save");
		verifyTrue(ArticlePage.doesTextPresent(driver, message_create));
		verifyTrue(ArticlePage.doesitemExist(driver, title_modified));
	}
	
	@Test (description = "Verify user can publish an unpublished article")
	public void TC_JOOMLA_ARTICLE_003()
	{
		ArticlePage.selectCheckboxItem(driver, title_modified);
		ArticlePage.clickToolbarButton(driver, "publish");
		verifyTrue(ArticlePage.doesTextPresent(driver, message_publish));
		verifyTrue(getitemStatus(driver, in_ArticlesPage.publish_status_icon, title_modified).equals("icon-publish"));
		
	}
	
	@Test (description = "Verify user can unpublish an published article")
	public void TC_JOOMLA_ARTICLE_004()
	{
		ArticlePage.selectCheckboxItem(driver, title_modified);
		ArticlePage.clickToolbarButton(driver, "unpublish");
		verifyTrue(ArticlePage.doesTextPresent(driver, message_unpublish));
		verifyTrue(getitemStatus(driver, in_ArticlesPage.publish_status_icon, title_modified).equals("icon-unpublish"));
	}
	
	@Test (description = "Verify user can move an article to the archive")
	public void TC_JOOMLA_ARTICLE_005()
	{
		ArticlePage.selectCheckboxItem(driver, title_modified);
		ArticlePage.clickToolbarButton(driver, "archive");
		verifyTrue(ArticlePage.doesTextPresent(driver, message_archive));
		ArticlePage.filterArticleByDropdown("Archived", null, null, null);
		verifyTrue(ArticlePage.doesitemExist(driver, title_modified));
	}
	
	
	@Test (description = "Verify user can move an article to trash section")
	public void TC_JOOMLA_ARTICLE_007()
	{
		ArticlePage.selectCheckboxItem(driver, title_modified);
		ArticlePage.clickToolbarButton(driver, "trash");
		verifyTrue(ArticlePage.doesTextPresent(driver, message_trash));
		ArticlePage.filterArticleByDropdown("Trashed", null, null, null);
		verifyTrue(ArticlePage.doesitemExist(driver, title_modified));
	}
	
	
	@Test (description = "Verify user can change the status of articles using the Status column")
	public void TC_JOOMLA_ARTICLE_015()
	{
		ArticlePage.selectCheckboxItem(driver, title_modified);
		ArticlePage.clickStatusIconInTheList(driver, title_modified, in_ArticlesPage.publish_status_icon);
		ArticlePage.filterArticleByDropdown("Published", null, null, null);
		verifyTrue(ArticlePage.doesTextPresent(driver, message_publish));
		verifyTrue(getitemStatus(driver, in_ArticlesPage.publish_status_icon, title_modified).equals("icon-publish"));
		
		//ArticlePage.selectCheckboxItem(driver, title_modified);
		ArticlePage.clickStatusIconInTheList(driver, title_modified, in_ArticlesPage.publish_status_icon);
		ArticlePage.filterArticleByDropdown("Unpublished", null, null, null);
		verifyTrue(ArticlePage.doesTextPresent(driver, message_unpublish));
		verifyTrue(getitemStatus(driver, in_ArticlesPage.publish_status_icon, title_modified).equals("icon-unpublish"));
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
	private ac_ArticlesPage ArticlePage;
	
	private String title = randUniqueString("Test Article");
	private String title_modified = randUniqueString("Test Article modified");
	private String message_create = "Article successfully saved";
	private String arttext = "this is article content";
	private String arttext_modified = "this is article content modified";
	private String category = "- Sample Data-Articles";
	private String message_archive = "1 article archived.";
	private String message_trash = "1 article trashed.";
	private String state_unpublish = "Unpublished";
	private String message_publish = "1 article published.";
	private String message_unpublish = "1 article unpublished.";
}
