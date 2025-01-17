package main.pages;

import main.buildSettings.TestEnvironment;
import main.pages.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Test_Automation-automationexercise
 *
 * @author lukasz.radzajewski
 **/

public class MainPage extends BasePage<MainPage> {

    //VIEWS & ICONS//
    @FindBy(how = How.XPATH, using = "//i[@class='fa fa-user']")
    public WebElement userIsLoggedIcon;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Logged in")
    public WebElement userIsLoggedInMessage;

    //BUTTONS & INPUTS & DROPDOWN//

    @FindBy(how = How.XPATH, using = "//iframe[@name='googlefcLoaded']")
    public WebElement consentFrame;

    @FindBy(how = How.XPATH, using = "//button[@class='fc-button fc-cta-consent fc-primary-button']")
    public WebElement consentButton;

    @FindBy(how = How.XPATH, using = "//div[@class='shop-menu pull-right']//a[@href='/login']")
    public WebElement signInButton;

    @FindBy(how = How.XPATH, using = "//div[@id='contact-link']")
    public WebElement contactUsButton;

    @FindBy(how = How.XPATH, using = "//input[@class='search_query form-control ac_input']")
    public WebElement searchBoxInput;

    @FindBy(how = How.XPATH, using = "//form[@id='searchbox']//button[@type='submit']")
    public WebElement searchBoxSubmit;

    //SUB-MENU//
    @FindBy(how = How.XPATH, using = "//li//a[@title='Women' and not(img)]")
    public WebElement subMenuWomen;

    @FindBy(how = How.XPATH, using = "(//li//a[@title='Dresses' and not(img)])[2]")
    public WebElement subMenuDresses;

    @FindBy(how = How.XPATH, using = "(//li//a[@title='T-shirts' and not(img)])[2]")
    public WebElement subMenuTshirts;

    @FindBy(how = How.XPATH, using = "//span[@class='cat-name']")
    public WebElement subMenuChosenCategory;

    public MainPage() {
        super(TestEnvironment.HOME_URL);
    }
}