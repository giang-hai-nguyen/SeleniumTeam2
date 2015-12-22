package config;

import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import in_common.in_AdminstratorPage;

import org.testng.Assert;

public class AutoElements extends Initialize {
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
	public boolean doesControlExist(WebDriver driver, String control)
	{
		element = findAnElement(driver, control);
		return element.isDisplayed();
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

		return basestring + day + month + year + second + minute + hour;
	}
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
	/**
	  * Verify condition is true
	  * @param condition
	  */
	 protected void verifyTrue(boolean condition) 
	 {
		 Assert.assertTrue(condition);
	 }

	 /**
	  * Verify condition is false
	  * @param condition
	  */
	 protected void verifyFalse(boolean condition) 
	 {
		 Assert.assertFalse(condition);
	 }

	 /**
	  * Verify whether actual result meets expectation
	  * @param actual
	  * @param expected
	  */
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
	 
	 public boolean doesElementExistByType(WebDriver driver, String type, String item) 
	 {
		 Boolean check = null;
		 if (type == "link") {
			 check = driver.findElement(By.linkText(item)).isDisplayed();
	  }
	  return check;
	 }
	 
	 public void searchItem(WebDriver driver, String searchtext) 
	 {
	  WebElement txtbox = driver.findElement(By.xpath(in_pages.in_ArticlesPage.filter_textbox));
	  String a = txtbox.getAttribute("value").toString(); 
	  if ( !a.equals(searchtext)) {
	   txtbox.clear();
	   waitForPageLoad(Config.short_wait_time);
	   txtbox.sendKeys(searchtext);
	   driver.findElement(By.xpath(in_pages.in_ArticlesPage.search_button)).click();
	  }
	 }
	 public void waitForPageLoad(long waittime){
	  try {
	   Thread.sleep(waittime*1000);
	  } catch (InterruptedException e) {
	   e.printStackTrace();
	  }
	 }
	 /**
	  * @author: TuanNguyen
	  * @edit by: Giang Nguyen, Hang Tran
	  */
	 public boolean doesTextDisplay(WebDriver driver, String control, String message)
	 {
		 try {
				element = driver.findElement(By.xpath(String.format(control, message)));
//				String temp = element.getText();
//				boolean check = temp.contains(message);
//				return check;  
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
	 
	 public boolean doesitemExist(WebDriver driver, String item)
	 {
		 searchItem(driver, item);
		 return doesElementExistByType(driver, "link", item);
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
	 public String getitemAccessStatus(WebDriver driver, String control, String title)
	 {
		 element = driver.findElement(By.xpath(String.format(control, title)));
		 return element.getText();
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
	  * @author: Tuan Nguyen 12-03
	  * @edit by: 
	  */
		public void selectToobarButton(WebDriver driver, String itemName, String button) {
			//searchItem(driver, itemName);
			selectCheckboxItem(driver, itemName);
			clickToolbarButton(driver, button);
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

	protected WebElement element;
}
