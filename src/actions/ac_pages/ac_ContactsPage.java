package ac_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import config.Config;
import in_common.in_AdminstratorPage;
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
			selectitems(driver, in_ContactsPage.category_dropdown, in_ContactsPage.category_dropdown_values, category);
		}
		if (state != null){
			selectitems(driver, in_ContactsPage.state_dropdown,in_ContactsPage.state_dropdown_values, state);
		}			
		if (access != null){
			selectitems(driver, in_ContactsPage.access_dropdown,in_ContactsPage.access_dropdown_values, access);
		}
		if (otherInfo != null)
		{
			selecttabs(driver, in_ContactsPage.otherInfo_tabs, "Miscellaneous Information");
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
			selectitems(driver, in_ContactsPage.category_dropdown, in_ContactsPage.category_dropdown_values, category);
		}
		if (state != null){
			selectitems(driver, in_ContactsPage.state_dropdown,in_ContactsPage.state_dropdown_values, state);
		}			
		if (access != null){
			selectitems(driver, in_ContactsPage.access_dropdown,in_ContactsPage.access_dropdown_values, access);
		}
		if (otherInfo != null)
		{
			selecttabs(driver, in_ContactsPage.otherInfo_tabs, "Miscellaneous Information");
			switchToFrame(driver, in_ContactsPage.otherinfo_iframe);
			driver.findElement(By.xpath(in_ContactsPage.frame_textbox)).clear();
			enter(driver, in_ContactsPage.frame_textbox, otherInfo);
			switchBackDefaultframe(driver);
		}
		if (image != null)
		{
			selecttabs(driver, in_ContactsPage.otherInfo_tabs, "Miscellaneous Information");
			click(driver, in_ContactsPage.image_frame_image_button);
			waitForPageLoad(Config.short_wait_time/2);
			switchToFrame(driver, in_ContactsPage.image_frame);
			enter(driver, in_ContactsPage.image_frame_url_textbox, "images/"+ image);
			click(driver, in_ContactsPage.image_frame_insert_button);
			waitForPageLoad(Config.short_wait_time/2);
			switchBackDefaultframe(driver);
		}
	}
	
	public void createNewContact(String name, String category, String state, String access, String otherInfo, String saveOption)
	{
		navigatemenu(driver, "Components", "Contacts", "Contacts");
		clickToolbarButton(driver, "new");
		if (name != null)
			clearText(driver, in_ContactsPage.name_textbox);
			enter(driver, in_ContactsPage.name_textbox, name);
		if (category != null){
			selectitems(driver, in_ContactsPage.category_dropdown, in_ContactsPage.category_dropdown_values, category);
		}
		if (state != null){
			selectitems(driver, in_ContactsPage.state_dropdown,in_ContactsPage.state_dropdown_values, state);
		}			
		if (access != null){
			selectitems(driver, in_ContactsPage.access_dropdown,in_ContactsPage.access_dropdown_values, access);
		}
		if (otherInfo != null)
		{
			selecttabs(driver, in_ContactsPage.otherInfo_tabs, "Miscellaneous Information");
			switchToFrame(driver, in_ContactsPage.otherinfo_iframe);
			driver.findElement(By.xpath(in_ContactsPage.frame_textbox)).clear();
			enter(driver, in_ContactsPage.frame_textbox, otherInfo);
			switchBackDefaultframe(driver);
		}
		if (saveOption != null)
		{
			if (saveOption == "save")
				clickToolbarButton(driver, in_AdminstratorPage.toolbar_save);
			else if (saveOption == "save & close")
				clickToolbarButton(driver, in_AdminstratorPage.toolbar_saveandclose);
			else if (saveOption == "save & new")
				clickToolbarButton(driver, in_AdminstratorPage.toolbar_saveandnew);
			else if (saveOption == "cancel")
				clickToolbarButton(driver, in_AdminstratorPage.toolbar_cancel);
		}
	}
	
	public void filterContactByTextbox(String text)
	{
		driver.findElement(By.xpath(in_AdminstratorPage.filter_textbox)).clear();
		enter(driver, in_AdminstratorPage.filter_textbox, text);
		click(driver, in_AdminstratorPage.search_button);
	}
	
	public void filterContactByDropdown(String status, String category, String access, String language)
	{
		if (status != null)
		{
			selectitems(driver, in_ContactsPage.filter_state_dropdown, in_ContactsPage.filter_state_dropdown_values, status);
			waitForPageLoad(Config.short_wait_time/2);
		}
		if (category != null)
		{
			selectitems(driver, in_ContactsPage.filter_category_dropdown,in_ContactsPage.filter_category_dropdown_values, category);
			waitForPageLoad(Config.short_wait_time/2);
		}	
		if (access != null)
		{
			selectitems(driver, in_ContactsPage.filter_access_dropdown, in_ContactsPage.filter_access_dropdown_values, access);
			waitForPageLoad(Config.short_wait_time/2);
		}
		if (language != null)
		{
			selectitems(driver, in_ContactsPage.filter_language_dropdown,in_ContactsPage.filter_language_dropdown_values, language);
			waitForPageLoad(Config.short_wait_time/2);
		}
	}
	
	public boolean doesContactExist(String item)
	{
		return doesElementExistByType(driver,"link" , item);
	}
	
	public void cleanSearch()
	{
		click(driver, in_AdminstratorPage.clear_button);
		waitForPageLoad(Config.short_wait_time/3);
		filterContactByDropdown("- Select Status -", "- Select Category -", "- Select Access -", "- Select Language -");
	}

}