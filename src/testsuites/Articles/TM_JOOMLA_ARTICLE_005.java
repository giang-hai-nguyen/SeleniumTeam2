package Articles;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ac_common.BrowserExecution;
import ac_common.ac_AdministratorPage;
import ac_pages.ac_ArticlesPage;
import ac_pages.ac_LoginPage;
import config.Config;
import in_pages.in_ArticlesPage;

public class TM_JOOMLA_ARTICLE_005 extends ac_ArticlesPage {

	@BeforeClass	
	@Parameters({ "browser" })
	public void Setup(@Optional("firefox") String browser) {
		driver = BrowserExecution.navigateJoomla(browser);
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}
	
	@Test (description = "Verify user can check-in an article")
	@Parameters({ "browser" })
	public void TC_JOOMLA_ARTICLE_006(@Optional("firefox") String browser)
	{
		ArticlePage = new ac_ArticlesPage(driver);
		ArticlePage.navigatemenu(driver, "Content", "Articles", null);
		ArticlePage.clickToolbarButton(driver, "new");
		ArticlePage.fillArticleInfo(title, category, state_publish, null, arttext);
		ArticlePage.clickToolbarButton(driver, "apply");
		verifyTrue(ArticlePage.doesTextPresent(driver, message_create));

		driver.close();
		driver = BrowserExecution.navigateJoomla(browser);
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
		
		ArticlePage.selectCheckboxItem(driver, title);
		verifyTrue(ArticlePage.getitemStatus(driver, in_ArticlesPage.checkin_status_icon, title).equals(state_checkin));
		ArticlePage.clickToolbarButton(driver, "checkin");
		verifyTrue(ArticlePage.doesTextPresent(driver, message_checkin));
		verifyFalse(ArticlePage.getitemStatus(driver, in_ArticlesPage.checkin_status_icon, title).equals(state_checkin));
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
	private String message_create = "Article successfully saved";
	private String arttext = "this is article content";
	private String category = "- Sample Data-Articles";
	private String message_checkin = "1 article successfully checked in";
	private String state_publish = "Published";
	private String state_checkin = "icon-checkedout";
}
