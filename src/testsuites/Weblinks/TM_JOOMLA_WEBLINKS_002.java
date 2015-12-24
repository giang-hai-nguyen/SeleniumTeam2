package Weblinks;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ac_common.ac_AdministratorPage;
import ac_pages.ac_LoginPage;
import ac_pages.ac_WeblinksPage;
import config.Config;
import in_pages.in_WeblinksPage;

public class TM_JOOMLA_WEBLINKS_002 extends ac_WeblinksPage {
		
	@BeforeClass
	public void Setup() {
		driver = openBrowser();
		LoginPage = new ac_LoginPage(driver);
		LoginPage.Login(Config.default_username, Config.default_password);
	}
	
	@Test (description = "Verify user can search for weblinks using the filter text field")
	public void TC_JOOMLA_WEBLINKS_009()
	{
		WeblinksPage = new ac_WeblinksPage(driver);
		WeblinksPage.navigatemenu(driver, "Components", "Weblinks", null);
		WeblinksPage.click(driver, in_WeblinksPage.new_button);
		WeblinksPage.fillInfoWeblinks(weblinks_title, weblinks_url, null, "save & close");
		WeblinksPage.searchItem(driver, weblinks_title);
		
		verifyTrue(doesitemExist(driver, weblinks_title));
		clearText(driver, in_WeblinksPage.filter_textbox);
		click(driver, in_WeblinksPage.search_button);
		
	}
	
	@Test (description = "Verify user can search for weblinks using the filter dropdown lists")
	public void TC_JOOMLA_WEBLINKS_010()
	{
		WeblinksPage.selectitems(driver, in_WeblinksPage.status_filter_dropdown, "Published");
		WeblinksPage.selectitems(driver, in_WeblinksPage.category_filter_dropdown, "Sample Data-Weblinks");
	
		verifyTrue(doesitemExist(driver, weblinks_title));
		
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
	private ac_WeblinksPage WeblinksPage;
	
	private String weblinks_title = randUniqueString("Test Weblink");
	private String weblinks_url = "http://www.joomla.org";
}
