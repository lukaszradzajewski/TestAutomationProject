package main.steps;

import com.github.javafaker.Faker;
import main.buildSettings.ContextInjection;
import main.buildSettings.TestCommons;
import main.buildSettings.TestEnvironment;
import com.google.inject.Inject;
import main.pages.AuthenticationPage;
import main.pages.MainPage;
import main.pages.RegistrationPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;

import java.util.List;

import static net.andreinc.mockneat.unit.address.Cities.cities;
import static net.andreinc.mockneat.unit.companies.Departments.departments;
import static net.andreinc.mockneat.unit.user.Emails.emails;
import static net.andreinc.mockneat.unit.user.Names.names;
import static net.andreinc.mockneat.unit.user.Passwords.passwords;

/**
 * Test_Automation-automationexercise
 *
 * @author lukasz.radzajewski
 **/

public class RegistrationPageSteps extends TestEnvironment {

    private final MainPage mainPage = new MainPage();
    private final TestCommons testCommons = new TestCommons();
    private final RegistrationPage registrationPage = new RegistrationPage();
    private final AuthenticationPage authenticationPage = new AuthenticationPage();

    private final ContextInjection contextInjection;

    @Inject
    public RegistrationPageSteps(ContextInjection contextInjection) {
        this.contextInjection = contextInjection;
    }

    @Step("I click on Sign in button")
    @When("I click on Sign in button")
    public void iClickOnSignInButton() throws Throwable {
        testCommons.customClick(mainPage.signInButton);
    }
    @Step("I write a Name")
    @When("I write a Name")
    public void iWriteName() throws Throwable {
        //ARRANGE//
        contextInjection.generatedName = faker.random().hex(5) + names().get();

        //ACT//
        testCommons.customSendKeys(authenticationPage.createAnAccountNameInput, contextInjection.generatedName);
        logger.info(String.format("User valid email: \"%S\"", contextInjection.generatedName));

        //ASSERT//
        Assert.assertEquals(authenticationPage.createAnAccountNameInput.getAttribute("value").toLowerCase(),
                contextInjection.generatedName.toLowerCase(), ContextInjection.VALUE_ERROR);
    }
    @Step("I write an email address")
    @When("I write an email address")
    public void iWriteAnEmailAddress() throws Throwable {
        //ARRANGE//
        contextInjection.generatedEmail = faker.random().hex(5) + emails().get();

        //ACT//
        testCommons.customSendKeys(authenticationPage.createAnAccountEmailInput, contextInjection.generatedEmail);
        logger.info(String.format("User valid email: \"%S\"", contextInjection.generatedEmail));

        //ASSERT//
        Assert.assertEquals(authenticationPage.createAnAccountEmailInput.getAttribute("value").toLowerCase(),
                contextInjection.generatedEmail.toLowerCase(), ContextInjection.VALUE_ERROR);
    }
// Invalid email cannot be tested due to front-end validation that forbids the user from writing strings without "@" in the middle
//    @Step("I write an invalid email address")
//    @When("I write an invalid email address")
//    public void iWriteAnInvalidEmailAddress() throws Throwable {
//        //ARRANGE//
//        final String userInvalidEmailAddress = testCommons.getRandomResourceBundleValue
//                (RESOURCE_BUNDLE_INVALID_EMAILS.getString("invalidEmails"));
//
//        //ACT//
//        testCommons.customSendKeys(authenticationPage.createAnAccountNameInput, contextInjection.generatedName);
//        testCommons.customSendKeys(authenticationPage.createAnAccountEmailInput, userInvalidEmailAddress);
//        logger.info(String.format("User invalid email: \"%S\"", userInvalidEmailAddress));
//
//        //ASSERT//
//        Assert.assertEquals(authenticationPage.createAnAccountEmailInput.getAttribute("value").toLowerCase(),
//                userInvalidEmailAddress.toLowerCase(), ContextInjection.VALUE_ERROR);
//    }

