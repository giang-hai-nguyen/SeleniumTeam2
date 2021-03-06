package ac_common;

import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import config.Config;
import config.Initialize;
import in_common.in_AdminstratorPage;
import in_pages.in_BannersPage;

import org.testng.Assert;

public abstract class CommonElements extends Initialize {

	
	/****************** Method for control ********************/
	public WebElement findAnElement (WebDriver driver, String control){
		if (control.contains("byID")) {
			element = driver.findElement(By.id(control));
		}
		else if (control.contains("bytagName")){
			element = driver.findElement(By.tagName(control));
		} else
			element = driver.findElement(By.xpath(control));
		return element;
	}
	
	public void click(WebDriver driver, String control)
	{
		element  = findAnElement(driver, control);
		element.click();
	}
	
	public void enter(WebDriver driver, String control, String value)
	{
		element  = findAnElement(driver, control);
		element.sendKeys(value);
	}
	
	public void clearText(WebDriver driver, String control)
	{
		driver.findElement(By.xpath(control)).clear();
	}
	
	public void selectitems(WebDriver driver, String xpath1, String xpath2, String item) 
	{
		driver.findElement(By.xpath(xpath1)).click();
		waitForPageLoad(Config.short_wait_time);
		driver.findElement(By.xpath(String.format(xpath2, item))).click();
	}
	
	public void selecttabs(WebDriver driver, String xpath, String item) 
	{
		driver.findElement(By.xpath(String.format(xpath, item))).click();
	}
	
	public void selectCheckboxItem(WebDriver driver, String item) 
	{
		searchItem(driver, item);
		driver.findElement(By.xpath(String.format(in_AdminstratorPage.div_checkbox, item))).click();
	}
	
	public void selectCheckboxItemWithoutDiv(WebDriver driver, String item) 
	{
		searchItem(driver, item);
		driver.findElement(By.xpath(String.format(in_AdminstratorPage.without_div_checkbox, item))).click();
	}
	
	/**
	  * @author: Tuan Nguyen 12-03
	  * @edit by: 
	  */
	public void clickToolbarButton(WebDriver driver, String button) {
		driver.findElement(By.xpath("//div[@id='toolbar']/div[@id='toolbar-" + button + "']/button")).click();
		waitForPageLoad(Config.short_wait_time);
	}
	/**
	  * @author: Giang Nguyen 12-07
	  * @edit by: 
	  */
	public void clickStatusIconInTheList(WebDriver driver, String title, String control)
	{
		element = driver.findElement(By.xpath(String.format(control, title)));
		element.click();
	}
	
	public void searchItem(WebDriver driver, String searchtext) 
	 {
		WebElement txtbox = driver.findElement(By.xpath(in_AdminstratorPage.filter_textbox));
		String a = txtbox.getAttribute("value").toString(); 
		if ( !a.equals(searchtext)) {
			txtbox.clear();
			waitForPageLoad(Config.short_wait_time/2);
			txtbox.sendKeys(searchtext);
			driver.findElement(By.xpath(in_AdminstratorPage.search_button)).click();
			waitForPageLoad(Config.short_wait_time);
		}
	 }
	public List<WebElement> findListElements(WebDriver driver, String control, String property){
		List<WebElement> lElement = driver.findElements(By.xpath(String.format(control, property)));
		return lElement;
	}
	/**
	  * @author: Tuan Nguyen 12-03
	  * @edit by: 
	  */
	public void selectToobarButton(WebDriver driver, String itemName, String button) {
		selectCheckboxItem(driver, itemName);
		clickToolbarButton(driver, button);
	}
	
	/****************** Method for verifying ********************/
	
	 protected void verifyTrue(boolean condition) 
	 {
		 Assert.assertTrue(condition);
	 }

	 protected void verifyFalse(boolean condition) 
	 {
		 Assert.assertFalse(condition);
	 }

	 protected void verifyEquals(String actual, String expected) 
	 {
		 Assert.assertEquals(actual, expected);
	 }
	 
