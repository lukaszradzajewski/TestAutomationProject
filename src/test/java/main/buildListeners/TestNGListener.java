package main.buildListeners;

import main.buildSettings.ContextInjection;
import main.buildSettings.MessageBuilder;
import main.buildSettings.TestEnvironment;
import io.cucumber.java.Scenario;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Test_Automation-automationpractice
 *
 * @author lukasz.radzajewski
 **/

public class TestNGListener implements ITestListener {

    public static List<String> passedTests = new ArrayList<>();
    public static List<String> failedTests = new ArrayList<>();

    private final MessageBuilder messageBuilder = new MessageBuilder();
    private final TestEnvironment testEnvironment = new TestEnvironment();

    /**
     * For TestNG tests
     */
    @Override
    public synchronized void onStart(ITestContext iTestContext) {
       // deleteOldLogs();
        messageBuilder.messageStartSuite(iTestContext);
    }

    @Override
    public synchronized void onFinish(ITestContext iTestContext) {
        messageBuilder.messageEndSuite(iTestContext);
        testEnvironment.allureWriteExecutors();
        testEnvironment.allureWriteProperties();
        ContextInjection.passedTestsAmount = passedTests.size();
        ContextInjection.failedTestsAmount = failedTests.size();
        //suiteResultsCleaner();
    }

    @Override
    public synchronized void onTestStart(ITestResult iTestResult) {
        messageBuilder.messageStartTest(iTestResult);
    }

    @Override
    public synchronized void onTestSuccess(ITestResult iTestResult) {
        messageBuilder.messageSuccessTest();
        passedTests.add(MessageBuilder.getTestDescription(iTestResult));
    }

 //   @Override
//    public synchronized void onTestFailure(ITestResult iTestResult) {
//        messageBuilder.messageFailTest();
//        failedTests.add(MessageBuilder.getTestDescription(iTestResult));
//        logger.error(String.valueOf(iTestResult.getThrowable()));
//        if (DriverFactory.getDriver() != null) {
//            allureSaveScreenshotPNG();
//        }
//        allureSaveTextLog(iTestResult);
//    }

    /**
     * For Cucumber tests
     **/
    public void onScenarioStart(Scenario scenario) {
        messageBuilder.messageStartScenario(scenario);
    }

    public void onScenarioFinish(Scenario scenario) throws IOException {
        messageBuilder.messageFinishScenario(scenario);
    }

}