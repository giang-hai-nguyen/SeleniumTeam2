package ac_pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

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
	 * @edit by: Tuan Nguyen
	 */
	public void createNewClient(String clientName, String contactName, String contactEmail, String saveOption){
		if (clientName != null)
			clearText(driver, in_BannersPage.client_name_textbox);
			enter(driver, in_BannersPage.client_name_textbox, clientName);
		if (contactName != null){
			clearText(driver, in_BannersPage.contact_name_textbox);
			enter(driver, in_BannersPage.contact_name_textbox, contactName);
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
	 * @edit by: Tuan Nguyen
	 */
	public void createNewCategory(String title, String saveOption){
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
	 * @edit by: Tuan Nguyen
	 */
	public void createNewBanner(String bannerName, String category, String state, String client, String description, String saveOption){
		if (bannerName != null)
			clearText(driver, in_BannersPage.name_textbox);
			enter(driver, in_BannersPage.name_textbox, bannerName);
		if (category != null){
			selectitems(driver, in_BannersPage.category_dropdown, in_BannersPage.fillter_category_dropdown_value, category);
		}
		if (state != null){
			selectitems(driver, in_BannersPage.state_dropdown, in_BannersPage.fillter_state_dropdown_value, state);
		}
		if (client != null){
			driver.findElement(By.xpath("//ul[@id='myTabTabs']//a[.='Banner Details']")).click();
			selectitems(driver, in_BannersPage.client_dropdown, in_BannersPage.fillter_client_dropdown_value, client);
		}
		if (description != null)
		{
			driver.findElement(By.xpath("//ul[@id='myTabTabs']//a[.='Details']")).click();
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
	/**
	 * @author: Tuan Nguyen
	 * @edit by: 
	 */
	public boolean verifyDataOfBanner(String name, String category, String status,
			String client) {
		
		String getName = driver.findElement(By.xpath(in_BannersPage.name_textbox)).getAttribute("value");
		((JavascriptExecutor)driver).executeScript("document.getElementById('jform_catid').style.display='block';");
		WebElement droplistCategory = driver.findElement(By.xpath(in_BannersPage.category_dropdown_by_js));
		Select categoryItem = new Select(droplistCategory);

		String getCategory = categoryItem.getFirstSelectedOption().getText().substring(2);
		
		((JavascriptExecutor)driver).executeScript("document.getElementById('jform_state').style.display='block';");
		WebElement droplistStatus = driver.findElement(By.xpath(in_BannersPage.state_dropdown_by_js));
		Select statusItem = new Select(droplistStatus);
		String getStatus = statusItem.getFirstSelectedOption().getText();
	
		driver.findElement(By.xpath("//ul[@id='myTabTabs']//a[.='Banner Details']")).click();
		((JavascriptExecutor)driver).executeScript("document.getElementById('jform_cid').style.display='block';");
		WebElement droplistClient = driver.findElement(By.xpath(in_BannersPage.client_dropdown_by_js));
		Select clientItem = new Select(droplistClient);
		String getClient = clientItem.getFirstSelectedOption().getText();
		
		Boolean check = null;
		if (getName.equals(name) && getCategory.equals(category) && getStatus.equals(status) && getClient.equals(client)) {
			check = true;
		} else {
			check = false;
		}
		return check;
	}
	private WebDriver driver;
}
