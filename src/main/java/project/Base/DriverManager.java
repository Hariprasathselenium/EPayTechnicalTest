package project.Base;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class DriverManager extends TestBase {

	static HashMap<String, String> map = new HashMap<String, String>();
	private static ThreadLocal<AppiumDriver<MobileElement>> appiumDriver = new ThreadLocal<>();
	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
	
	public static AppiumDriver<MobileElement> getDriver() {
		return appiumDriver.get();
	}

	
	  public static WebDriver getWebDriver(){ 
		  return webDriver.get(); 
		  }
	 
	
	public DriverManager(WebDriver driver) {
		// TODO Auto-generated constructor stub
		webDriver.set(driver);
	}
	public static String getDeviceID() {
		String DeviceID_ = map.get(DriverManager.getDriver().toString());
		return DeviceID_;
	}
	
	public DriverManager(String deviceid, AppiumDriver<MobileElement> driver) {
			if (executionType.equalsIgnoreCase("MOBILE")) {
				appiumDriver.set(driver);
				map.put(DriverManager.getDriver().toString(), deviceid);
				try {
	}catch(Exception e) {
		
	}
		}
	}

	
	
		

}
			
