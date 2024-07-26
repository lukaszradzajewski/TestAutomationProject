package main.steps.hooks;

import main.DriverFactory;
import main.buildSettings.MessageBuilder;
import org.slf4j.MDC;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

/**
 * Test_Automation-automatioexercise
 *
 * @author lukasz.radzajewski
 **/

public class API_Hooks extends DriverFactory implements ITestListener {



    @BeforeMethod(alwaysRun = true, description = "Setting up Test Class")
    public void beforeTest(ITestResult iTestResult) {
        MDC.put("testid", MessageBuilder.getTestDescription(iTestResult));
    }

    @AfterMethod(alwaysRun = true, description = "Teardown Test Class")
    public void afterTest() {
        MDC.remove("testid");
    }
}