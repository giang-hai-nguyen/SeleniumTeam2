package ac_common;

import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import config.Config;
import config.Initialize;
import in_common.in_AdminstratorPage;

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
	
	public void selectitems(WebDriver driver, String xpath, String item) 
	{
		 Select element= new Select (findAnElement (driver, xpath));
		 element.selectByVisibleText(item);
	}
	
	public void selectCheckboxItem(WebDriver driver, String item) 
	{
		searchItem(driver, item);
		driver.findElement(By.xpath("//td[a[contains(text(),'" + item+ "')]]/../td/input[@type='checkbox']")).click();
	}
	
	/**
	  * @author: Tuan Nguyen 12-03
	  * @edit by: 
	  */
	public void clickToolbarButton(WebDriver driver, String button) {
		driver.findElement(By.xpath("//li[@id='toolbar-" + button + "']/a/span")).click();
		waitForPageLoad(Config.short_wait_time/2);
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
			waitForPageLoad(Config.short_wait_time);
			txtbox.sendKeys(searchtext);
			driver.findElement(By.xpath(in_AdminstratorPage.search_button)).click();
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
		String menuitem1_path = "//a[text()='" + menuitem1 + "']"; 
		String menuitem2_path = menuitem1_path + "/../ul/li/a[text()='" + menuitem2 + "']";
		String menuitem3_path = menuitem2_path + "/../ul/li/a[text()='" + menuitem3 + "']";
		String temp = null;
		Actions action = new Actions(driver);
		if (menuitem1 != null)
		{
			action.moveToElement(driver.findElement(By.xpath(menuitem1_path)));
			temp = menuitem1_path; 
		}
		if (menuitem2 != null)
		{
			action.moveToElement(driver.findElement(By.xpath(menuitem2_path)));
			temp = menuitem2_path; 
		}
		if (menuitem3 != null)
		{
			action.moveToElement(driver.findElement(By.xpath(menuitem3_path)));
			temp = menuitem3_path; 
		}
		action.build().perform();
		click(driver, temp);					
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
	 
	 /****************** Method for system ********************/
	 public static String randUniqueString(String basestring) 
		{

			int day, month, year;
			int second, minute, hour;
			Calendar date = Calendar.getInstance();

			day = date.get(Calendar.DAY_OF_MONTH);
			month = date.get(Calendar.MONTH);
			year = date.get(Calendar.YEAR);

			second = date.get(Calendar.SECOND);
			minute = date.get(Calendar.MINUTE);
			hour = date.get(Calendar.HOUR);

			return basestring + " " + day + month + year + second + minute + hour;
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
	 
	 protected WebElement element;
}
