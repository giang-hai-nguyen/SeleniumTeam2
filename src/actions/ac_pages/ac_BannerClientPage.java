package ac_pages;

import org.openqa.selenium.WebDriver;

import config.Config;
import in_pages.in_BannerClientPage;
import in_pages.in_CategoryPage;;

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
	public void fillInfoBannerClient(String name, String contact, String email, String status){
		
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
			selectitems(driver, in_BannerClientPage.status_dropdown, in_BannerClientPage.status_dropdown_values, status);
	}
	
	public void filterBannerClientByDropdown(String status)
	{
		if (status != null)
		{
			selectitems(driver, in_BannerClientPage.filter_status_dropdown, in_BannerClientPage.filter_status_dropdown_values, status);
			waitForPageLoad(Config.short_wait_time);
		}
	}
	
	private WebDriver driver;
}
