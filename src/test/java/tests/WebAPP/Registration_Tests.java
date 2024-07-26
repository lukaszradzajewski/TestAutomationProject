package tests.WebAPP;

import main.buildListeners.TestNGListener;
import main.buildSettings.ContextInjection;
import main.steps.MainPageSteps;
import main.steps.RegistrationPageSteps;
import main.steps.hooks.WEB_Hooks;
import io.cucumber.datatable.DataTable;
import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Test_Automation-automationexercise
 *
 * @author lukasz.radzajewski
 **/

@Epic("Web App Tests")
@Feature("REGISTRATION TESTS")
@Listeners({TestNGListener.class})
public class Registration_Tests extends WEB_Hooks {

    @Issue("TAP-0013")
    @TmsLink("STORY-333")
    @Story("POSITIVE FLOW")
    @Owner("Lukasz Radzajewski")
    @Severity(SeverityLevel.CRITICAL)
    @Description("[US-333]/[1] As a user I check availability of registration page form")
    @Test(description = "[US-333]/[1] I check availability of registration page form",
            priority = 0)
    public void test_1() throws Throwable {
        //ARRANGE//
        final MainPageSteps mainPageSteps = new MainPageSteps();
        final RegistrationPageSteps registrationPageSteps = new RegistrationPageSteps(new ContextInjection());


        //ACT//
        mainPageSteps.iOpenHomePage();
        mainPageSteps.iCanSeeautomationexerciseComWebsite();
        registrationPageSteps.iClickOnSignInButton();
        registrationPageSteps.iCanSeeSignUpForm();
        registrationPageSteps.iWriteName();
        registrationPageSteps.iWriteAnEmailAddress();
        registrationPageSteps.iClickOnCreateAnAccountButton();

        //ASSERT//
        registrationPageSteps.iCanSeeAccountCreationPageForm();
    }

    @Issue("TAP-0014")
    @TmsLink("STORY-333")
    @Story("POSITIVE FLOW")
    @Owner("Lukasz Radzajewski")
    @Severity(SeverityLevel.CRITICAL)
    @Description("[US-333]/[2] As a user I can create an account by filling up all fields")
    @Test(description = "[US-333]/[2] I can create an account by filling up all fields",
            priority = 1, dependsOnMethods = {"test_1"})
    public void test_2() throws Throwable {
        //ARRANGE//
        final MainPageSteps mainPageSteps = new MainPageSteps();
        final RegistrationPageSteps registrationPageSteps = new RegistrationPageSteps(new ContextInjection());


        //ACT//
        mainPageSteps.iOpenHomePage();
        mainPageSteps.iCanSeeautomationexerciseComWebsite();
        registrationPageSteps.iClickOnSignInButton();
        registrationPageSteps.iCanSeeSignUpForm();
        registrationPageSteps.iWriteName();
        registrationPageSteps.iWriteAnEmailAddress();
        registrationPageSteps.iClickOnCreateAnAccountButton();
        registrationPageSteps.iChooseGender();
        registrationPageSteps.iCheckIfNameIsAlreadyWrittenAndValid();
        registrationPageSteps.iCheckIfEmailIsAlreadyWrittenAndValid();
        registrationPageSteps.iWritePassword();
        registrationPageSteps.iWriteMyFirstName();
        registrationPageSteps.iWriteMyLastName();
        registrationPageSteps.iChooseDateOfBirth();
        registrationPageSteps.iSignInToReceiveNewsletterAndSpecialOffers();
        registrationPageSteps.iWriteCompanyName();
        registrationPageSteps.iWriteMyAddresses();
        registrationPageSteps.iChooseCountry("United States");
        registrationPageSteps.iWriteCityName();
        registrationPageSteps.iChooseState();
        registrationPageSteps.iWritePostalCode();
        registrationPageSteps.iWriteMobilePhone();
        registrationPageSteps.iClickOnRegisterButton();

        //ASSERT//
        registrationPageSteps.iCanSeeLoggedInIcon();
    }