	 /**
	  * Verify True with parameter specific the test will be ended or continued
	  * @param condition
	  * @param iscontinue continue when test fail true/false
	  */
	 protected void verifyTrue(boolean condition, boolean iscontinue)
	 {
		 if ( iscontinue == true ) {
			 try {
	                Assert.assertTrue(condition);
	            } catch (Throwable e) {
	             Assert.fail("Condition is not matched");
	            }
	        } else {
	            Assert.assertTrue(condition);
	     }
	 }
	
	/****************** Method for checking ********************/
	 
	public boolean doesControlExist(WebDriver driver, String control)
	{
		try
		{
			element = findAnElement(driver, control);
		} catch (NoSuchElementException e)
		{
			return false;
		}
		return element.isDisplayed();
	}
	
	public boolean doesitemExist(WebDriver driver, String item)
	 {
		 searchItem(driver, item);
		 return doesElementExistByType(driver,"link" , item);
	 }
	 
	 public boolean doesitemNotExist(WebDriver driver, String item)
	 {
		 searchItem(driver, item);
		 int numOfRow= getTableRows(driver, "//table");
		 return numOfRow==0;
	 }
	 
	 public boolean doesElementExistByType(WebDriver driver, String type, String item) 
	 {
		 Boolean check = null;
		 
		 if (type == "link") {
			 check = driver.findElement(By.linkText(item)).isDisplayed();
		 }
		 		 
	  return check;
	 }
	 
	 /**
	  * @author: TuanNguyen
	  * @edit by: Giang Nguyen, Hang Tran
	  */
	 public boolean doesTextDisplay(WebDriver driver, String control, String message)
	 {
		 try {
				element = driver.findElement(By.xpath(String.format(control, message)));
				return element.isDisplayed();
			} 
			catch (Exception e) {
				   return false;
			}
	 }
	 public boolean doesTextPresent(WebDriver driver, String expectedMessage)
	 {
		 return doesTextDisplay(driver, in_AdminstratorPage.messageDynamic, expectedMessage);
	 }
	 
	 /**
	  * @author: Giang Nguyen
	  * @edit by:
	  */
	 public boolean doesHelpPageExist(WebDriver driver, String control)
		{
			// Store the current window handle
			String parentWindowHandle = driver.getWindowHandle();
			// switch to child window
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			boolean check = doesControlExist(driver, control);
			driver.close();
			// switch back parent window
			driver.switchTo().window(parentWindowHandle);
			return check;
		}
	 
	 /****************** Method for moving ********************/
	
	/**
	 * @author: Tuan Nguyen
	 * @edit by: Giang Nguyen
	 */
	public void switchToFrame (WebDriver driver, String control){
		WebElement bodyIframe = findAnElement (driver, control);
		driver.switchTo().frame(bodyIframe);	
	}
	/**
	 * @author: Giang Nguyen
	 * @edit by: 
	 */
	public void switchBackDefaultframe (WebDriver driver){
		driver.switchTo().defaultContent();		
	}
	/**
	 * @author: Giang Nguyen
	 * @edit by: 
	 */
	public void navigatemenu(WebDriver driver, String menuitem1, String menuitem2, String menuitem3 )
	{
		String menuitem1_path = "//a[contains(text(),'" + menuitem1 + "')]"; 
		String menuitem2_path = menuitem1_path + "/../ul/li/a[text()='" + menuitem2 + "']";
		driver.findElement(By.xpath(menuitem1_path)).click();
		
		if (menuitem3 != null){
			Actions actions = new Actions(driver);
			WebElement mainMenu2 = driver.findElement(By.linkText(menuitem2));
			actions.moveToElement(mainMenu2).build().perform();
			waitForPageLoad(Config.short_wait_time);
			if (menuitem2 != null) {
				actions.moveToElement(driver.findElement(By.xpath(menuitem2_path))).build().perform();
				waitForPageLoad(Config.short_wait_time);
			}			
			waitForControl(driver, menuitem2_path, Config.short_wait_time);			
			driver.findElement(By.linkText(menuitem3)).click();
		} 
		else {
			driver.findElement(By.xpath(menuitem2_path)).click();
		}
		
	}
	
