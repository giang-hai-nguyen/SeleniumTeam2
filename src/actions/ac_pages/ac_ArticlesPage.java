package ac_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
			selectitems(driver, in_ArticlesPage.category_dropdown, category);
		}
		if (state != null){
			selectitems(driver, in_ArticlesPage.status_dropdown, state);
		}			
		if (access != null){
			selectitems(driver, in_ArticlesPage.access_dropdown, access);
		}
		if (arttext != null)
		{
			switchToFrame(driver, in_ArticlesPage.arttext_frame_textbox);
			driver.findElement(By.xpath(in_ArticlesPage.frame_textbox)).clear();
			enter(driver, in_ArticlesPage.frame_textbox, arttext);
			switchBackDefaultframe(driver);
		}
	}
	private WebDriver driver;
}