    @Step("I write an email address which is already in database")
    @When("I write an email address which is already in database")
    public void iWriteAnEmailAddressWhichIsAlreadyInDatabase() throws Throwable {
        //ARRANGE//
        final String userRegisteredEmailAddress = ContextInjection.DEFAULT_REGISTERED_EMAIL_ADDRESS;

        //ACT//
        testCommons.customSendKeys(authenticationPage.createAnAccountNameInput, contextInjection.generatedName);
        testCommons.customSendKeys(authenticationPage.createAnAccountEmailInput, userRegisteredEmailAddress);
        logger.info(String.format("User registered email: \"%S\"", userRegisteredEmailAddress));

        //ASSERT//
        Assert.assertEquals(authenticationPage.createAnAccountEmailInput.getAttribute("value").toLowerCase(),
                userRegisteredEmailAddress.toLowerCase(), ContextInjection.VALUE_ERROR);
    }

    @Step("I click on Create An Account button")
    @And("I click on Create An Account button")
    public void iClickOnCreateAnAccountButton() throws Throwable {
        testCommons.customClick(authenticationPage.createAnAccountButton);
    }

    @Step("I can see signup form")
    @Then("I can see signup form")
    public void iCanSeeSignUpForm() throws Throwable {
        Assert.assertTrue(testCommons.isElementVisible(authenticationPage.signUpPanel),
                String.format(ContextInjection.VIEW_ERROR, "Registration page form"));
    }

    @Step("I can see account creation page form")
    @Then("I can see account creation page form")
    public void iCanSeeAccountCreationPageForm() throws Throwable {
        Assert.assertTrue(testCommons.isElementVisible(registrationPage.accountCreationPanel),
                String.format(ContextInjection.VIEW_ERROR, "Account creation page form"));
    }

    @Step("I write following data to registration form")
    @And("I write following data to registration form")
    public void iWriteFollowingDataToRegistrationForm(DataTable dataTable) throws Throwable {
        //ARRANGE//
        //data.get(Row).get(Column)
        List<List<String>> data = dataTable.asLists();
        final String userFirstName = data.get(1).get(0);
        final String userLastName = data.get(1).get(1);
        final String userPassword = data.get(1).get(2);
        final String userAddress = data.get(1).get(3);
        final String userCity = data.get(1).get(4);
        final String userState = data.get(1).get(5);
        final String userPostalCode = data.get(1).get(6);
        final String userCountry = data.get(1).get(7);
        final String userMobilePhone = data.get(1).get(8);

        //ACT//
        testCommons.customSendKeys(registrationPage.firstNameInput, userFirstName);
        logger.info(String.format("User first name: \"%S\"", userFirstName));
        Assert.assertEquals(registrationPage.firstNameInput.getAttribute("value").toLowerCase(),
                userFirstName.toLowerCase(), ContextInjection.VALUE_ERROR);

        testCommons.customSendKeys(registrationPage.lastNameInput, userLastName);
        logger.info(String.format("User last name: \"%S\"", userLastName));
        Assert.assertEquals(registrationPage.lastNameInput.getAttribute("value").toLowerCase(),
                userLastName.toLowerCase(), ContextInjection.VALUE_ERROR);

        testCommons.customSendKeys(registrationPage.passwordInput, userPassword);
        logger.info(String.format("User password: \"%S\"", userPassword));
        Assert.assertEquals(registrationPage.passwordInput.getAttribute("value").toLowerCase(),
                userPassword.toLowerCase(), ContextInjection.VALUE_ERROR);

        testCommons.customSendKeys(registrationPage.addressInput, userAddress);
        logger.info(String.format("User address: \"%S\"", userAddress));
        Assert.assertEquals(registrationPage.addressInput.getAttribute("value").toLowerCase(),
                userAddress.toLowerCase(), ContextInjection.VALUE_ERROR);

        testCommons.customSendKeys(registrationPage.cityInput, userCity);
        logger.info(String.format("User city: \"%S\"", userCity));
        Assert.assertEquals(registrationPage.cityInput.getAttribute("value").toLowerCase(),
                userCity.toLowerCase(), ContextInjection.VALUE_ERROR);

        testCommons.customSendKeys(registrationPage.stateInput, userState);
        logger.info(String.format("User state: \"%S\"", userState));
        Assert.assertEquals(registrationPage.stateInput.getAttribute("value").toLowerCase(),
                userState.toLowerCase(), ContextInjection.VALUE_ERROR);

        testCommons.customSendKeys(registrationPage.zipcodeCodeInput, userPostalCode);
        logger.info(String.format("User postal code: \"%S\"", userPostalCode));
        Assert.assertEquals(registrationPage.zipcodeCodeInput.getAttribute("value").toLowerCase(),
                userPostalCode.toLowerCase(), ContextInjection.VALUE_ERROR);

        testCommons.selectFromDropdownByText(userCountry, registrationPage.countryDropDown);
        logger.info(String.format("User country: \"%S\"", userCountry));
        Assert.assertEquals(registrationPage.countryDropDown.getText().toLowerCase(),
                userCountry.toLowerCase(), ContextInjection.VALUE_ERROR);

        testCommons.customSendKeys(registrationPage.mobilePhoneInput, userMobilePhone);
        logger.info(String.format("User mobile phone: \"%S\"", userMobilePhone));
        Assert.assertEquals(registrationPage.mobilePhoneInput.getAttribute("value").toLowerCase(),
                userMobilePhone.toLowerCase(), ContextInjection.VALUE_ERROR);
    }

