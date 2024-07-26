package main.steps;

import main.DriverFactory;
import main.buildSettings.ContextInjection;
import main.buildSettings.TestCommons;
import main.buildSettings.TestEnvironment;
import main.pages.MainPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.testng.Assert;

/**
 * Test_Automation-automationexercise
 *
 * @author lukasz.radzajewski
 **/

public class MainPageSteps extends TestEnvironment {

    private final MainPage mainPage = new MainPage();
    private final AuthenticationPageSteps authenticationPageSteps = new AuthenticationPageSteps();
    private final RegistrationPageSteps registrationPageSteps = new RegistrationPageSteps(new ContextInjection());
    private final TestCommons testCommons = new TestCommons();

    @Step("I open home page")
    @Given("I open home page")
    public void iOpenHomePage() throws Throwable {
        //ARRANGE//
        final String expectedPageURL = "https://automationexercise.com/";

        //ACT//
        DriverFactory.getDriver().get(HOME_URL);
        testCommons.customClick(mainPage.consentButton);
        //ASSERT//
        Assert.assertEquals(DriverFactory.getDriver().getCurrentUrl(), expectedPageURL, ContextInjection._21VOID);
    }

//    @Step("I click on Consent button")
//    @When("I click on Consent button")
//    public void iClickOnConsentButton() throws Throwable {
//        testCommons.customClick(mainPage.consentButton);
//    }

    @Step("I can see automationexercise.com website")
    @Given("I can see automationexercise.com website")
    public void iCanSeeautomationexerciseComWebsite() throws Throwable {
        //ARRANGE//
        final boolean isPageReadyToExecuteTests;

        //ACT//
        isPageReadyToExecuteTests = TestCommons.isPageReady();
        logger.info(String.format("Page ready: \"%S\"", isPageReadyToExecuteTests));

        //ASSERT//
        Assert.assertTrue(isPageReadyToExecuteTests, ContextInjection.PAGE_ERROR);
    }

    @Step("I am logged as customer *{0}* using *{1}* password")
    @When("I am logged as customer {string} using {string} password")
    public void iAmLoggedAsCustomerUsingPassword(String email, String password) throws Throwable {
        //ARRANGE//
        final String defaultUserName = ContextInjection.DEFAULT_CUSTOMER_USER_NAME;

        //ACT//
        registrationPageSteps.iClickOnSignInButton();
        authenticationPageSteps.iEnterLogin(email);
        authenticationPageSteps.iEnterPassword(password);
        authenticationPageSteps.iClickOnLoginButton();

        //ASSERT//
        Assert.assertEquals(mainPage.userIsLoggedInMessage.getText().toLowerCase(), defaultUserName.toLowerCase(), ContextInjection.VALUE_ERROR);
    }

    @Step("I am Logged In")
    @Given("I am Logged In")
    public void iAmLoggedIn() throws Throwable {
        registrationPageSteps.iCanSeeLoggedInIcon();
    }
}