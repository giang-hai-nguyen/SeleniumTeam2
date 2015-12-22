package testsuite_Articles;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import actions.ac_AdministratorPage;
import actions.ac_ArticlePage;
import actions.ac_LoginPage;
import commons.Config;
import interfaces.int_AdminstratorPage;
import interfaces.int_ArticlesPage;

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
	public String category = "- Sample Data-Articles";
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
	
	@Test
	//Verify user can create new article with valid information
	public void TC_JOOMLA_ARTICLE_001()
	{
		ArticlePage = new ac_ArticlePage(driver);
		ArticlePage.navigatemenu(driver, "Content", "Article Manager", null);
		ArticlePage.click(driver, int_ArticlesPage.new_button);
		ArticlePage.fillInfoArticle(title, category, null, null, arttext, "save & close");
		/*
		 * VP
		 * 1. "Article successfully saved" message is displayed
		 * 2. Created article is displayed on the articles table
		 */
		ArticlePage.checkArticleExist(message_create, title);
	}
	
	@Test
	//Verify user can edit an article
	public void TC_JOOMLA_ARTICLE_002()
	{
		ArticlePage.selectCheckboxItem(driver, title);
		ArticlePage.click(driver, int_ArticlesPage.edit_button);
		ArticlePage.fillInfoArticle(title_modified, category, null, null, arttext_modified, "save & close");
		
		/*
		 * VP
		 * 1. "Article successfully saved" message is displayed
		 * 2. Edited article is displayed on the articles table
		 */
		ArticlePage.checkArticleExist(message_create, title_modified);
	}
	/*
	 *Create by:Tuan nguyen 12-03
	 *Edit by: Hang Tran
	 *Verify user can publish an unpublished article				
	 */
	@Test
	public void TC_JOOMLA_ARTICLE_003()
	{
		//ArticlePage.click(driver, int_ArticlesPage.new_button);
		//ArticlePage.fillInfoArticle(title_modified, category, state_unpublish, null, arttext_modified, "save & close");
		ArticlePage.selectToolbarButtons(title_modified, int_AdminstratorPage.toolbar_publish);
		/*
		 * VP
		 * 1. "Article successfully saved" message is displayed
		 * 2. Verify the article is published successfully
		 */		
		ArticlePage.checkArticlePublishStatus(message_publish, title_modified, "state publish");
		
	}
	/*
	 *Create by:Tuan nguyen 12-03
	 *Edit by: Hang Tran	 
	 *Verify user can upublish an published article				
	 */
	@Test
	public void TC_JOOMLA_ARTICLE_004()
	{
		ArticlePage.selectToolbarButtons(title_modified, int_AdminstratorPage.toolbar_unpublish);
		/*
		 * VP
		 * 1. "Article successfully saved" message is displayed
		 * 2. Verify the article is unpublished successfully
		 */
		ArticlePage.checkArticlePublishStatus(message_unpublish, title_modified, "state unpublish");
	}
	
	@Test
	//Verify user can move an article to the archive
	public void TC_JOOMLA_ARTICLE_005()
	{
		ArticlePage.selectCheckboxItem(driver, title_modified);
		ArticlePage.click(driver, int_ArticlesPage.archive_button);
		/*
		 * VP
		 * 1. The "1 article archived" message is displayed
		 * 2. The archived article is displayed on the table grid
		 */
		ArticlePage.checkArticleArchived(message_archive, title_modified);
	}
	
	
	@Test
	//Verify user can move an article to trash section
	public void TC_JOOMLA_ARTICLE_007()
	{
		ArticlePage.selectCheckboxItem(driver, title_modified);
		ArticlePage.click(driver, int_ArticlesPage.trash_button);
		/*
		 * VP
		 * 1. The "1 article successfully trashed" message is displayed
		 * 2. The deleted article is displayed on the table grid
		 */
		ArticlePage.checkArticleTrashed(message_trash, title_modified);
	}
	
	
//	@Test
		//Verify user can change the status of articles using the Status column
		public void TC_JOOMLA_ARTICLE_015()
		{
			ArticlePage.selectCheckboxItem(driver, title);
			ArticlePage.clickStatusIconInTheList(driver, title, int_ArticlesPage.publish_status_icon);
			/*
			 * VP
			 * 1. The icon of the selected item is showed as 'unpublish'. 
			 * 2. The "1 article unpublished" message is displayed
			 */
			ArticlePage.checkArticlePublishStatus(message_unpublish, title, "state unpublish");
			
			ArticlePage.selectCheckboxItem(driver, title);
			ArticlePage.clickStatusIconInTheList(driver, title, int_ArticlesPage.publish_status_icon);
			/*
			 * VP
			 * 1. The icon of the selected item is showed as 'Publish'. 
			 * 2. The "1 article published" message is displayed
			 */
			ArticlePage.checkArticlePublishStatus(message_publish, title, "state publish");
		}
		
//		@Test
		//Verify user can create a new article with 'Public' Access Level property
		public void TC_JOOMLA_ARTICLE_017()
		{
			ArticlePage = new ac_ArticlePage(driver);
			ArticlePage.navigatemenu(driver, "Content", "Article Manager", null);
			ArticlePage.click(driver, int_ArticlesPage.new_button);
			ArticlePage.fillInfoArticle(title, category, null, access_public, arttext, "save & close");
			/*
			 * VP
			 * 1. "Article successfully saved" message is displayed
			 * 2. Created article is displayed on the articles table
			 * 3. The Access Level of the article is displayed as 'Public'
			 */
			ArticlePage.checkArticleExist(message_create, title);
			ArticlePage.checkArticleIsPublic(title, "Public");
		}
	
	@AfterClass
	public void teardown(){
		AdminPage = new ac_AdministratorPage(driver);
		AdminPage.Logout();		
		driver.close();
	}
}