    @Step("I choose gender")
    @And("I choose gender")
    public void iChooseGender() throws Throwable {
        //ARRANGE//
        final int randomNumber = testCommons.getRandomIntValue(2, 1);

        //ACT//
        if (randomNumber == 1) {
            testCommons.customClick(registrationPage.mrButton);
            logger.info("User gender: \"Male\"");
            //ASSERT//
            Assert.assertTrue(registrationPage.mrButton.isSelected());
        } else {
            testCommons.customClick(registrationPage.mrsButton);
            logger.info("User gender: \"Female\"");
            //ASSERT//
            Assert.assertTrue(registrationPage.mrsButton.isSelected());
        }
    }
    @Step("I check if Name is already written and valid")
    @And("I check if Name is already written and valid")
    public void iCheckIfNameIsAlreadyWrittenAndValid() throws Throwable {
        Assert.assertEquals(registrationPage.nameInput.getAttribute("value").toLowerCase(),
                contextInjection.generatedName.toLowerCase(), ContextInjection.VALUE_ERROR);
    }
    @Step("I check if email is already written and valid")
    @And("I check if email is already written and valid")
    public void iCheckIfEmailIsAlreadyWrittenAndValid() throws Throwable {
        Assert.assertEquals(registrationPage.emailInput.getAttribute("value").toLowerCase(),
                contextInjection.generatedEmail.toLowerCase(), ContextInjection.VALUE_ERROR);
    }

    @Step("I write password")
    @And("I write password")
    public void iWritePassword() throws Throwable {
        //ARRANGE//
        final String userPassword = passwords().medium().val();

        //ACT//
        testCommons.customSendKeys(registrationPage.passwordInput, userPassword);
        logger.info(String.format("User password: \"%S\"", userPassword));

        //ASSERT//
        Assert.assertEquals(registrationPage.passwordInput.getAttribute("value").toLowerCase(),
                userPassword.toLowerCase(), ContextInjection.VALUE_ERROR);
    }

    @Step("I write my first name")
    @And("I write my first name")
    public void iWriteMyFirstName() throws Throwable {
        //ARRANGE//
        final String userFirstName = names().first().val();

        //ACT//
        testCommons.customSendKeys(registrationPage.firstNameInput, userFirstName);
        logger.info(String.format("User first name: \"%S\"", userFirstName));

        //ASSERT//
        Assert.assertEquals(registrationPage.firstNameInput.getAttribute("value").toLowerCase(),
                userFirstName.toLowerCase(), ContextInjection.VALUE_ERROR);
    }

    @Step("I write my last name")
    @And("I write my last name")
    public void iWriteMyLastName() throws Throwable {
        //ARRANGE//
        final String userLastName = names().last().val();

        //ACT//
        testCommons.customSendKeys(registrationPage.lastNameInput, userLastName);
        logger.info(String.format("User last name: \"%S\"", userLastName));

        //ASSERT//
        Assert.assertEquals(registrationPage.lastNameInput.getAttribute("value").toLowerCase(),
                userLastName.toLowerCase(), ContextInjection.VALUE_ERROR);
    }

    @Step("I choose date of birth")
    @And("I choose date of birth")
    public void iChooseDateOfBirth() throws Throwable {
        //ARRANGE//
        final int day = testCommons.getRandomIntValue(28, 1);
        final int month = testCommons.getRandomIntValue(12, 1);
        final int year = testCommons.getRandomIntValue(2021, 1900);

        //ACT//
        testCommons.selectFromDropdownByValue(Integer.toString(day), registrationPage.dayOfBirth);
        testCommons.selectFromDropdownByValue(Integer.toString(month), registrationPage.monthOfBirth);
        testCommons.selectFromDropdownByValue(Integer.toString(year), registrationPage.yearOfBirth);
        logger.info(String.format("User birthday: \"%d-%d-%d\"", day, month, year));

    }

