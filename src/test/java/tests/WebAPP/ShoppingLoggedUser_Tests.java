package tests.WebAPP;

import main.buildListeners.TestNGListener;
import main.buildSettings.ContextInjection;
import main.steps.MainPageSteps;
import main.steps.RegistrationPageSteps;
import main.steps.ShoppingLoggedUserSteps;
import main.steps.hooks.WEB_Hooks;
import io.cucumber.datatable.DataTable;
import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Test_Automation-automationexercise
 *
 * @author lukasz.radzajewski
 **/

@Epic("Web App Tests")
@Feature("SHOPPING TESTS")
@Listeners({TestNGListener.class})
public class ShoppingLoggedUser_Tests extends WEB_Hooks {

    @Issue("TAP-0039")
    @TmsLink("STORY-666")
    @Story("POSITIVE FLOW")
    @Owner("Lukasz Radzajewski")
    @Severity(SeverityLevel.CRITICAL)
    @Description("[US-666]/[1] As a user I would like to buy new \"Faded Short Sleeve T-shirts\"")
    @Test(description = "[US-666]/[1] I would like to buy new \"Faded Short Sleeve T-shirts\"",
            priority = 0)
    public void test_1() throws Throwable {
        //ARRANGE//
        final MainPageSteps mainPageSteps = new MainPageSteps();
        final RegistrationPageSteps registrationPageSteps = new RegistrationPageSteps(new ContextInjection());
        final ShoppingLoggedUserSteps shoppingLoggedUserSteps = new ShoppingLoggedUserSteps(new ContextInjection());


        List<List<String>> orderDetails = Arrays.asList
                (
                        Arrays.asList("Quantity", "Size", "Colour"),
                        Arrays.asList("5", "M", "Blue")
                );
        DataTable orderDetailsDataTable = DataTable.create(orderDetails);

        List<List<String>> paymentDetails = Arrays.asList
                (
                        Collections.singletonList("Payment Method"),
                        Collections.singletonList("Pay by check")
                );
        DataTable paymentDetailsDataTable = DataTable.create(paymentDetails);

        //ACT//
        mainPageSteps.iOpenHomePage();
        mainPageSteps.iCanSeeautomationexerciseComWebsite();
        mainPageSteps.iAmLoggedAsCustomerUsingPassword("thor.odinson@example.com", "12345");
        mainPageSteps.iAmLoggedIn();
        shoppingLoggedUserSteps.iClickOnButtonFromSubMenu("Women");
        shoppingLoggedUserSteps.iClickOnFollowingProduct("Faded Short Sleeve T-shirts");
        shoppingLoggedUserSteps.iChooseFollowingDetailsOfMyOrder(orderDetailsDataTable);
        shoppingLoggedUserSteps.iClickOnAddToCartButton();
        shoppingLoggedUserSteps.iCanSeeModalWhereIAmAbleToSeeDetailedDataAboutMyPurchase();
        shoppingLoggedUserSteps.iClickOnProceedToCheckoutButtonFromModal();
        shoppingLoggedUserSteps.iCanSeeShoppingCartFormWithValidInformation("Your shopping cart");
        shoppingLoggedUserSteps.iClickOnProceedToCheckoutButtonFromShoppingCart();
        shoppingLoggedUserSteps.iCanSeeShoppingCartFormWithValidInformation("Addresses");
        shoppingLoggedUserSteps.iWriteCommentAboutMyOrder();
        shoppingLoggedUserSteps.iClickOnProceedToCheckoutButtonFromShoppingCart();
        shoppingLoggedUserSteps.iCanSeeShoppingCartFormWithValidInformation("Shipping");
        shoppingLoggedUserSteps.iChooseShippingOption("My carrier");
        shoppingLoggedUserSteps.iClickOnTermsOfServiceCheckbox();
        shoppingLoggedUserSteps.iClickOnProceedToCheckoutButtonFromShoppingCart();
        shoppingLoggedUserSteps.iCanSeeShoppingCartFormWithValidInformation("Your payment method");
        shoppingLoggedUserSteps.iChoosePaymentMethod(paymentDetailsDataTable);
        shoppingLoggedUserSteps.iClickOnIConfirmMyOrderButton();

        //ASSERT
        shoppingLoggedUserSteps.iCanSeeShoppingCartFormWithValidInformation("Order confirmation");
    }

