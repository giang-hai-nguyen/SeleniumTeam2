package ac_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import config.Config;
import in_pages.in_ArticlesPage;
import in_pages.in_NewArtPage;

public class ac_ArticlePage extends config.AutoElements {
	
	public ac_ArticlePage() {
		// TODO Auto-generated constructor stub
		
	}
	
	public ac_ArticlePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		//driver = this.driver;
		this.driver = driver;
	}
	
	public ac_ArticlePage getActiclePage(WebDriver driver)
	{
		return new ac_ArticlePage(driver);
	}
	/**
	 * @author: TuanNguyen
	 * @edit by: Giang Nguyen, Hang Tran
	 */
	public void fillInfoArticle(String name, String category, String state, String access, String arttext, String saveoption){
		//click(driver, int_ArticlesPage.new_button);
		if (name != null)
			//driver.findElement(By.xpath(int_NewArtPage.title_texbox)).clear();
			clearText(driver, in_NewArtPage.title_texbox);
			enter(driver, in_NewArtPage.title_texbox, name);
		if (category != null){
			selectitems(driver, in_NewArtPage.category_dropdown, category);
			//Select select_category = new Select (driver.findElement(By.xpath(int_NewArtPage.category_dropdown)));
			//select_category.selectByVisibleText(category);
		}
		if (state != null){
			selectitems(driver, in_NewArtPage.status_dropdown, state);
			//Select select_state = new Select (driver.findElement(By.xpath(int_NewArtPage.status_dropdown)));
		    //select_state.selectByVisibleText(state);
		}			
		if (access != null){
			selectitems(driver, in_NewArtPage.access_dropdown, access);
			//Select select_access = new Select (driver.findElement(By.xpath(int_NewArtPage.access_dropdown)));
			//select_access.selectByVisibleText(access);
		}
		if (arttext != null)
			switchToFrame(driver, in_NewArtPage.arttext_frame_textbox);
			driver.findElement(By.xpath(in_NewArtPage.body_frame_textbox)).clear();
			enter(driver, in_NewArtPage.body_frame_textbox, arttext);
			switchBackDefaultframe(driver);
		if (saveoption == "save")
			click(driver, in_NewArtPage.save_button);
		else if (saveoption == "save & close")
			click(driver, in_NewArtPage.saveclose_button);
		else if (saveoption == "save & new")
			click(driver, in_NewArtPage.savenew_button);
		else if (saveoption == "cancel")
			click(driver, in_NewArtPage.cancel_button);
	}
	
	/**
	 * @author: Giang Nguyen
	 * @edit by: 
	 */
	public void checkArticleExist(String message, String title)
	{
		verifyTrue(doesTextPresent(driver, message));
		verifyTrue(doesitemExist(driver, title));
	}
	/**
	 * @author: Giang Nguyen
	 * @edit by: 
	 */
	public void checkArticlePublishStatus(String message, String title, String status)
	{
		verifyTrue(doesTextPresent(driver, message));
		verifyTrue(getitemStatus(driver, in_ArticlesPage.publish_status_icon, title).equals(status));
	}
	/**
	 * @author: Giang Nguyen
	 * @edit by: 
	 */
	public void checkArticleArchived(String message, String title)
	{
		verifyTrue(doesTextPresent(driver, message));
		selectitems(driver, in_ArticlesPage.state_filter_dropdown, "Archived");
		verifyTrue(doesitemExist(driver, title));
	}
	/**
	 * @author: Giang Nguyen
	 * @edit by: 
	 */
	public void checkArticleTrashed(String message, String title)
	{
		verifyTrue(doesTextPresent(driver, message));
		clearText(driver, in_ArticlesPage.filter_textbox);
		selectitems(driver, in_ArticlesPage.state_filter_dropdown, "Trashed");
		verifyTrue(doesitemExist(driver, title));
	}
	public void checkArticleIsPublic(String title, String access)
	{
		verifyTrue(getitemAccessStatus(driver, in_ArticlesPage.access_status, title).contains(access));
	}
	
	/**
	 * @author: Tuan Nguyen
	 * @edit by: 
	 */	
	public ac_ArticlePage selectToolbarButtons(String itemName, String button){
		selectToobarButton(driver, itemName, button);
		if (button =="publish"||button =="unpublish"||button =="archive"||button =="checkin"||button =="trash"){
			waitForControl(driver, in_ArticlesPage.message_header, Config.short_wait_time*10);
		}
		return this;
	}
	
	private WebDriver driver;
}