    @Step("I sign in to receive newsletter and special offers")
    @And("I sign in to receive newsletter and special offers")
    public void iSignInToReceiveNewsletterAndSpecialOffers() throws Throwable {
        //ARRANGE//
        int tempRandomValue = testCommons.getRandomIntValue(3, 3);

        //ACT//
        if (tempRandomValue == 1) {
            if (!registrationPage.newsletterCheckbox.isSelected()) {
                registrationPage.newsletterCheckbox.click();
                logger.info("User signed to receive newsletter");
            }
            //ASSERT//
            Assert.assertTrue(registrationPage.newsletterCheckbox.isSelected());
        } else if (tempRandomValue == 2) {
            if (!registrationPage.specialOffersCheckbox.isSelected()) {
                registrationPage.specialOffersCheckbox.click();
                logger.info("User signed to receive special offers");
            }
            //ASSERT//
            Assert.assertTrue(registrationPage.specialOffersCheckbox.isSelected());
        } else {
            if (!registrationPage.newsletterCheckbox.isSelected() && !registrationPage.specialOffersCheckbox.isSelected()) {
                registrationPage.newsletterCheckbox.click();
                registrationPage.specialOffersCheckbox.click();
                logger.info("User signed to receive newsletter & special offers");
            }
            //ASSERT//
            Assert.assertTrue(registrationPage.newsletterCheckbox.isSelected() &&
                    registrationPage.specialOffersCheckbox.isSelected());
        }
    }

    @Step("I check if my name & email are already written and are correct")
    @And("I check if my name & email are already written and are correct")
    public void iCheckIfMyNameAndEmailAreAlreadyWrittenAndAreCorrect() throws Throwable {
        Assert.assertEquals(registrationPage.nameInput.getAttribute("value").toLowerCase(),
                registrationPage.assertNameInput.getAttribute("value").toLowerCase(), ContextInjection.VALUE_ERROR);
        Assert.assertEquals(registrationPage.emailInput.getAttribute("value").toLowerCase(),
                registrationPage.assertEmailInput.getAttribute("value").toLowerCase(), ContextInjection.VALUE_ERROR);
    }

    @Step("I write company name")
    @And("I write company name")
    public void iWriteCompanyName() throws Throwable {
        //ARRANGE//
        final String companyName = departments().val();

        //ACT//
        testCommons.customSendKeys(registrationPage.companyInput, companyName);
        logger.info(String.format("User company name: \"%S\"", companyName));

        //ASSERT//
        Assert.assertEquals(registrationPage.companyInput.getAttribute("value").toLowerCase(),
                companyName.toLowerCase(), ContextInjection.VALUE_ERROR);
    }

    @Step("I write my addresses")
    @And("I write my addresses")
    public void iWriteMyAddresses() throws Throwable {
        //ARRANGE//
        final String userAddress = faker.address().streetName();
        final String userSecondAddress = faker.address().secondaryAddress() + faker.address().buildingNumber();

        //ACT//
        testCommons.customSendKeys(registrationPage.addressInput, userAddress);
        testCommons.customSendKeys(registrationPage.addressSecondInput, userSecondAddress);
        logger.info(String.format("User addresses: \"%S\" and \"%S\"", userAddress, userSecondAddress));

        //ASSERT//
        Assert.assertEquals(registrationPage.addressInput.getAttribute("value").toLowerCase(),
                userAddress.toLowerCase(), ContextInjection.VALUE_ERROR);
        Assert.assertEquals(registrationPage.addressSecondInput.getAttribute("value").toLowerCase(),
                userSecondAddress.toLowerCase(), ContextInjection.VALUE_ERROR);
    }

    @Step("I write my address")
    @And("I write my address")
    public void iWriteMyAddress() throws Throwable {
        //ARRANGE//
        final String userAddress = faker.address().streetName() + faker.address().buildingNumber();

        //ACT//
        testCommons.customSendKeys(registrationPage.addressInput, userAddress);
        logger.info(String.format("User address: \"%S\"", userAddress));

        //ASSERT//
        Assert.assertEquals(registrationPage.addressInput.getAttribute("value").toLowerCase(),
                userAddress.toLowerCase(), ContextInjection.VALUE_ERROR);
    }

