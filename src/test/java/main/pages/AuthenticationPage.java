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

public class AuthenticationPage extends BasePage<AuthenticationPage> {

    /**
     * CREATE AN ACCOUNT
     **/
    //VIEW//
    @FindBy(how = How.XPATH, using = "//div[@class='signup-form']")
    public WebElement signUpPanel;

    //BUTTONS & INPUTS //
    @FindBy(how = How.XPATH, using = "//input[@data-qa='signup-name']")
    public WebElement createAnAccountNameInput;

    @FindBy(how = How.XPATH, using = "//input[@data-qa='signup-email']")
    public WebElement createAnAccountEmailInput;

    @FindBy(how = How.XPATH, using = "//button[@data-qa='signup-button']")
    public WebElement createAnAccountButton;


    /**
     * ALREADY REGISTERED
     **/
    //VIEW//
    @FindBy(how = How.XPATH, using = "//div[@class='login-form']")
    public WebElement registeredPanel;

    //MESSAGES//
    @FindBy(how = How.XPATH, using = "//div[@class='login-form']//p")
    public WebElement registeredLoginError;

    //BUTTONS & INPUTS //
    @FindBy(how = How.XPATH, using = "//input[@data-qa='login-email']")
    public WebElement registeredEmailInput;

    @FindBy(how = How.XPATH, using = "//input[@data-qa='login-password']")
    public WebElement registeredPasswordInput;

    @FindBy(how = How.XPATH, using = "//button[@data-qa='login-button']")
    public WebElement registeredSignInButton;

    public AuthenticationPage() {
        super("login");
    }
}