    @Issue("TAP-0040")
    @TmsLink("STORY-666")
    @Story("POSITIVE FLOW")
    @Owner("Lukasz Radzajewski")
    @Severity(SeverityLevel.CRITICAL)
    @Description("[US-666]/[2] As a user I would like to buy new \"Blouse\"")
    @Test(description = "[US-666]/[2] I would like to buy new \"Blouse\"",
            priority = 0)
    public void test_2() throws Throwable {
        //ARRANGE//
        final MainPageSteps mainPageSteps = new MainPageSteps();
        final RegistrationPageSteps registrationPageSteps = new RegistrationPageSteps(new ContextInjection());
        final ShoppingLoggedUserSteps shoppingLoggedUserSteps = new ShoppingLoggedUserSteps(new ContextInjection());


        List<List<String>> orderDetails = Arrays.asList
                (
                        Arrays.asList("Quantity", "Size", "Colour"),
                        Arrays.asList("2", "S", "Black")
                );
        DataTable orderDetailsDataTable = DataTable.create(orderDetails);

        List<List<String>> paymentDetails = Arrays.asList
                (
                        Collections.singletonList("Payment Method"),
                        Collections.singletonList("Pay by bank wire")
                );
        DataTable paymentDetailsDataTable = DataTable.create(paymentDetails);

        //ACT//
        mainPageSteps.iOpenHomePage();
        mainPageSteps.iCanSeeautomationexerciseComWebsite();
        mainPageSteps.iAmLoggedAsCustomerUsingPassword("thor.odinson@example.com", "12345");
        mainPageSteps.iAmLoggedIn();
        shoppingLoggedUserSteps.iClickOnButtonFromSubMenu("Women");
        shoppingLoggedUserSteps.iClickOnFollowingProduct("Blouse");
        shoppingLoggedUserSteps.iChooseFollowingDetailsOfMyOrder(orderDetailsDataTable);
        shoppingLoggedUserSteps.iClickOnAddToCartButton();
        shoppingLoggedUserSteps.iCanSeeModalWhereIAmAbleToSeeDetailedDataAboutMyPurchase();
        shoppingLoggedUserSteps.iClickOnProceedToCheckoutButtonFromModal();
        shoppingLoggedUserSteps.iCanSeeShoppingCartFormWithValidInformation("Your shopping cart");
        shoppingLoggedUserSteps.iClickOnProceedToCheckoutButtonFromShoppingCart();
        shoppingLoggedUserSteps.iCanSeeShoppingCartFormWithValidInformation("Addresses");
        shoppingLoggedUserSteps.iWriteCommentAboutMyOrder();
        shoppingLoggedUserSteps.iClickOnProceedToCheckoutButtonFromShoppingCart();
        shoppingLoggedUserSteps.iCanSeeShoppingCartFormWithValidInformation("Shipping");
        shoppingLoggedUserSteps.iChooseShippingOption("My carrier");
        shoppingLoggedUserSteps.iClickOnTermsOfServiceCheckbox();
        shoppingLoggedUserSteps.iClickOnProceedToCheckoutButtonFromShoppingCart();
        shoppingLoggedUserSteps.iCanSeeShoppingCartFormWithValidInformation("Your payment method");
        shoppingLoggedUserSteps.iChoosePaymentMethod(paymentDetailsDataTable);
        shoppingLoggedUserSteps.iClickOnIConfirmMyOrderButton();

        //ASSERT
        shoppingLoggedUserSteps.iCanSeeShoppingCartFormWithValidInformation("Order confirmation");
    }
}