package Articles;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ac_common.ac_AdministratorPage;
import ac_pages.ac_ArticlesPage;
import ac_pages.ac_LoginPage;
import config.Config;
import in_pages.in_ArticlesPage;

public class TM_JOOMLA_ARTICLE_002 extends ac_ArticlesPage
{
	@BeforeClass
	public void Setup() {
		driver = openBrowser();
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}
	
	@Test (description = "Verify user can create a new article with 'Public' Access Level property")
	public void TC_JOOMLA_ARTICLE_017()
	{
		ArticlePage = new ac_ArticlesPage(driver);
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
	
	private WebDriver driver;
	private ac_LoginPage LoginPage;
	private ac_AdministratorPage AdminPage;
	private ac_ArticlesPage ArticlePage;
	
	private String title = randUniqueString("Article");
	private String message_create = "Article successfully saved";
	private String arttext = "this is article content";
	private String category = "- - Sample Data-Articles";
	private String access_public = "Public";
}
