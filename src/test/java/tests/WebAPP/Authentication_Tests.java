package tests.WebAPP;

import main.buildListeners.TestNGListener;
import main.buildSettings.ContextInjection;
import main.steps.AuthenticationPageSteps;
import main.steps.MainPageSteps;
import main.steps.RegistrationPageSteps;
import main.steps.hooks.WEB_Hooks;
import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Test_Automation-automatioexercise
 *
 * @author lukasz.radzajewski
 **/

@Epic("Web App Tests")
@Feature("LOGIN TESTS")
@Listeners({TestNGListener.class})
public class Authentication_Tests extends WEB_Hooks {

    @Issue("TAP-0001")
    @TmsLink("STORY-111")
    @Story("POSITIVE FLOW")
    @Owner("Lukasz Radzajewski")
    @Severity(SeverityLevel.CRITICAL)
    @Description("[US-111]/[1] As a user I can log into automationexercise.com using registered email \"lucastest1337@gmail.com\" & password \"12345\"")
    @Test(description = "[US-111]/[1] I can log into automationexercise.com using registered email \"lucastest1337@gmail.com\" & password \"12345\"",
            priority = 0)
    public void test_1() throws Throwable {
        //ARRANGE//
        final MainPageSteps mainPageSteps = new MainPageSteps();
        final RegistrationPageSteps registrationPageSteps = new RegistrationPageSteps(new ContextInjection());
        final AuthenticationPageSteps authenticationPageSteps = new AuthenticationPageSteps();


        //ACT//
        mainPageSteps.iOpenHomePage();
        mainPageSteps.iCanSeeautomationexerciseComWebsite();
        registrationPageSteps.iClickOnSignInButton();
        authenticationPageSteps.iCanSeeLoginForm();
        authenticationPageSteps.iEnterLogin("lucastest1337@gmail.com");
        authenticationPageSteps.iEnterPassword("12345");
        authenticationPageSteps.iClickOnLoginButton();

        //ASSERT
        registrationPageSteps.iCanSeeLoggedInIcon();
    }

    @Issue("TAP-0002")
    @TmsLink("STORY-111")
    @Story("NEGATIVE FLOW")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Lukasz Radzajewski")
    @Description("[US-111]/[2] As a user I can't log into automationexercise.com using email \"wrongmail@example.com\" & password \"12345\"")
    @Test(description = "[US-111]/[2] I can't log into automationexercise.com using email \"wrongmail@example.com\" & password \"12345\"",
            priority = 0)
    public void test_2() throws Throwable {
        //ARRANGE//
        final MainPageSteps mainPageSteps = new MainPageSteps();
        final RegistrationPageSteps registrationPageSteps = new RegistrationPageSteps(new ContextInjection());
        final AuthenticationPageSteps authenticationPageSteps = new AuthenticationPageSteps();


        //ACT//
        mainPageSteps.iOpenHomePage();
        mainPageSteps.iCanSeeautomationexerciseComWebsite();
        registrationPageSteps.iClickOnSignInButton();
        authenticationPageSteps.iCanSeeLoginForm();
        authenticationPageSteps.iEnterLogin("wrongmail@example.com");
        authenticationPageSteps.iEnterPassword("12345");
        authenticationPageSteps.iClickOnLoginButton();

        //ASSERT//
        authenticationPageSteps.iCanSeeWarningMessageWithInclude("Your email or password is incorrect!");
    }

    @Issue("TAP-0003")
    @TmsLink("STORY-111")
    @Story("NEGATIVE FLOW")
    @Owner("Lukasz Radzajewski")
    @Severity(SeverityLevel.NORMAL)
    @Description("[US-111]/[2] As a user I can't log into automationexercise.com using email \"lucastest1337@gmail.com\" & password \"wrongpass\"")
    @Test(description = "[US-111]/[3] I can't log into automationexercise.com using email \"lucastest1337@gmail.com\" & password \"wrongpass\"",
            priority = 0)
    public void test_3() throws Throwable {
        //ARRANGE//
        final MainPageSteps mainPageSteps = new MainPageSteps();
        final RegistrationPageSteps registrationPageSteps = new RegistrationPageSteps(new ContextInjection());
        final AuthenticationPageSteps authenticationPageSteps = new AuthenticationPageSteps();


        //ACT//
        mainPageSteps.iOpenHomePage();
        mainPageSteps.iCanSeeautomationexerciseComWebsite();
        registrationPageSteps.iClickOnSignInButton();
        authenticationPageSteps.iCanSeeLoginForm();
        authenticationPageSteps.iEnterLogin("lucastest1337@gmail.com");
        authenticationPageSteps.iEnterPassword("wrongpass");
        authenticationPageSteps.iClickOnLoginButton();

        //ASSERT//
        authenticationPageSteps.iCanSeeWarningMessageWithInclude("Your email or password is incorrect!");
    }
}