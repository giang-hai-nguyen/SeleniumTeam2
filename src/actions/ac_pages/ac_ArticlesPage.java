package ac_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import config.Config;
import in_pages.in_ArticlesPage;

public class ac_ArticlesPage extends ac_common.CommonElements {
	
	public ac_ArticlesPage() {
		// TODO Auto-generated constructor stub
		
	}
	
	public ac_ArticlesPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		//driver = this.driver;
		this.driver = driver;
	}
	
	public ac_ArticlesPage getActiclePage(WebDriver driver)
	{
		return new ac_ArticlesPage(driver);
	}
	/**
	 * @author: TuanNguyen
	 * @edit by: Giang Nguyen, Hang Tran
	 */
	public void fillArticleInfo(String name, String category, String state, String access, String arttext){
		if (name != null)
			clearText(driver, in_ArticlesPage.title_texbox);
			enter(driver, in_ArticlesPage.title_texbox, name);
		if (category != null){
			selectitems(driver, in_ArticlesPage.category_dropdown,in_ArticlesPage.category_dropdown_values, category);
		}
		if (state != null){
			selectitems(driver, in_ArticlesPage.state_dropdown,in_ArticlesPage.state_dropdown_values, state);
		}			
		if (access != null){
			selectitems(driver, in_ArticlesPage.access_dropdown,in_ArticlesPage.access_dropdown_values, access);
		}
		if (arttext != null)
		{
			switchToFrame(driver, in_ArticlesPage.arttext_frame_textbox);
			driver.findElement(By.xpath(in_ArticlesPage.frame_textbox)).clear();
			enter(driver, in_ArticlesPage.frame_textbox, arttext);
			switchBackDefaultframe(driver);
		}
	}
	public void filterArticleByDropdown(String status, String category, String access, String language)
	{
		if(doesControlExist(driver, in_ArticlesPage.filter_state_dropdown) == false)
		{
			click(driver, in_ArticlesPage.filter_list_dropdown);
			waitForPageLoad(Config.short_wait_time/2);
		}
		
		if (status != null)
		{
			selectitems(driver, in_ArticlesPage.filter_state_dropdown, in_ArticlesPage.filter_state_dropdown_values, status);
			waitForPageLoad(Config.short_wait_time);
		}
		if (category != null)
		{
			selectitems(driver, in_ArticlesPage.filter_category_dropdown,in_ArticlesPage.filter_category_dropdown_values, category);
			waitForPageLoad(Config.short_wait_time);
		}	
		if (access != null)
		{
			selectitems(driver, in_ArticlesPage.filter_access_dropdown, in_ArticlesPage.filter_access_dropdown_values, access);
			waitForPageLoad(Config.short_wait_time);
		}
		if (language != null)
		{
			selectitems(driver, in_ArticlesPage.filter_language_dropdown,in_ArticlesPage.filter_language_dropdown_values, language);
			waitForPageLoad(Config.short_wait_time);
		}
	}
	private WebDriver driver;
}
