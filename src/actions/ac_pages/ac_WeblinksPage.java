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
	public void fillInfoWeblinks(String name, String url, String status, String saveoption){
		//click(driver, int_ArticlesPage.new_button);
		if (name != null)
			clearText(driver, in_WeblinksPage.title_texbox);
			enter(driver, in_WeblinksPage.title_texbox, name);
		if (url != null)
			clearText(driver, in_WeblinksPage.url_texbox);
			enter(driver, in_WeblinksPage.url_texbox, url);
		if (status != null)
			selectitems(driver, in_WeblinksPage.status_dropdown, status);
							
		if (saveoption == "save")
			click(driver, in_WeblinksPage.save_button);
		else if (saveoption == "save & close")
			click(driver, in_WeblinksPage.saveclose_button);
		else if (saveoption == "save & new")
			click(driver, in_WeblinksPage.savenew_button);
		else if (saveoption == "cancel")
			click(driver, in_WeblinksPage.cancel_button);
	}
	
	 public void checkWeblinksArchived(String message, String title)
	{
		verifyTrue(doesTextPresent(driver, message));
		selectitems(driver, in_WeblinksPage.status_filter_dropdown, "Archived");
		verifyTrue(doesitemExist(driver, title));
	}
	/**
	 * @author: Giang Nguyen
	 * @edit by: 
	 */
	public void checkWeblinksTrashed(String message, String title)
	{
		verifyTrue(doesTextPresent(driver, message));
		clearText(driver, in_WeblinksPage.filter_textbox);
		selectitems(driver, in_WeblinksPage.status_filter_dropdown, "Trashed");
		verifyTrue(doesitemExist(driver, title));
	}
	
	
	/**
	 * @author: Tuan Nguyen
	 * @edit by: 
	 */	
	public ac_WeblinksPage selectToolbarButtons(String itemName, String button){
		selectToobarButton(driver, itemName, button);
		if (button =="publish"||button =="unpublish"||button =="archive"||button =="checkin"||button =="trash"){
			waitForControl(driver, in_WeblinksPage.message_header, Config.short_wait_time*10);
		}
		return this;
	}
	
	private WebDriver driver;
}

