package ac_pages;

import org.openqa.selenium.WebDriver;
import config.Config;
import in_pages.in_CategoryPage;
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
			waitForPageLoad(Config.short_wait_time);
		if (url != null)
			clearText(driver, in_WeblinksPage.url_texbox);
			enter(driver, in_WeblinksPage.url_texbox, url);
			waitForPageLoad(Config.short_wait_time);
		if (status != null)
			selectitems(driver, in_WeblinksPage.status_dropdown, in_WeblinksPage.status_dropdown_value, status);
			waitForPageLoad(Config.short_wait_time);						
	}
	
	public void filterWeblinksByDropdown(String status, String category, String access)
	{
		if (status != null)
		{
			selectitems(driver, in_WeblinksPage.status_filter_dropdown, in_WeblinksPage.status_filter_dropdown_value, status);
			waitForPageLoad(Config.short_wait_time);
		}
		if (category != null)
		{
			selectitems(driver, in_WeblinksPage.category_filter_dropdown, in_WeblinksPage.category_filter_dropdown_value, category);
			waitForPageLoad(Config.short_wait_time);
		}
		if (access != null)
		{
			selectitems(driver, in_WeblinksPage.access_filter_dropdown,in_WeblinksPage.access_filter_dropdown_value, access);
			waitForPageLoad(Config.short_wait_time);
		}
	}
	
	
	private WebDriver driver;
}

