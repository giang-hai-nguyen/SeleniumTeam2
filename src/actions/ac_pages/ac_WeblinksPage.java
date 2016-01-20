package ac_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
	public void fillInfoWeblinks(String name, String url, String status, String image, String publish_date){
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
		if (image != null)
			{
				selecttabs(driver, in_WeblinksPage.otherInfo_tabs, "Images");
				click(driver, in_WeblinksPage.select_button);
				waitForPageLoad(Config.short_wait_time/2);
				switchToFrame(driver, in_WeblinksPage.image_frame);
				enter(driver, in_WeblinksPage.image_frame_url_textbox, "images/"+ image);
				waitForPageLoad(Config.short_wait_time);
				click(driver, in_WeblinksPage.image_frame_insert_button);
				waitForPageLoad(Config.short_wait_time);
				switchBackDefaultframe(driver);
			}
		if (publish_date != null)
		{
			selecttabs(driver, in_WeblinksPage.otherInfo_tabs, "Publishing");
			click(driver, in_WeblinksPage.publish_date_textbox);
			driver.findElement(By.xpath("//div/input[@id='jform_publish_up']")).clear(); 
			enter(driver, in_WeblinksPage.publish_date_textbox, publish_date);
			
		}
	
	}
	
	public String getPublishDate(WebDriver driver, String control)
	 {
		WebElement element  = driver.findElement(By.xpath("//div/input[@id='jform_publish_up']"));
		String elementval = element .getAttribute("value");
		 return elementval;
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
	
	public boolean verifyCheckInStateBanner(String weblinks, String state){
		return verifyCheckInState(driver, weblinks, state);
	}
	
	
	private WebDriver driver;
}

