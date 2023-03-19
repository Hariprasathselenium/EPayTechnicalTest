package Modules.StepDefinitions;
import Modules.POM.Home;
import io.cucumber.java.en.And;
import project.Base.TestBase;

public class HomePage extends TestBase{
	public Home home;

	public HomePage(){
		home = new Home();
	}
	@And("^Open the Browser and Verify Site is launched$")
	public void validatingTheHomePageTitle() throws Exception {
		home.validatingTheHomePageTitle();
	}
	
	@And("^Click Convert form button$")
	public void verifySiteLaunched() throws Exception {
		home.clickConvertLink();
	}

	@And("^Clear value in Amount Input Field$")
	public void clearAmountInputField() throws Exception {
		home.clearAmountInputField();
	}
	
	@And("^Verify Error message when amount is cleared in input field$")
	public void verifyErrorMessage() throws Exception {
		home.verifyErrorMessage();
	}
	
	
	@And("^Verify From field contains INR - \"(.*)\" option is available$")
	public void verifyINRisDisplayed(String value) throws Exception {
		home.verifyINRoptionISDisplayed(value);
	}
	
	@And("^Verify Convert button is displayed$")
	public void verifyConvertButtonIsDisplayed() throws Exception {
		home.verifyConvertButtonIsDisplayed();
	}
	
	@And("^Enter Value in Amount field$")
	public void entervalueInAmountField() throws Exception {
		home.entervalueInAmountField();
	}
	
	@And("^Choose GBP - british pound in From dropdown$")
	public void chooseGBPFromDropdown() throws Exception {
		home.chooseGBPFromDropdown();
	}
	
	@And("^Click Convert button$")
	public void clickConvertButton() throws Exception {
		home.clickConvertButton();
	}
	
	@And("^Verify Currency Conversion from GBP to EUR$")
	public void verifyCurrencyConversion() throws Exception {
		home.verifyCurrencyConversion();
	}
	
	@And("^Scroll down the page and Click on signIn and Send brightbile button$")
	public void scrolldownAndClickSignInButton() throws Exception {
		home.scrolldownAndClickSignInButton();
	}
	
	@And("^Enter \"(.*)\" under Email input box$")
	public void enterEmail(String value) throws Exception {
		home.enterEmailID(value);
	}
	
	@And("^Enter \"(.*)\" under Password input box$")
	public void enterPassword(String value) throws Exception {
		home.enterPassword(value);
	}
	
	@And("^Verify the registration button is enabled$")
	public void verifyRegistrationButtonEnabled() throws Exception {
		home.verifyRegistrationButtonIsEnabled();
	}
}
