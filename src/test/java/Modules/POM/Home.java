package Modules.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import project.Base.DriverManager;
import project.Base.TestBase;
import project.Utility.UtilsManager;


public class Home extends TestBase {
    By input_Amount = By.xpath(".//*[@class='amount-input']//input");
    By err_Message = By.xpath(".//*[contains(@class,'currency-converter__ErrorText')]");
    By lnk_Convert = By.xpath(".//span[text()='Convert']//parent::a");
    By input_From = By.xpath(".//*[@id='midmarketFromCurrency']");
    By input_FromDrpdwnvalue = By.xpath(".//*[@id='midmarketFromCurrency-listbox']//li[@id='midmarketFromCurrency-option-0']");
    By btn_Convert = By.xpath(".//*[contains(@class,'button__BaseButton-sc')][text()='Convert']");
    By txt_ResultValue = By.xpath(".//*[contains(@class,'result__BigRate')]");
    By txt_UnitValue = By.xpath(".//*[contains(@class,'unit-rates')]");
    By btn_signInRegister = By.xpath(".//*[contains(text(),'Sign in and send')]//parent::a");
    By input_UserName = By.xpath(".//input[@id='email']");
    By input_Password = By.xpath(".//input[@id='password']");
    By btn_Registration = By.xpath(".//*[contains(text(),'Register now')]");
    
    WebDriverWait wt = new WebDriverWait(DriverManager.getWebDriver(), 10);
    Actions act = new Actions(DriverManager.getWebDriver());
    
    public void validatingTheHomePageTitle() throws Exception {
    	String strTitle =DriverManager.getWebDriver().getTitle();
    	if(strTitle.equalsIgnoreCase("Online Currency Exchange - Send Money Internationally for Less with Xe")) {
    		System.out.println("Home Page is Launched");
    	}
        UtilsManager.takeScreenshot();
    }
    
    public void clickConvertLink() {
    	UtilsManager.click(lnk_Convert);
    }
    
	public void clearAmountInputField() throws Exception {
		wt.until(ExpectedConditions.elementToBeClickable(input_Amount));
		DriverManager.getWebDriver().findElement(input_Amount).click();
		DriverManager.getWebDriver().findElement(input_Amount).sendKeys("5");
		act.sendKeys(Keys.BACK_SPACE).build().perform();
		System.out.println("Imput Field is cleared");
	}

    public void verifyErrorMessage() throws Exception {
    	if(UtilsManager.waitForElementVisibility(err_Message)) {
    		String strActualText = UtilsManager.getText(err_Message).trim();
    		String strExpectedText = "Please enter a valid amount";
    			Assert.assertEquals(strActualText, strExpectedText);
    	}else {
    		Assert.fail("Unable to Enter Password");
    	}
    }
    
    public void verifyINRoptionISDisplayed(String value) {
    	Actions act = new Actions(DriverManager.getWebDriver());
    	act.sendKeys(Keys.TAB);
    	UtilsManager.sendText(input_From, value);
    	wt.until(ExpectedConditions.visibilityOfElementLocated(input_FromDrpdwnvalue));
    	String strDropdwnvalule = UtilsManager.getText(input_FromDrpdwnvalue);
    	if(strDropdwnvalule.contains(value)) {
    		System.out.println("INR- Indian Rupee option is available under from dropdown");
    	}else {
    		Assert.fail("INR -Indian Rupee option is not available");
    	}
    	
    }
    
    public void verifyConvertButtonIsDisplayed() {
    	if(DriverManager.getWebDriver().findElement(btn_Convert).isDisplayed()) {
    		System.out.println("Convery Button under Form is featured and displayed");
    	}else {
    		Assert.fail("Convery Button under Form is not displayed");
    	}
    }
    
    public void entervalueInAmountField() {
    	wt.until(ExpectedConditions.visibilityOfElementLocated(input_Amount));
    	UtilsManager.sendText(input_Amount, "10");
    }
    
    public void chooseGBPFromDropdown() {
    	act.sendKeys(Keys.TAB);
    	UtilsManager.sendText(input_From, "British Pound");
    	DriverManager.getWebDriver().findElement(input_FromDrpdwnvalue).click();
    	System.out.println("Choosed GBP - British Pound under From field");
    }
    
    public void clickConvertButton() {
    	wt.until(ExpectedConditions.visibilityOfElementLocated(btn_Convert));
    	DriverManager.getWebDriver().findElement(btn_Convert).click();
    	System.out.println("Convert button is clicked");
    }
    
    public void verifyCurrencyConversion() {
    	String strConvertedResult = UtilsManager.getText(txt_ResultValue).split(" ")[0].substring(0, 5);
    	String strvalue2 = UtilsManager.getText(txt_UnitValue).split("EUR")[0].split("=")[1].trim();
    	String ActualValue = String.valueOf(Float.parseFloat(strvalue2)*10).substring(0, 5);
    	if(strConvertedResult.equalsIgnoreCase(ActualValue)) {
    		System.out.println("Converted value from GBP to EUR is :"+ActualValue+" and Displayed Correctly");
    	}else {
    		Assert.fail("Converted value is not displayed correctly");
    	}
    }
    
    public void scrolldownAndClickSignInButton() {
    	//UtilsManager.scrollDownToElementAndClick(btn_signInRegister);
    	//DriverManager.getWebDriver().findElement(btn_signInRegister).click();
    	act.moveToElement(DriverManager.getWebDriver().findElement(btn_signInRegister)).build().perform();
    	DriverManager.getWebDriver().findElement(btn_signInRegister).click();
    }
    
    public void enterEmailID(String username) {
    	UtilsManager.waitForElementClickable(input_UserName);
    	DriverManager.getWebDriver().findElement(input_UserName).sendKeys(username);;
    }
    
    public void enterPassword(String password) {
    	UtilsManager.waitForElementClickable(input_Password);
    	DriverManager.getWebDriver().findElement(input_Password).sendKeys(password);;
    }
    
    public void verifyRegistrationButtonIsEnabled() {
    	UtilsManager.waitForElementVisibility(btn_Registration);
    	if(DriverManager.getWebDriver().findElement(btn_Registration).isEnabled()) {
    		System.out.println("Registration button is Enabled");
    	}else {
    		Assert.fail("Registration Button is disabled");
    	}
    }
}