	/****************** Method for getting ********************/
		
	 public int getTableRows(WebDriver driver, String tableXpath){
		 String tableRowXpath = "%s/tbody/tr";
		 int rows = findListElements(driver, tableRowXpath, tableXpath).size();
		 return rows;
		}
	 
	 /**
	  * @author: Giang Nguyen
	  * @edit by: 
	  */
	 public String getitemStatus(WebDriver driver, String control, String title)
	 {
		 element = driver.findElement(By.xpath(String.format(control, title)));
		 return element.getAttribute("class");
	 }
	 /**
	  * @author: Giang Nguyen 12-07
	  * @edit by: 
	  */
	 public String getTextitem(WebDriver driver, String control, String title)
	 {
		 element = driver.findElement(By.xpath(String.format(control, title)));
		 return element.getText();
	 }
	 
	 public String getValueitem(WebDriver driver, String control)
	 {
		 element = driver.findElement(By.xpath(control));
		 return element.getAttribute("value");
	 }
	 
	 /****************** Method for system ********************/
	 public static String randUniqueString(String basestring) 
		{

			int day, month, year;
			int millisecond, second, minute, hour;
			Calendar date = Calendar.getInstance();

			day = date.get(Calendar.DAY_OF_MONTH);
			month = date.get(Calendar.MONTH);
			year = date.get(Calendar.YEAR);

			millisecond = date.get(Calendar.MILLISECOND);
			second = date.get(Calendar.SECOND);
			minute = date.get(Calendar.MINUTE);
			hour = date.get(Calendar.HOUR);

			return basestring + " " + year + month + day + hour + minute + second + millisecond;
		}
	 
	 public void waitForPageLoad(long waittime){
		  try {
		   Thread.sleep(waittime*1000);
		  } catch (InterruptedException e) {
		   e.printStackTrace();
		  }
		 }
	 /**
	  * @author: Tuan Nguyen 12-03
	  * @edit by: 
	  */
	 public void waitForControl(WebDriver driver, String messageHeader, long timeout)
		{
			try {
				WebDriverWait wait = new WebDriverWait(driver, timeout);
				wait.until(ExpectedConditions.elementToBeClickable((By) driver.findElement(By.xpath(messageHeader))));
			} catch (Exception e) {
				Reporter.log("Element doesn't exist");
			}
		}
	 /**
	  * @author: Tuan Nguyen
	  * @edit by: 
	  */
	 public void changeFeatureItem(WebDriver driver, String itemName) {
		 	driver.findElement(By.xpath("//td/div[a[contains(text(),'"+itemName+"')]]/../preceding-sibling::td[1]/div/a[@data-original-title='Toggle featured status.']")).click();
			waitForPageLoad(Config.short_wait_time);
	}
	 /**
	  * @author: Tuan Nguyen
	  * @edit by: 
	  */
	
	public boolean doesItemFeature(WebDriver driver, String itemname, String feature) 
	{
		Boolean check=null;
		if (feature == Config.contact_yes_featured) {
			check = driver
					.findElement(
							By.xpath("//td/div[a[contains(text(),'"
									+ itemname
									+ "')]]/../preceding-sibling::td[1]/div/a[@data-original-title='Toggle featured status.']/span[@class='icon-featured']"))
									.isDisplayed();
		} else if (feature == Config.contact_no_featured) {
			check = driver
					.findElement(
							By.xpath("//td/div[a[contains(text(),'"
									+ itemname
									+ "')]]/../preceding-sibling::td[1]/div/a[@data-original-title='Toggle featured status.']/span[@class='icon-unfeatured']"))
									.isDisplayed();
		} else {
			check = false;
			System.out.println("The feature value is incorrect.");
		}
		return check;
	}

