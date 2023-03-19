package project.Base;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

public class AbstractTestNG extends TestBase {

	private TestNGCucumberRunner testNGCucumberRunner;

	@BeforeClass(alwaysRun = true)
	public void setUpClass() {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}

	@SuppressWarnings("unused")
	@Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
	public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
		try {
			String featureNameCucumber = featureWrapper.toString();
			int start=featureNameCucumber.indexOf('[');
			int end=featureNameCucumber.indexOf(']');
			featureNameCucumber = featureNameCucumber.substring(start+1, end);
			if(featureName.get()==null) {
				CreateFeatureNode(featureNameCucumber);
				featureName.set(featureWrapper.toString());
			}
			else if (!featureName.get().equals(featureWrapper.toString())) {
				CreateFeatureNode(featureNameCucumber);
				featureName.set(featureWrapper.toString());
			}
			else {
			}
		}
		catch (Exception e) {
		}
		testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
	}

	/**
	 * Returns two dimensional array of {@link PickleWrapper}s with their
	 * associated {@link FeatureWrapper}s.
	 *
	 * @return a two dimensional array of scenarios features.
	 */
	@DataProvider
	public Object[][] scenarios() {
		if (testNGCucumberRunner == null) {
			return new Object[0][0];
		}
		return testNGCucumberRunner.provideScenarios();
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() {
		if (testNGCucumberRunner == null) {
			return;
		}
		testNGCucumberRunner.finish();
	}
}
