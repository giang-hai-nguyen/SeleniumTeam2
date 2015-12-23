package ac_pages;

import org.openqa.selenium.WebDriver;

import config.Config;
import in_pages.in_WeblinksPage;

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
		return new ac_WeblinksPage(driver);
	}
	/**
	 * @author: Hang Tran
	 * @edit by: 
	 */
	public void fillInfoWeblinks(String name, String url, String status, String saveoption){
		//click(driver, int_ArticlesPage.new_button);
		if (name != null)
			//driver.findElement(By.xpath(int_NewArtPage.title_texbox)).clear();
			clearText(driver, ac_CategoryPage.title_texbox);
			enter(driver, ac_CategoryPage.title_texbox, name);
		if (url != null)
				//driver.findElement(By.xpath(int_NewArtPage.title_texbox)).clear();
				clearText(driver, ac_CategoryPage.url_texbox);
				enter(driver, ac_CategoryPage.url_texbox, url);
		if (status != null){
			selectitems(driver, ac_CategoryPage.status_dropdown, status);
			//Select select_state = new Select (driver.findElement(By.xpath(int_NewArtPage.status_dropdown)));
		    //select_state.selectByVisibleText(state);
		}		
		
		if (saveoption == "save")
			click(driver, ac_CategoryPage.save_button);
		else if (saveoption == "save & close")
			click(driver, ac_CategoryPage.saveclose_button);
		else if (saveoption == "save & new")
			click(driver, ac_CategoryPage.savenew_button);
		else if (saveoption == "cancel")
			click(driver, ac_CategoryPage.cancel_button);
	}
	
	 public void checkWeblinksArchived(String message, String title)
	{
		verifyTrue(doesTextPresent(driver, message));
		selectitems(driver, ac_CategoryPage.status_filter_dropdown, "Archived");
		verifyTrue(doesitemExist(driver, title));
	}
	/**
	 * @author: Giang Nguyen
	 * @edit by: 
	 */
	public void checkWeblinksTrashed(String message, String title)
	{
		verifyTrue(doesTextPresent(driver, message));
		clearText(driver, ac_CategoryPage.filter_textbox);
		selectitems(driver, ac_CategoryPage.status_filter_dropdown, "Trashed");
		verifyTrue(doesitemExist(driver, title));
	}
	
	
	/**
	 * @author: Tuan Nguyen
	 * @edit by: 
	 */	
	public ac_CategoryPage selectToolbarButtons(String itemName, String button){
		selectToobarButton(driver, itemName, button);
		if (button =="publish"||button =="unpublish"||button =="archive"||button =="checkin"||button =="trash"){
			waitForControl(driver, in_WeblinksPage.message_header, Config.short_wait_time*10);
		}
		return this;
	}
	
	private WebDriver driver;
}
