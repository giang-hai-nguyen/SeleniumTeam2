package Articles;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import config.Config;
import ac_common.ac_AdministratorPage;
import ac_pages.ac_ArticlesPage;
import ac_pages.ac_LoginPage;

public class TM_JOOMLA_ARTICLE_003 extends ac_ArticlesPage
{
	@BeforeClass
	public void Setup() {
		driver = openBrowser();
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}
	
	@AfterClass
	public void teardown(){
		AdminPage = new ac_AdministratorPage(driver);
		AdminPage.Logout();		
		driver.close();
	}
	
	@Test(description = "Verify user can search for articles using the filter text field", groups = "regression")
	public void TC_JOOMLA_ARTICLE_009() 
	{
		ArticlePage = new ac_ArticlesPage(driver);
		ArticlePage.navigatemenu(driver, "Content", "Articles", null);
		ArticlePage.clickToolbarButton(driver, "new");
		ArticlePage.fillArticleInfo(title, category, access_public, null, arttext);
		ArticlePage.clickToolbarButton(driver, "save");
		verifyTrue(ArticlePage.doesTextPresent(driver, message_create));
		verifyTrue(ArticlePage.doesitemExist(driver, title));
	}
	
	@Test(description = "Verify user can change the feature property of articles using the Featured column", dependsOnMethods = "TC_JOOMLA_ARTICLE_009", groups = "regression")
	public void TC_JOOMLA_ARTICLE_016() {

		ArticlePage.changeFeatureArticle(title);
		verifyTrue(ArticlePage.doesArticleFeature(title, Config.contact_yes_featured));

		ArticlePage.changeFeatureArticle(title);
		verifyTrue(ArticlePage.doesArticleFeature(title, Config.contact_no_featured));
	}
	
	@Test (description = "Verify user can search for articles using the filter dropdown lists", dependsOnMethods = "TC_JOOMLA_ARTICLE_016", groups = "regression")
	public void TC_JOOMLA_ARTICLE_010() 
	{
		ArticlePage.filterArticleByDropdown(access_public, category.substring(2), null, null);		
		verifyTrue(ArticlePage.doesitemExist(driver, title));
	}
	
	private WebDriver driver;
	private ac_LoginPage LoginPage;
	private ac_AdministratorPage AdminPage;
	private ac_ArticlesPage ArticlePage;
	
	private String title = randUniqueString("Article");
	private String message_create = "Article successfully saved";
	private String arttext = "this is article content";
	private String category = "- Sample Data-Articles";
	private String access_public = "Published";

}
