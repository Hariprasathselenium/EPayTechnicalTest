package project.Utility;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import project.Base.DriverManager;
import project.Base.TestBase;

public class UtilsManager extends TestBase {
	
	public static JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getWebDriver();

	public static void click(By by) {
		WebDriverWait wait = new WebDriverWait((WebDriver) DriverManager.getWebDriver(), 40);
		wait.until(ExpectedConditions.elementToBeClickable(by)).click();
	}

	public static void sendText(By by,String value) {
		if(executionType.equalsIgnoreCase("MOBILE")) {
			WebDriverWait wait = new WebDriverWait((WebDriver) DriverManager.getDriver(), 40);
			wait.until(ExpectedConditions.elementToBeClickable(by)).sendKeys(value);
		}else {
			WebDriverWait wait = new WebDriverWait((WebDriver) DriverManager.getWebDriver(), 40);
			wait.until(ExpectedConditions.elementToBeClickable(by)).sendKeys(value);
		}
	}

	public static void scrollDownToElementAndClick(By by) {
		WebDriverWait wait = new WebDriverWait((WebDriver) DriverManager.getWebDriver(), 40);
		WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(by));
		Actions ac= new Actions(DriverManager.getWebDriver());
		ac.moveToElement(ele).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(by)).click();
	}

	public static void displayCheckOfElement(By by) {
		scrollDownToElementAndClick(by);
		WebDriverWait wait = new WebDriverWait((WebDriver) DriverManager.getWebDriver(), 40);
		Assert.assertEquals(wait.until(ExpectedConditions.presenceOfElementLocated(by)).isDisplayed(),true);
	}

	public static byte[] screenshot() throws IOException {
		File src;
		if(executionType.equalsIgnoreCase("MOBILE")) {
			src = ((TakesScreenshot)DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
		}else {
			src = ((TakesScreenshot)DriverManager.getWebDriver()).getScreenshotAs(OutputType.FILE);
		}
		
		byte[] fileContent = FileUtils.readFileToByteArray(src);
		return fileContent;
	}
	
	public static boolean waitForElementVisibility(By by) {
		System.out.println("waitng for " + by.toString());
		boolean isElementDisplayed = false;
		if(executionType.equalsIgnoreCase("MOBILE")) {
			try {
				WebDriverWait wait = new WebDriverWait((WebDriver) DriverManager.getDriver(), 40);
				isElementDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed();
			} catch (Exception e) {
			}
		}else {
			try {
				WebDriverWait wait = new WebDriverWait((WebDriver) DriverManager.getWebDriver(), 40);
				isElementDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed();
			} catch (Exception e) {
			}
		}
		
		return isElementDisplayed;

	}
	
	public static boolean waitForElementClickable(By by) {
		System.out.println("waitng for " + by.toString());
		boolean isElementDisplayed = false;
			try {
				WebDriverWait wait = new WebDriverWait((WebDriver) DriverManager.getWebDriver(), 40);
				isElementDisplayed = wait.until(ExpectedConditions.elementToBeClickable(by)) != null;
			} catch (Exception e) {
			}
		return isElementDisplayed;
	}
	
	public static boolean isElementDisplayed(By by) throws Exception {
		boolean flag = false;
		if(executionType.equalsIgnoreCase("MOBILE")) {
			try {
				flag = DriverManager.getDriver().findElement(by).isDisplayed();
			} catch (Exception e) {
			}
		}else {
			try {
				flag = DriverManager.getWebDriver().findElement(by).isDisplayed();
			} catch (Exception e) {
			}
		}
		return flag;
	}
	
	public static void VerifyExpectedTextIsSameAsActual(By byID, String ExpectedText) throws Exception {
		UtilsManager.waitForElementVisibility(byID);
		String strActual = DriverManager.getWebDriver().findElement(byID).getText().trim();
		if (ExpectedText.contains(strActual)) {
			Assert.assertEquals(strActual, ExpectedText);
		} else {
			Assert.fail("Expected Text is not matched with Actual Text");
		}
	}
	
	public static void scrollToParticularElement(By byID) {
		executor.executeScript("arguments[0].scrollIntoView(true);", byID);
	}
	
	public static void scrollToTopOfPage() throws InterruptedException {
		DriverManager.getWebDriver().findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.HOME);
		Thread.sleep(1000);
	}
	
	public static void scrollToBottomOfPage() throws InterruptedException {
			DriverManager.getWebDriver().findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
			Thread.sleep(1000);
	}
	
	public static String getText(By byID) {
		String strText = DriverManager.getWebDriver().findElement(byID).getText().trim();
		return strText;

	}
	
	public static void takeScreenshot() throws IOException {
		TakesScreenshot ts;
		String dateName = new SimpleDateFormat("yyyy-MM-dd-hhmmss").format(new Date());
		ScreenshotsLocationForSharing = "../Screenshots";
		ScreenshotsLocationLocal = System.getProperty("user.dir") + "/TestReport/Screenshots";
		if(executionType.equalsIgnoreCase("MOBILE")) {
			ts = (TakesScreenshot) DriverManager.getWebDriver();
		}else {
			ts = (TakesScreenshot) DriverManager.getWebDriver();
		}
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationLocal = ScreenshotsLocationLocal + "/" + dateName + ".png";
		File finalDestination = new File(destinationLocal);
		FileUtils.copyFile(source, finalDestination);
		ScreenshotLocation.set(finalDestination.toString());
	}
}
