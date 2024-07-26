package main.pages;

import main.pages.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Test_Automation-automationexercise
 *
 * @author lukasz.radzajewski
 **/

public class RegistrationPage extends BasePage<RegistrationPage> {

    //VIEW//
    @FindBy(how = How.XPATH, using = "//div[@class='login-form']")
    public WebElement accountCreationPanel;

    //MESSAGES//
//    @FindBy(how = How.XPATH, using = "//div[@class='alert alert-danger']")
//    public WebElement registerError;

    //BUTTONS & INPUTS & DROPDOWN//
    @FindBy(how = How.XPATH, using = "//input[@id='id_gender1']")
    public WebElement mrButton;

    @FindBy(how = How.XPATH, using = "//input[@id='id_gender2']")
    public WebElement mrsButton;

    @FindBy(how = How.XPATH, using = "//input[@data-qa='name']")
    public WebElement nameInput;

    @FindBy(how = How.XPATH, using = "//input[@data-qa='name']")
    public WebElement assertNameInput;

    @FindBy(how = How.XPATH, using = "//input[@data-qa='email']")
    public WebElement emailInput;

    @FindBy(how = How.XPATH, using = "//input[@data-qa='email']")
    public WebElement assertEmailInput;

    @FindBy(how = How.XPATH, using = "//input[@data-qa='password']")
    public WebElement passwordInput;

    @FindBy(how = How.XPATH, using = "//select[@id='days']")
    public WebElement dayOfBirth;

//    @FindBy(how = How.XPATH, using = "//div[@id='uniform-days']//span")
//    public WebElement readDayOfBirth;

    @FindBy(how = How.XPATH, using = "//select[@id='months']")
    public WebElement monthOfBirth;

//    @FindBy(how = How.XPATH, using = "//div[@id='uniform-months']//span")
//    public WebElement readMonthOfBirth;

    @FindBy(how = How.XPATH, using = "//select[@id='years']")
    public WebElement yearOfBirth;

//    @FindBy(how = How.XPATH, using = "//div[@id='uniform-years']//span")
//    public WebElement readYearOfBirth;

    @FindBy(how = How.XPATH, using = "//input[@id='newsletter']")
    public WebElement newsletterCheckbox;

    @FindBy(how = How.XPATH, using = "//input[@id='optin']")
    public WebElement specialOffersCheckbox;

    @FindBy(how = How.XPATH, using = "//input[@id='first_name']")
    public WebElement firstNameInput;

    @FindBy(how = How.XPATH, using = "//input[@id='last_name']")
    public WebElement lastNameInput;

    @FindBy(how = How.XPATH, using = "//input[@id='company']")
    public WebElement companyInput;

    @FindBy(how = How.XPATH, using = "//input[@id='address1']")
    public WebElement addressInput;

    @FindBy(how = How.XPATH, using = "//input[@id='address2']")
    public WebElement addressSecondInput;

    @FindBy(how = How.XPATH, using = "//select[@id='country']")
    public WebElement countryDropDown;

//    @FindBy(how = How.XPATH, using = "//div[@id='uniform-id_country']//span")
//    public WebElement readCountryDropdown;

    @FindBy(how = How.XPATH, using = "//input[@id='state']")
    public WebElement stateInput;

    @FindBy(how = How.XPATH, using = "//input[@id='city']")
    public WebElement cityInput;

    @FindBy(how = How.XPATH, using = "//input[@id='zipcode']")
    public WebElement zipcodeCodeInput;

    @FindBy(how = How.XPATH, using = "//input[@id='mobile_number']")
    public WebElement mobilePhoneInput;

    @FindBy(how = How.XPATH, using = "//button[@data-qa='create-account']")
    public WebElement createAccountButton;

    public RegistrationPage() {
        super("signup");
    }
}