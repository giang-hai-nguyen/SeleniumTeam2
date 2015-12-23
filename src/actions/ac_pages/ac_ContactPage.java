package ac_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import in_pages.in_ContactsPage;

public class ac_ContactPage extends ac_common.CommonElements {
	private WebDriver driver;
	
	public ac_ContactPage() {
		// TODO Auto-generated constructor stub
	}
	public ac_ContactPage(WebDriver driver) {
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

}