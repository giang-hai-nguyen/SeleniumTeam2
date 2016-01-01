package Articles;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import config.Config;
import ac_common.ac_AdministratorPage;
import ac_pages.ac_ArticlesPage;
import ac_pages.ac_LoginPage;

public class TM_JOOMLA_ARTICLE_004 extends ac_ArticlesPage
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
	
	@Test (description = "Verify user can sort the article table by ID column", groups = "regression")
	public void TC_JOOMLA_ARTICLE_011() {

		ArticlePage = new ac_ArticlesPage(driver);		
		ArticlePage.navigatemenu(driver, "Content", "Articles", null);
		ArticlePage.clickToolbarButton(driver, "new");
		ArticlePage.fillArticleInfo(title1, category1, access_public, null, arttext);
		ArticlePage.clickToolbarButton(driver, "save");
		
		ArticlePage.navigatemenu(driver, "Content", "Articles", null);
		ArticlePage.clickToolbarButton(driver, "new");
		ArticlePage.fillArticleInfo(title2, category2, access_public, null, arttext);
		ArticlePage.clickToolbarButton(driver, "save");
		
		searchItem(driver, "Article");
		ArticlePage.clickArticleHeaderID();
		verifyTrue(ArticlePage.doesSortingIDAscending());
		ArticlePage.clickArticleHeaderID();
		verifyTrue(ArticlePage.doesSortingIDDescending());
	}
	
	@Test (description = "Verify user can paging the articles using the paging control", dependsOnMethods = "TC_JOOMLA_ARTICLE_011", groups = "regression")
	public void TC_JOOMLA_ARTICLE_012() {
		
		verifyTrue(ArticlePage.doesPagingAll());
		verifyTrue(ArticlePage.doesPagingNumber(displaypagenumber));

	}
	
	private WebDriver driver;
	private ac_LoginPage LoginPage;
	private ac_AdministratorPage AdminPage;
	private ac_ArticlesPage ArticlePage;
	
	private String title1 = randUniqueString("Article order1");
	private String title2 = randUniqueString("Article order2");
	private String arttext = "this is article content";
	private String category1 = "- Sample Data-Articles";
	private String category2 = "- - Joomla!";
	private String access_public = "Published";
	private int displaypagenumber = 5;

}
