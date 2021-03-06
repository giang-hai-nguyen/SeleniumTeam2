package ac_pages;

import org.openqa.selenium.WebDriver;

import config.Config;

public class ac_LoginPage extends ac_common.CommonElements{
		
	public ac_LoginPage(WebDriver driver) {
        this.driver = driver;
    }
	
//	public ac_LoginPage getLoginPage(WebDriver driver)
//	{
//		return new ac_LoginPage(driver);
//	}
	
	public void Login (String Username, String Password){
		enter(driver, in_pages.in_LoginPage.username_textbox, Username);
		enter(driver, in_pages.in_LoginPage.password_textbox, Password);
		click(driver,in_pages.in_LoginPage.login_button);
		waitForPageLoad(Config.long_wait_time);
	}
	
	private WebDriver driver;
}
