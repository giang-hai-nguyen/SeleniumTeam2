package ac_common;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
	WebDriver driver=null;
	String filePath = "Screenshot\\";
	
	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Error " + result.getName() + " test has failed");
		String methodName=result.getName().toString().trim();
		takeScreenShot(methodName);
	}

	/**
	 * Take screenshot when failure
	 * @param methodName
	 */
	public void takeScreenShot(String methodName) {
	    driver = BrowserExecution.getDriver();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(filePath + methodName + getDateTime() + ".png"));
			System.out.println("Saved " + methodName + getDateTime() + ".png" + " screenshot in " + filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	 private String getDateTime() {
	     DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HHmmss");
	     Date date = new Date();
	     return dateFormat.format(date);
	 }
	     
	public void onFinish(ITestContext context) {}

	public void onTestStart(ITestResult result) {   }

	public void onTestSuccess(ITestResult result) {   }

	public void onTestSkipped(ITestResult result) {   }

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {   }

	public void onStart(ITestContext context) {   }
}  