package ac_pages;

import org.openqa.selenium.WebDriver;

public class ac_LoginPage extends config.AutoElements{
		
	public ac_LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		//driver = this.driver;
		this.driver = driver;
	}
	
	public ac_LoginPage getLoginPage(WebDriver driver)
	{
		return new ac_LoginPage(driver);
	}
	
	public void Login (String Username, String Password){
		enter(driver, in_pages.in_LoginPage.username_textbox, Username);
		enter(driver, in_pages.in_LoginPage.password_textbox, Password);
		click(driver,in_pages.in_LoginPage.login_button);
	}
	
	private WebDriver driver;
}
