package ac_pages;

import org.openqa.selenium.WebDriver;

import config.Config;
import in_pages.in_BannerClientPage;;

public class ac_BannerClientPage extends ac_common.CommonElements{
	public ac_BannerClientPage() {
		// TODO Auto-generated constructor stub
		
	}
	
	public ac_BannerClientPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	
	public ac_BannerClientPage getWeblinksPage(WebDriver driver)
	{
		return new ac_BannerClientPage(driver);
	}
	/**
	 * @author: Hang Tran
	 * @edit by: 
	 */
	public void fillInfoBannerClient(String name, String contact, String email, String status, String saveoption){
		
		if (name != null)
			clearText(driver, in_BannerClientPage.clientname_texbox);
			enter(driver, in_BannerClientPage.clientname_texbox, name);
		if (contact != null)
			clearText(driver, in_BannerClientPage.contact_textbox);
			enter(driver, in_BannerClientPage.contact_textbox, contact);	
		if (email != null)
			clearText(driver, in_BannerClientPage.email_textbox);
			enter(driver, in_BannerClientPage.email_textbox, email);	
		if (status != null)
			selectitems(driver, in_BannerClientPage.status_dropdown, status);
		
		
		if (saveoption == "save")
			click(driver, in_BannerClientPage.save_button);
		else if (saveoption == "save & close")
			click(driver, in_BannerClientPage.saveclose_button);
		else if (saveoption == "save & new")
			click(driver, in_BannerClientPage.savenew_button);
		else if (saveoption == "cancel")
			click(driver, in_BannerClientPage.cancel_button);
	}
	
	 public void checkWeblinksArchived(String message, String title)
	{
		verifyTrue(doesTextPresent(driver, message));
		selectitems(driver, in_BannerClientPage.status_filter_dropdown, "Archived");
		verifyTrue(doesitemExist(driver, title));
	}
	/**
	 * @author: Giang Nguyen
	 * @edit by: 
	 */
	public void checkWeblinksTrashed(String message, String title)
	{
		verifyTrue(doesTextPresent(driver, message));
		clearText(driver, in_BannerClientPage.filter_textbox);
		selectitems(driver, in_BannerClientPage.status_filter_dropdown, "Trashed");
		verifyTrue(doesitemExist(driver, title));
	}
	
	
	/**
	 * @author: Tuan Nguyen
	 * @edit by: 
	 */	
	public ac_BannerClientPage selectToolbarButtons(String itemName, String button){
		selectToobarButton(driver, itemName, button);
		if (button =="publish"||button =="unpublish"||button =="archive"||button =="checkin"||button =="trash"){
			waitForControl(driver, in_BannerClientPage.message_header, Config.short_wait_time*10);
		}
		return this;
	}
	
	private WebDriver driver;
}