	public void clickHeaderID(WebDriver driver){
		driver.findElement(By.xpath(in_AdminstratorPage.header_ID)).click();
		waitForPageLoad(Config.short_wait_time);
	}
	
	public void clicksubmenu(WebDriver driver){
		driver.findElement(By.xpath(in_BannersPage.submenu_link)).click();
		waitForPageLoad(Config.short_wait_time);
	}
	
	public boolean doesSortingIDAscend(WebDriver driver) {

		Boolean check = false;
		WebElement webTableElement = driver.findElement(By.xpath(in_AdminstratorPage.table_admin));
		int tableRows = webTableElement.findElements(By.tagName("tr")).size();
		int tableColumns = driver.findElements(
				By.xpath(".//*[@id='adminForm']//table/tbody/tr[1]/td")).size();

		for (int i = 1; i < tableRows; i++) {
			int j = i + 1;
			String beforeIDvalue = driver.findElement(
					By.xpath(".//*[@id='adminForm']//table/tbody/tr[" + i
							+ "]/td[" + tableColumns + "]")).getText();
			int beforeID = Integer.parseInt(beforeIDvalue);
			String afterIDvalue = driver.findElement(
					By.xpath(".//*[@id='adminForm']//table/tbody/tr[" + j
							+ "]/td[" + tableColumns + "]")).getText();
			int afterID = Integer.parseInt(afterIDvalue);
			int compare = Integer.compare(beforeID, afterID);

			if (compare <= 0) {
				check = true;
			} else {
				check = false;
				break;
			}
		}
		return check;
	}
	
	public boolean doesSortingIDDescend(WebDriver driver) {
		Boolean check = false;
		WebElement webTableElement = driver.findElement(By.xpath(in_AdminstratorPage.table_admin));
		int tableRows = webTableElement.findElements(By.tagName("tr")).size();
		int tableColumns = driver.findElements(
				By.xpath(".//*[@id='adminForm']//table/tbody/tr[1]/td")).size();

		for (int i = 1; i < tableRows; i++) {
			int j = i + 1;
			String beforeIDvalue = driver.findElement(
					By.xpath(".//*[@id='adminForm']//table/tbody/tr[" + i
							+ "]/td[" + tableColumns + "]")).getText();
			int beforeID = Integer.parseInt(beforeIDvalue);
			String afterIDvalue = driver.findElement(
					By.xpath(".//*[@id='adminForm']//table/tbody/tr[" + j
							+ "]/td[" + tableColumns + "]")).getText();
			int afterID = Integer.parseInt(afterIDvalue);
			int compare = Integer.compare(beforeID, afterID);

			if (compare >= 0) {
				check = true;
			} else {
				check = false;
				break;
			}
		}
		return check;
	}

	public boolean doesPagingAll(WebDriver driver) {
		driver.findElement(By.xpath(in_AdminstratorPage.clear_btn)).click();
		Boolean check = null;
		((JavascriptExecutor)driver).executeScript("document.getElementById('list_limit').style.display='block';");		
		selectdropDownListItem(driver, in_AdminstratorPage.dropdownlist_paginate, "All");
		waitForPageLoad(Config.timeout);
		WebElement baseTable = driver.findElement(By.xpath(in_AdminstratorPage.table_admin));
		int tableRows = baseTable.findElements(By.xpath("//*[@id='adminForm']//table/tbody/tr")).size();
		if (tableRows > 100) {
			check = true;
		}
		return check;
	}
	
	public boolean doesPagingNumber(WebDriver driver, int rowlimit) {
		driver.findElement(By.xpath(in_AdminstratorPage.clear_btn)).click();
		Boolean check = null;
		((JavascriptExecutor)driver).executeScript("document.getElementById('list_limit').style.display='block';");	
		selectdropDownListItem(driver, in_AdminstratorPage.dropdownlist_paginate,
				Integer.toString(rowlimit));
		WebElement baseTable = driver.findElement(By.xpath(in_AdminstratorPage.table_admin));
		int tableRows = baseTable.findElements(By.xpath("//*[@id='adminForm']//table/tbody/tr")).size();
		check = (tableRows == rowlimit);
		return check;
	}
	