    @Step("I choose country *{0}*")
    @And("I choose country {string}")
    public void iChooseCountry(String country) throws Throwable {
        //ARRANGE//
        final String defaultCountry = ContextInjection.DEFAULT_CUSTOMER_COUNTRY;

        //ACT//
        if (registrationPage.countryDropDown

                .getText().toLowerCase().contains(defaultCountry.toLowerCase()) &&
                !registrationPage.countryDropDown.getText().toLowerCase().equals(country.toLowerCase())) {
            testCommons.selectFromDropdownByText(country, registrationPage.countryDropDown);
        }
        logger.info(String.format("User chosen country: \"%S\"", country));

        //ASSERT//
//        Assert.assertEquals(registrationPage.countryDropDown.getText().toLowerCase(),
//                country.toLowerCase(), ContextInjection.VALUE_ERROR);
    }
    @Step("I write state")
    @And("I write state")
    public void iChooseState() throws Throwable {
        //ARRANGE//
        Faker faker = new Faker();
        final String userState = faker.address().state();

        //ACT
        testCommons.customSendKeys(registrationPage.stateInput, userState);
        logger.info(String.format("User chosen state: \"%S\"", userState));

        //ASSERT//
        Assert.assertEquals(registrationPage.stateInput.getAttribute("value").toLowerCase(),
                userState.toLowerCase(), ContextInjection.VALUE_ERROR);
    }
    @Step("I write city name")
    @And("I write city name")
    public void iWriteCityName() throws Throwable {
        //ARRANGE//
        final String userCity = cities().us().val();

        //ACT//
        testCommons.customSendKeys(registrationPage.cityInput, userCity);
        logger.info(String.format("User chosen city: \"%S\"", userCity));

        //ASSERT//
        Assert.assertEquals(registrationPage.cityInput.getAttribute("value").toLowerCase(),
                userCity.toLowerCase(), ContextInjection.VALUE_ERROR);
    }
    @Step("I write postal code")
    @And("I write postal code")
    public void iWritePostalCode() throws Throwable {
        //ARRANGE//
        Faker faker = new Faker();
        final String userPostalCode = StringUtils.left(faker.address().zipCode(), 5);

        //ACT//
        testCommons.customSendKeys(registrationPage.zipcodeCodeInput, userPostalCode);
        logger.info(String.format("User postal code: \"%S\"", userPostalCode));

        //ASSERT//
        Assert.assertEquals(registrationPage.zipcodeCodeInput.getAttribute("value").toLowerCase(),
                userPostalCode.toLowerCase(), ContextInjection.VALUE_ERROR);
    }

    @Step("I write mobile phone")
    @And("I write mobile phone")
    public void iWriteMobilePhone() throws Throwable {
        //ARRANGE//
        Faker faker = new Faker();
        final String userMobilePhone = faker.phoneNumber().cellPhone();

        //ACT//
        testCommons.customSendKeys(registrationPage.mobilePhoneInput, userMobilePhone);
        logger.info(String.format("User mobile phone number: \"%S\"", userMobilePhone));

        //ARRANGE//
        Assert.assertEquals(registrationPage.mobilePhoneInput.getAttribute("value").toLowerCase(),
                userMobilePhone.toLowerCase(), ContextInjection.VALUE_ERROR);
    }

    @Step("I click on Register button")
    @And("I click on Register button")
    public void iClickOnRegisterButton() throws Throwable {
        testCommons.customClick(registrationPage.createAccountButton);
    }

    @Step("I can see logged in icon")
    @Then("I can see logged in icon")
    public void iCanSeeLoggedInIcon() throws Throwable {
        Assert.assertTrue(testCommons.isElementVisible(mainPage.userIsLoggedIcon));
    }
    @Step("I can see registration error")
    @Then("I can see registration error")
    public void iCanSeeRegistrationError() throws Throwable {
        Assert.assertTrue(testCommons.isElementVisible(authenticationPage.registeredLoginError),
                String.format(ContextInjection.VIEW_ERROR, "Registration error header"));
    }

}