package ac_common;

import org.openqa.selenium.WebDriver;
import ac_pages.ac_LoginPage;
import config.Config;

public class ac_AdministratorPage extends ac_common.CommonElements{
	
	private WebDriver driver;
	
	public ac_AdministratorPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		//driver = this.driver;
		this.driver = driver;
	}
	public ac_AdministratorPage getAdminPage(WebDriver driver)
	{
		return new ac_AdministratorPage(driver);
	}
	
	public ac_LoginPage Logout(){
		click(driver, in_common.in_AdminstratorPage.drop_open);
		waitForPageLoad(Config.short_wait_time);
		click(driver, in_common.in_AdminstratorPage.logout_icon);
		waitForPageLoad(Config.short_wait_time);
		return new ac_LoginPage(driver);
	}
	
}
