package ac_common;

import org.openqa.selenium.WebDriver;

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
	
	public void Logout(){		
		click(driver, in_common.in_AdminstratorPage.drop_open);
		click(driver, in_common.in_AdminstratorPage.logout_icon);
	}
	
}
