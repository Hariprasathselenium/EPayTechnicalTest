package project.Hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import project.Base.DriverManager;
import project.Base.TestBase;
import project.Utility.UtilsManager;

public class Hooks extends TestBase {


    @BeforeStep
    public void BeforeSteps() {

    }

    @Before
    public synchronized void beforeScenario(Scenario scenario) throws Exception {
    	
    }

    @After
    public synchronized void afterScenario(Scenario scenario) throws Exception {
    	if(executionType.equalsIgnoreCase("MOBILE")) {
    		if (scenario.getStatus() == Status.PASSED) {
                scenario.attach(UtilsManager.screenshot(),"image/png","screenshot_"+System.currentTimeMillis());
            } else {
                scenario.attach(UtilsManager.screenshot(),"image/png","screenshot_"+System.currentTimeMillis());
            }
            DriverManager.getDriver().quit();
    	}else {
    		if (scenario.getStatus() == Status.PASSED) {
                scenario.attach(UtilsManager.screenshot(),"image/png","screenshot_"+System.currentTimeMillis());
            } else {
                scenario.attach(UtilsManager.screenshot(),"image/png","screenshot_"+System.currentTimeMillis());
            }
            DriverManager.getWebDriver().close();
    	}
        
    }
}