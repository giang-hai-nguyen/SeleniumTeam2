package ac_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

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
	public void fillInfoCategory(String name, String status, String access, String language){
		
		{if (name != null)
			clearText(driver, in_CategoryPage.title_texbox);
			enter(driver, in_CategoryPage.title_texbox, name);
			waitForPageLoad(Config.short_wait_time);}
		{if (status != null)
			selectitems(driver, in_CategoryPage.status_dropdown, in_CategoryPage.status_dropdown_values, status);}
		{if (access != null)
			selectitems(driver, in_CategoryPage.access_dropdown, in_CategoryPage.access_dropdown_values, access);}
		{if (language !=null)
			selectitems(driver, in_CategoryPage.language_dropdown, in_CategoryPage.language_dropdown_values, language);}
		//wait to enter info
		waitForPageLoad(Config.short_wait_time);
	}
	
	
	public void filterCategoryByDropdown(String status, String access, String language)
	{
		if (status != null)
		{
			selectitems(driver, in_CategoryPage.filter_status_dropdown, in_CategoryPage.filter_status_dropdown_values, status);
			waitForPageLoad(Config.short_wait_time/2);
		}
		if (access != null)
		{
			selectitems(driver, in_CategoryPage.filter_access_dropdown, in_CategoryPage.filter_access_dropdown_values, access);
			waitForPageLoad(Config.short_wait_time/2);
		}
		if (language != null)
		{
			selectitems(driver, in_CategoryPage.filter_language_dropdown,in_CategoryPage.filter_language_dropdown_values, language);
			waitForPageLoad(Config.short_wait_time/2);
		}
	}
	
	public boolean doesHelpWindowsDisplay() {
		return doesHelpWindowsDisplay(driver);
	}
	
	public void selectCategoryProcess(String category, String action) {
		selectitems(driver, in_CategoryPage.move_copy_dropdown,in_CategoryPage.move_copy_dropdown_value, category);
		click(driver,"//div[@id='batch-copy-move']//label[contains(.,'" + action + "')]");
		click(driver,"//button[contains(text(),'Process')]");
	}
	
	public void selectAccessLevel(String access_value) {
		selectitems(driver, in_CategoryPage.access_level_dropdown,in_CategoryPage.access_level_dropdown_value, access_value);
		click(driver,"//button[contains(text(),'Process')]");
	}
	public boolean verifyParentOfCategory(String parentname) {
		
		((JavascriptExecutor)driver).executeScript("document.getElementById('jform_parent_id').style.display='block';");
		WebElement droplistParent = driver.findElement(By.xpath(in_CategoryPage.parent_dropdown_by_js));
		Select ParentItem = new Select(droplistParent);

		String getParent = ParentItem.getFirstSelectedOption().getText().substring(2);
				
		Boolean check = null;
		if (getParent.equals(parentname)) {
			check = true;
		} else {
			check = false;
		}
		return check;
	}
	public boolean verifyAccessOfCategory(String accessname) {
		
		((JavascriptExecutor)driver).executeScript("document.getElementById('jform_access').style.display='block';");
		WebElement droplistParent = driver.findElement(By.xpath(in_CategoryPage.access_dropdown_by_js));
		Select ParentItem = new Select(droplistParent);

		String getParent = ParentItem.getFirstSelectedOption().getText();
				
		Boolean check = null;
		if (getParent.equals(accessname)) {
			check = true;
		} else {
			check = false;
		}
		return check;
	}
	
	private WebDriver driver;
}