	public void selectdropDownListItem(WebDriver driver, By dropdownlist, String item) {
		WebElement dropDownListBox = driver.findElement(dropdownlist);
		Select clickItem = new Select(dropDownListBox);
		clickItem.selectByVisibleText(item);
	}
	
	public void clickHeaderOrdering(WebDriver driver){
		driver.findElement(By.xpath(in_AdminstratorPage.header_ordering)).click();
		if (doesControlExist(driver, in_AdminstratorPage.order_arrow_up) == true) {
			driver.findElement(By.xpath(in_AdminstratorPage.order_arrow_up)).click();
		} else {
			driver.findElement(By.xpath(in_AdminstratorPage.order_arrow_down)).click();			
		}
	}
	
	public boolean doesOrderTwoItems(WebDriver driver, String beforeitem, String afteritem) {
		Boolean check = null;
		int orderItem1 = driver.findElements(
				By.xpath("//div[a[contains(text(),'" + beforeitem
						+ "')]]/../../preceding-sibling::tr")).size();
		int orderItem2 = driver.findElements(
				By.xpath("//div[a[contains(text(),'" + afteritem
						+ "')]]/../../preceding-sibling::tr")).size();
		check = orderItem1<orderItem2;
		return check;
	}
	
	public boolean doesTextDisplay(WebDriver driver, String text) {
		String result = driver.findElement(By.tagName("body")).getText();
		Boolean check = result.contains(text);
		return check;
	}
	
	public boolean doesHelpWindowsDisplay(WebDriver driver) {
		Boolean check = null;
		String parentHandle = driver.getWindowHandle();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			if (driver.getTitle().equals("Joomla! Help")) {
				check = true;
			}
			else check = false;
		}
		driver.close(); 
		driver.switchTo().window(parentHandle);
		return check;
	}
	public boolean verifyCheckInState(WebDriver driver, String itemname, String state) {
		Boolean check = null;
		searchItem(driver, itemname);
		Boolean lockicon_state = driver.findElements(
				By.xpath("//div[a[contains(text(),'" + itemname
						+ "')]]/a/span[@class='icon-checkedout']"))
						.size() != 0;
		if (state == "locked") {
			check = lockicon_state.equals(true);
		} else {
			check = lockicon_state.equals(false);
		}
		return check;
	}
	
	public boolean isPageTitle(WebDriver driver, String pagetitle){
		Boolean check = null;
		String getPageTitle=driver.findElement(By.xpath(in_AdminstratorPage.page_title)).getText().toString();
		System.out.println(getPageTitle);
		check = getPageTitle.equals(pagetitle);
		return check;
	}
	public void deleteItem(WebDriver driver, String item_name, String special_div){
		driver.findElement(By.xpath("//form[@id='adminForm']//button[contains(.,'Clear')]")).click();
		searchItem(driver, item_name);
		if (special_div == "Yes") {
			selectCheckboxItem(driver, item_name);
		} 
		else if (special_div == "No")
		{
			selectCheckboxItemWithoutDiv(driver, item_name);			
		}
		
		clickToolbarButton(driver, "trash");
		click(driver, "//div/button[@data-original-title='Filter the list items.']");
		waitForPageLoad(Config.short_wait_time/2);
		selectitems(driver, "//div[@id='filter_published_chzn']", "//div[@id='filter_published_chzn']/div/ul/li[text()='%s']", "Trashed");
		if (special_div == "Yes") {
			selectCheckboxItem(driver, item_name);
		} 
		else if (special_div == "No")
		{
			selectCheckboxItemWithoutDiv(driver, item_name);			
		}
		clickToolbarButton(driver, "delete");
	}
	protected WebElement element;
}
