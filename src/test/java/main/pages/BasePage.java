package main.pages;

import main.DriverFactory;
import main.buildSettings.TestCommons;
import main.buildSettings.TestEnvironment;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

/**
 * Test_Automation-automationexercise
 *
 * @author lukasz.radzajewski
 **/

public abstract class BasePage<T extends LoadableComponent<T>> extends LoadableComponent<T> {

    private final String pageURL;

    protected BasePage(String pageURL) {
        this.pageURL = pageURL;
        //PageFactory.initElements(new AjaxElementLocatorFactory(DriverFactory.getDriver(), TIMEOUT), this);
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

    @Override
    public void load() {
        if (pageURL.startsWith("http://") || pageURL.startsWith("https://")) {
            DriverFactory.getDriver().get(pageURL);
        } else {
            DriverFactory.getDriver().get(TestEnvironment.HOME_URL + pageURL);
        }
    }

    @Override
    public void isLoaded() throws Error {
        if (!DriverFactory.getDriver().getCurrentUrl().contains(pageURL) && TestCommons.isPageReady()) {
            throw new Error("automationexercise website isn't correctly loaded!");
        }
    }
}