    @Issue("TAP-0015")
    @TmsLink("STORY-333")
    @Story("POSITIVE FLOW")
    @Owner("Lukasz Radzajewski")
    @Severity(SeverityLevel.CRITICAL)
    @Description("[US-333]/[3] As a user I can create an account by filling up only required fields")
    @Test(description = "[US-333]/[3] I can create an account by filling up only required fields",
            priority = 1, dependsOnMethods = {"test_1"})
    public void test_3() throws Throwable {
        //ARRANGE//
        final MainPageSteps mainPageSteps = new MainPageSteps();
        final RegistrationPageSteps registrationPageSteps = new RegistrationPageSteps(new ContextInjection());


        //ACT//
        mainPageSteps.iOpenHomePage();
        mainPageSteps.iCanSeeautomationexerciseComWebsite();
        registrationPageSteps.iClickOnSignInButton();
        registrationPageSteps.iCanSeeSignUpForm();
        registrationPageSteps.iWriteName();
        registrationPageSteps.iWriteAnEmailAddress();
        registrationPageSteps.iClickOnCreateAnAccountButton();
        registrationPageSteps.iChooseGender();
        registrationPageSteps.iCheckIfNameIsAlreadyWrittenAndValid();
        registrationPageSteps.iCheckIfEmailIsAlreadyWrittenAndValid();
        registrationPageSteps.iWritePassword();
        registrationPageSteps.iWriteMyFirstName();
        registrationPageSteps.iWriteMyLastName();
        registrationPageSteps.iWriteMyAddresses();
        registrationPageSteps.iChooseCountry("United States");
        registrationPageSteps.iWriteCityName();
        registrationPageSteps.iChooseState();
        registrationPageSteps.iWritePostalCode();
        registrationPageSteps.iWriteMobilePhone();
        registrationPageSteps.iClickOnRegisterButton();

        //ASSERT//
        registrationPageSteps.iCanSeeLoggedInIcon();
    }

    @Issue("TAP-0016")
    @TmsLink("STORY-333")
    @Story("NEGATIVE FLOW")
    @Owner("Lukasz Radzajewski")
    @Severity(SeverityLevel.NORMAL)
    @Description("US-333]/[4] As a user I can't create an account without filling up fields")
    @Test(description = "US-333]/[4] I can't create an account without filling up fields",
            priority = 1, dependsOnMethods = {"test_1"})
    public void test_4() throws Throwable {
        //ARRANGE//
        final MainPageSteps mainPageSteps = new MainPageSteps();
        final RegistrationPageSteps registrationPageSteps = new RegistrationPageSteps(new ContextInjection());


        //ACT//
        mainPageSteps.iOpenHomePage();
        mainPageSteps.iCanSeeautomationexerciseComWebsite();
        registrationPageSteps.iClickOnSignInButton();
        registrationPageSteps.iCanSeeSignUpForm();
        registrationPageSteps.iWriteName();
        registrationPageSteps.iWriteAnEmailAddress();
        registrationPageSteps.iClickOnCreateAnAccountButton();
        registrationPageSteps.iClickOnRegisterButton();

        //ASSERT//

    }
    @Issue("TAP-0018")
    @TmsLink("STORY-333")
    @Story("NEGATIVE FLOW")
    @Owner("Lukasz Radzajewski")
    @Severity(SeverityLevel.CRITICAL)
    @Description("[US-333]/[6] As a user I can't create an account, when email is already in database")
    @Test(description = "[US-333]/[6] I can't create an account, when email is already in database",
            priority = 1, dependsOnMethods = {"test_1"})
    public void test_6() throws Throwable {
        //ARRANGE//
        final MainPageSteps mainPageSteps = new MainPageSteps();
        final RegistrationPageSteps registrationPageSteps = new RegistrationPageSteps(new ContextInjection());


        //ACT//
        mainPageSteps.iOpenHomePage();
        mainPageSteps.iCanSeeautomationexerciseComWebsite();
        registrationPageSteps.iClickOnSignInButton();
        registrationPageSteps.iCanSeeSignUpForm();
        registrationPageSteps.iWriteName();
        registrationPageSteps.iWriteAnEmailAddressWhichIsAlreadyInDatabase();
        registrationPageSteps.iClickOnCreateAnAccountButton();

        //ASSERT//
        registrationPageSteps.iCanSeeRegistrationError();
    }

}