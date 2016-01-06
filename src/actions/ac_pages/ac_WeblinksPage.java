package ac_pages;

import org.openqa.selenium.WebDriver;
import config.Config;
import in_pages.in_WeblinksPage;

public class ac_WeblinksPage extends ac_common.CommonElements {
	
	public ac_WeblinksPage() {
		// TODO Auto-generated constructor stub
		
	}
	
	public ac_WeblinksPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	
	public ac_WeblinksPage getWeblinksPage(WebDriver driver)
	{
		return new ac_WeblinksPage(driver);
	}
	/**
	 * @author: Hang Tran
	 * @edit by: 
	 */
	public void fillInfoWeblinks(String name, String url, String status){
		if (name != null)
			clearText(driver, in_WeblinksPage.title_texbox);
			enter(driver, in_WeblinksPage.title_texbox, name);
		if (url != null)
			clearText(driver, in_WeblinksPage.url_texbox);
			enter(driver, in_WeblinksPage.url_texbox, url);
		if (status != null)
			selectitems(driver, in_WeblinksPage.status_dropdown, in_WeblinksPage.status_dropdown_value, status);
							
		
	}
	
	
	private WebDriver driver;
}

