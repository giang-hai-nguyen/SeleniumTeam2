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
	public void fillInfoCategory(String name, String status, String access, String language, String saveoption){
		//click(driver, int_ArticlesPage.new_button);
		if (name != null)
			clearText(driver, in_CategoryPage.title_texbox);
			enter(driver, in_CategoryPage.title_texbox, name);
		if (status != null)
			selectitems(driver, in_CategoryPage.status_dropdown, status);
		if (access != null)
			selectitems(driver, in_CategoryPage.access_status, access);
		if (language !=null)
			selectitems(driver, in_CategoryPage.language_dropdown, language);	
		
		if (saveoption == "save")
			click(driver, in_CategoryPage.save_button);
		else if (saveoption == "save & close")
			click(driver, in_CategoryPage.saveclose_button);
		else if (saveoption == "save & new")
			click(driver, in_CategoryPage.savenew_button);
		else if (saveoption == "cancel")
			click(driver, in_CategoryPage.cancel_button);
	}
	
	 public void checkWeblinksArchived(String message, String title)
	{
		verifyTrue(doesTextPresent(driver, message));
		selectitems(driver, in_CategoryPage.status_filter_dropdown, "Archived");
		verifyTrue(doesitemExist(driver, title));
	}
	/**
	 * @author: Giang Nguyen
	 * @edit by: 
	 */
	public void checkWeblinksTrashed(String message, String title)
	{
		verifyTrue(doesTextPresent(driver, message));
		clearText(driver, in_CategoryPage.filter_textbox);
		selectitems(driver, in_CategoryPage.status_filter_dropdown, "Trashed");
		verifyTrue(doesitemExist(driver, title));
	}
	
	
	/**
	 * @author: Tuan Nguyen
	 * @edit by: 
	 */	
	public ac_CategoryPage selectToolbarButtons(String itemName, String button){
		selectToobarButton(driver, itemName, button);
		if (button =="publish"||button =="unpublish"||button =="archive"||button =="checkin"||button =="trash"){
			waitForControl(driver, in_CategoryPage.message_header, Config.short_wait_time*10);
		}
		return this;
	}
	
	private WebDriver driver;
}
