package project.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import project.Base.DriverManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;


public class TestBase {
	//public static WebDriver driver;
	//public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	public static DriverManager driver;
	public static ThreadLocal<String> SystemPath = new ThreadLocal<String>();
	public static ThreadLocal<String> URL = new ThreadLocal<String>();
	public static ThreadLocal<ExtentTest> loggerInfo = new ThreadLocal<>();
	public static ThreadLocal<ExtentTest> maintest = new ThreadLocal<>();
	public static String ScreenshotsLocationForSharing = null;
	public static String ScreenshotsLocationLocal = null;
	public static String Screenshotsquality = "HIGH";
	public static ThreadLocal<String> ScreenshotLocation = new ThreadLocal<String>();
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	public static ThreadLocal<ExtentReports> extent = new ThreadLocal<ExtentReports>();
	protected static ThreadLocal<String> CurrentStep = new ThreadLocal<String>();
	protected static ThreadLocal<String> CurrentSteps = new ThreadLocal<String>();
	public static ThreadLocal<String> featureName = new ThreadLocal<>();
	protected static ThreadLocal<String> platform = new ThreadLocal<String>();
	protected static ThreadLocal<String> device = new ThreadLocal<String>();
	public static ThreadLocal<Exception> e = new ThreadLocal<Exception>();
	public static ThreadLocal<String> bundleId = new ThreadLocal<String>();
	public static String reportName;
	public static String executionType = null;
	protected static String AutomationPlatform = null;
	protected static String deviceID = null;
	protected static URL url = null;
	public static Properties property;
	private static String configpath = System.getProperty("user.dir")+"/TestData/configuration.properties";
	
	
	
	public static ThreadLocal<String> testSet = new ThreadLocal<String>();
	public static ThreadLocal<String> testModule = new ThreadLocal<String>();
	public static ThreadLocal<Boolean> scenarioStatus = new ThreadLocal<>();
	
	@BeforeSuite
	public void beforeSuiteInitializer() throws IOException, ParseException, InterruptedException {
		SystemPath.set(System.getProperty("user.dir")+"/drivers/chromedriver.exe");
	}

	@BeforeTest
	public void initializer(){
		initializePropertyFile();
		
	}

@BeforeClass(alwaysRun = true)
public static void launchDriver(ITestContext iTestResult) throws Exception {
	URL url;
	executionType = property.getProperty("Execution_type");
	AutomationPlatform = property.getProperty("AutomationPlatform");
	platform.set(property.getProperty("Platform"));
	device.set(property.getProperty("DeviceID"));
	bundleId.set(property.getProperty("bundleId"));
	DesiredCapabilities capabilities = new DesiredCapabilities();
    if (executionType.equalsIgnoreCase("MOBILE")) {
        if (AutomationPlatform.equalsIgnoreCase("physical")) {
            url = new URL("http://127.0.0.1:4723/wd/hub/");
            if (platform.get().equalsIgnoreCase("Android")) {
                capabilities.setCapability(MobileCapabilityType.UDID, device.get());
                capabilities.setCapability("platformName", "Android");
                capabilities.setCapability("platformVersion", "12");
                capabilities.setCapability("app", System.getProperty("user.dir") + "/TestData/SauceDemo.apk");
                capabilities.setCapability("unicodeKeyboard", true);
                AndroidDriver(url, capabilities);

            } else {
                capabilities.setCapability(MobileCapabilityType.UDID, device.get());
                capabilities.setCapability("deviceName", "IPHONE");
                capabilities.setCapability("platformName", "ios");
                capabilities.setCapability("platformVersion", "12");
                capabilities.setCapability("bundleId", bundleId);
                capabilities.setCapability("unicodeKeyboard", true);
                iOSDriver(url, capabilities);
            }

        } else if (AutomationPlatform.equalsIgnoreCase("emulator")) {
            url = new URL("http://127.0.0.1:4723/wd/hub/");
            if (platform.get().equalsIgnoreCase("Android")) {
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
                capabilities.setCapability("appPackage", "com.swaglabsmobileapp");
                capabilities.setCapability("appActivity", "com.swaglabsmobileapp.MainActivity");
                AndroidDriver(url, capabilities);
            } else {
                capabilities.setCapability(MobileCapabilityType.UDID, device.get());
                capabilities.setCapability("deviceName", "IPHONE");
                capabilities.setCapability("platformName", "ios");
                capabilities.setCapability("platformVersion", "16");
                //capabilities.get().setCapability("bundleId", bundleId);
                capabilities.setCapability("app", System.getProperty("user.dir") + "/TestData/SauceDemo.ipa");
                capabilities.setCapability("unicodeKeyboard", true);
                iOSDriver(url, capabilities);
            }
        }
    } else if (executionType.equalsIgnoreCase("WEB")) {
            System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/drivers/chromedriver.exe");
            driver = new DriverManager(new ChromeDriver());
            DriverManager.getWebDriver().manage().window().maximize();
            DriverManager.getWebDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            DriverManager.getWebDriver().get(property.getProperty("ApplicationURL"));
            
    }

}

private static boolean AndroidDriver(URL url, DesiredCapabilities caps) {
	try {
		DriverManager AndroidDriver = new DriverManager(device.get(), new AndroidDriver<MobileElement>(url, caps));
	} catch (WebDriverException e1) {
		e.set(e1);
		return false;
	}
	return true;
}
private static boolean iOSDriver(URL url, DesiredCapabilities caps) {
	try {
		new DriverManager(device.get(), new IOSDriver<MobileElement>(url, caps));
	} catch (WebDriverException e1) {
		e.set(e1);
		return false;
	}
	return true;
}

	@AfterSuite()
	public static synchronized void tearDown(ITestContext iTestContext) {
		
	}
	
	public void CreateFeatureNode(String feature) throws ClassNotFoundException {
		featureName.set(feature);
		ExtentTest s = extent.get().createTest(new GherkinKeyword("Feature"),
				featureName.get());
		maintest.set(s);
	}
	
	public static void initializePropertyFile() {
		property = new Properties();
		try {
			InputStream inputStr = new FileInputStream(configpath);
			property.load(inputStr);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
