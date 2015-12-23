package ac_pages;
import org.openqa.selenium.WebDriver;

import in_pages.in_BannersPage;

public class ac_BannersPage extends ac_common.CommonElements{

	public ac_BannersPage() {
		// TODO Auto-generated constructor stub
	}
	public ac_BannersPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		//driver = this.driver;
		this.driver = driver;
	}
	
	public ac_BannersPage getBannersPage(WebDriver driver)
	{
		return new ac_BannersPage(driver);
	}
	
	/**
	 * @author: Giang Nguyen
	 * @edit by: 
	 */
	public void createNewClient(String clientName, String contactName, String contactEmail, String saveOption){
		navigatemenu(driver, "Components", "Banners", "Clients");
		if (clientName != null)
			clearText(driver, in_BannersPage.client_name_textbox);
			enter(driver, in_BannersPage.client_name_textbox, clientName);
		if (contactName != null){
			clearText(driver, in_BannersPage.contact_name_textbox);
			enter(driver, in_BannersPage.client_name_textbox, contactName);
		}
		if (contactEmail != null){
			clearText(driver, in_BannersPage.contact_email_textbox);
			enter(driver, in_BannersPage.contact_email_textbox, contactEmail);
		}
		if (saveOption == "save")
		{
			clickToolbarButton(driver, "apply");
		}
		else if (saveOption == "save & close")
		{
			clickToolbarButton(driver, "save");
		}
		else if (saveOption == "save & new")
		{
			clickToolbarButton(driver, "save-new");
		}
		else if (saveOption == "cancel")
		{
			clickToolbarButton(driver, "cancel");
		}
	}
	
	/**
	 * @author: Giang Nguyen
	 * @edit by: 
	 */
	public void createNewCategory(String title, String saveOption){
		navigatemenu(driver, "Components", "Banners", "Categories");
		if (title != null)
		{
			clearText(driver, in_BannersPage.category_title_textbox);
			enter(driver, in_BannersPage.category_title_textbox, title);
		}
		if (saveOption == "save")
		{
			clickToolbarButton(driver, "apply");
		}
		else if (saveOption == "save & close")
		{
			clickToolbarButton(driver, "save");
		}
		else if (saveOption == "save & new")
		{
			clickToolbarButton(driver, "save-new");
		}
		else if (saveOption == "cancel")
		{
			clickToolbarButton(driver, "cancel");
		}
	}
	
	/**
	 * @author: Giang Nguyen
	 * @edit by: 
	 */
	public void fillBannerInfo(String name, String category, String state, String client, String description){
		if (name != null)
			clearText(driver, in_BannersPage.name_textbox);
			enter(driver, in_BannersPage.name_textbox, name);
		if (category != null){
			selectitems(driver, in_BannersPage.category_dropdown, category);
		}
		if (state != null){
			selectitems(driver, in_BannersPage.state_dropdown, state);
		}
		if (client != null){
			selectitems(driver, in_BannersPage.client_dropdown, client);
		}
		if (description != null)
		{
			enter(driver, in_BannersPage.description_textarea, description);
		}
	}
	/**
	 * @author: Giang Nguyen
	 * @edit by: 
	 */
	public void createNewBanner(String name, String category, String state, String client, String description, String saveOption){
		navigatemenu(driver, "Components", "Banners", "Banners");
		if (name != null)
			clearText(driver, in_BannersPage.name_textbox);
			enter(driver, in_BannersPage.name_textbox, name);
		if (category != null){
			selectitems(driver, in_BannersPage.category_dropdown, category);
		}
		if (state != null){
			selectitems(driver, in_BannersPage.state_dropdown, state);
		}
		if (client != null){
			selectitems(driver, in_BannersPage.client_dropdown, client);
		}
		if (description != null)
		{
			enter(driver, in_BannersPage.description_textarea, description);
		}
		if (saveOption == "save")
		{
			clickToolbarButton(driver, "apply");
		}
		else if (saveOption == "save & close")
		{
			clickToolbarButton(driver, "save");
		}
		else if (saveOption == "save & new")
		{
			clickToolbarButton(driver, "save-new");
		}
		else if (saveOption == "cancel")
		{
			clickToolbarButton(driver, "cancel");
		}
	}
	private WebDriver driver;
}
