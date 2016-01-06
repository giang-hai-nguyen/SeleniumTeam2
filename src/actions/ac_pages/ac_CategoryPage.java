package ac_pages;

import org.openqa.selenium.WebDriver;

import config.Config;
import in_pages.in_CategoryPage;



public class ac_CategoryPage extends ac_common.CommonElements {
	public ac_CategoryPage() {
		// TODO Auto-generated constructor stub
		
	}
	
	public ac_CategoryPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	
	public ac_CategoryPage getWeblinksPage(WebDriver driver)
	{
		return new ac_CategoryPage(driver);
	}
	/**
	 * @author: Hang Tran
	 * @edit by: 
	 */
	public void fillInfoCategory(String name, String status, String access, String language){
		
		if (name != null)
			clearText(driver, in_CategoryPage.title_texbox);
			enter(driver, in_CategoryPage.title_texbox, name);
		if (status != null)
			selectitems(driver, in_CategoryPage.status_dropdown, in_CategoryPage.status_dropdown_values, status);
		if (access != null)
			selectitems(driver, in_CategoryPage.access_dropdown, in_CategoryPage.access_dropdown_values, access);
		if (language !=null)
			selectitems(driver, in_CategoryPage.language_dropdown, in_CategoryPage.language_dropdown_values, language);
		//wait to enter info
		waitForPageLoad(Config.short_wait_time);
	}
	
	
	public void filterCategoryByDropdown(String status, String access, String language)
	{
		if (status != null)
		{
			selectitems(driver, in_CategoryPage.filter_status_dropdown, in_CategoryPage.filter_status_dropdown_values, status);
			waitForPageLoad(Config.short_wait_time/2);
		}
		if (access != null)
		{
			selectitems(driver, in_CategoryPage.filter_access_dropdown, in_CategoryPage.filter_access_dropdown_values, access);
			waitForPageLoad(Config.short_wait_time/2);
		}
		if (language != null)
		{
			selectitems(driver, in_CategoryPage.filter_language_dropdown,in_CategoryPage.filter_language_dropdown_values, language);
			waitForPageLoad(Config.short_wait_time/2);
		}
	}
	
	
	private WebDriver driver;
}
