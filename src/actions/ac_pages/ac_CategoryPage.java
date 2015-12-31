package ac_pages;

import org.openqa.selenium.WebDriver;

import config.Config;
import in_pages.in_CategoryPage;
import in_pages.in_ContactsPage;


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
	
//	 public void checkWeblinksArchived(String message, String title)
//	{
//		verifyTrue(doesTextPresent(driver, message));
//		selectitems(driver, in_CategoryPage.status_filter_dropdown, "Archived");
//		verifyTrue(doesitemExist(driver, title));
//	}
//	/**
//	 * @author: Giang Nguyen
//	 * @edit by: 
//	 */
//	public void checkWeblinksTrashed(String message, String title)
//	{
//		verifyTrue(doesTextPresent(driver, message));
//		clearText(driver, in_CategoryPage.filter_textbox);
//		selectitems(driver, in_CategoryPage.status_filter_dropdown, "Trashed");
//		verifyTrue(doesitemExist(driver, title));
//	}
//	
//	
//	/**
//	 * @author: Tuan Nguyen
//	 * @edit by: 
//	 */	
//	public ac_CategoryPage selectToolbarButtons(String itemName, String button){
//		selectToobarButton(driver, itemName, button);
//		if (button =="publish"||button =="unpublish"||button =="archive"||button =="checkin"||button =="trash"){
//			waitForControl(driver, in_CategoryPage.message_header, Config.short_wait_time*10);
//		}
//		return this;
//	}
	
	private WebDriver driver;
}
