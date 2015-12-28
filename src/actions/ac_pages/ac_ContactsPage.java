package ac_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import config.Config;
import in_pages.in_ContactsPage;

public class ac_ContactsPage extends ac_common.CommonElements {
	private WebDriver driver;
	
	public ac_ContactsPage() {
		// TODO Auto-generated constructor stub
	}
	public ac_ContactsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	
//	public ac_ContactPage getContactPage(WebDriver driver)
//	{
//		return new ac_ContactPage(driver);
//	}
	/**
	 * @author: GiangNguyen
	 * @edit by:
	 */
	public void fillContactInfo(String name, String category, String state, String access, String otherInfo)
	{
		if (name != null)
			clearText(driver, in_ContactsPage.name_textbox);
			enter(driver, in_ContactsPage.name_textbox, name);
		if (category != null){
			selectitems(driver, in_ContactsPage.category_dropdown, category);
		}
		if (state != null){
			selectitems(driver, in_ContactsPage.state_dropdown, state);
		}			
		if (access != null){
			selectitems(driver, in_ContactsPage.access_dropdown, access);
		}
		if (otherInfo != null)
		{
			switchToFrame(driver, in_ContactsPage.otherinfo_iframe);
			driver.findElement(By.xpath(in_ContactsPage.frame_textbox)).clear();
			enter(driver, in_ContactsPage.frame_textbox, otherInfo);
			switchBackDefaultframe(driver);
		}
	}
	/**
	 * @author: GiangNguyen
	 * @edit by:
	 */
	public void fillContactInfo(String name, String category, String state, String access, String otherInfo, String image)
	{
		if (name != null)
			clearText(driver, in_ContactsPage.name_textbox);
			enter(driver, in_ContactsPage.name_textbox, name);
		if (category != null){
			selectitems(driver, in_ContactsPage.category_dropdown, category);
		}
		if (state != null){
			selectitems(driver, in_ContactsPage.state_dropdown, state);
		}			
		if (access != null){
			selectitems(driver, in_ContactsPage.access_dropdown, access);
		}
		if (otherInfo != null)
		{
			switchToFrame(driver, in_ContactsPage.otherinfo_iframe);
			driver.findElement(By.xpath(in_ContactsPage.frame_textbox)).clear();
			enter(driver, in_ContactsPage.frame_textbox, otherInfo);
			switchBackDefaultframe(driver);
		}
		if (image != null)
		{
			click(driver, in_ContactsPage.image_frame_image_button);
			switchToFrame(driver, in_ContactsPage.image_frame);
			enter(driver, in_ContactsPage.image_frame_url_textbox, "images/"+ image);
			click(driver, in_ContactsPage.image_frame_insert_button);
			switchBackDefaultframe(driver);
			waitForPageLoad(Config.short_wait_time);
		}
	}

}