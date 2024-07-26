package tests.WebAPI;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.filter.log.ResponseLoggingFilter;
import main.buildListeners.TestNGListener;
import main.steps.hooks.API_Hooks;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.Assert;
import static net.andreinc.mockneat.unit.address.Cities.cities;
import static net.andreinc.mockneat.unit.user.Emails.emails;
import static net.andreinc.mockneat.unit.user.Names.names;
import static net.andreinc.mockneat.unit.user.Passwords.passwords;
import main.buildSettings.ContextInjection;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * Test_Automation-automationexercise
 *
 * @author lukasz.radzajewski
 **/

@Epic("API Tests")
@Feature("HTTP Statuses")
@Listeners({TestNGListener.class})
public class API_Tests extends API_Hooks {

    RequestSpecification reqSpec;
    ResponseSpecification resSpec;
    @BeforeClass
    public void setUp() {
        reqSpec = new RequestSpecBuilder()
                .setBaseUri("https://automationexercise.com")
                .build();
        resSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
        RequestLoggingFilter requestFilter = new RequestLoggingFilter();
        ResponseLoggingFilter responseFilter = new ResponseLoggingFilter();
        RestAssured.filters(requestFilter, responseFilter);
    }

    @Issue("TAP/API-0001")
    @TmsLink("JIRA-000")
    @Story("HTTP STATUSES")
    @Owner("Lukasz Radzajewski")
    @Severity(SeverityLevel.BLOCKER)
    @Description("[API]/[1] As a user I would like to check availability of automationexercise.com")
    @Test(description = "[API]/[1] I would like to check availability of automationexercise.com",
            priority = 0)
    public void test_1() throws Throwable {
        given(reqSpec).when().get().then().assertThat().statusCode(200);
    }

    @Issue("TAP/API-0002")
    @TmsLink("JIRA-001")
    @Story("PRODUCT LIST")
    @Owner("Lukasz Radzajewski")
    @Severity(SeverityLevel.BLOCKER)
    @Description("[API]/[2] As a user I would like to see all the products that are available")
    @Test(description = "[API]/[2] I would like to see all the products that are available",
            priority = 0)
    public void test_2() {
        Response response = given()
                .header("Content-Type", "application/json")
                .spec(reqSpec)
                .when()
                .get("/api/productsList")
                .then()
                .spec(resSpec)
                .contentType(ContentType.HTML)
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getList("products"));
    }

    @Issue("TAP/API-0003")
    @TmsLink("JIRA-002")
    @Story("PRODUCT SEARCHING")
    @Owner("Lukasz Radzajewski")
    @Severity(SeverityLevel.BLOCKER)
    @Description("[API]/[3] As a user I would like to search for a product")
    @Test(description = "[API]/[3] I would like to search for a product",
            priority = 0)
    public void test_3() {
        String searchTerm = "shirt";

        Response response = given()
                .header("Content-Type", "application/json")
                .spec(reqSpec)
                .param("search_product", searchTerm)
                .when()
                .post("/api/searchProduct")
                .then()
                .spec(resSpec)
                .contentType(ContentType.HTML)
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(!response.jsonPath().getList("products").isEmpty());
        Assert.assertTrue(response.jsonPath().getString("products[0].name").toLowerCase().contains(searchTerm));
    }

    @Issue("TAP/API-0004")
    @TmsLink("JIRA-003")
    @Story("ACCOUNT REGISTRATION")
    @Owner("Lukasz Radzajewski")
    @Severity(SeverityLevel.BLOCKER)
    @Description("[API]/[4] As a user I would like to register new account")
    @Test(description = "[API]/[4] I would like to register new account",
            priority = 1)
    public void test_4() {


        // Create a map for the JSON body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", names().get());
        requestBody.put("email", faker.random().hex(5) + emails().get());
        requestBody.put("password", faker.random().hex(5) + passwords().get());
        requestBody.put("title", "Mr");
        requestBody.put("birth_date", "1985-05-15");
        requestBody.put("firstname", ContextInjection.DEFAULT_CUSTOMER_FIRST_NAME);
        requestBody.put("lastname", ContextInjection.DEFAULT_CUSTOMER_LAST_NAME);
        requestBody.put("company", "Example Corp");
        requestBody.put("address1", "123 Main St");
        requestBody.put("address2", "Apt 4B");
        requestBody.put("country", ContextInjection.DEFAULT_CUSTOMER_COUNTRY);
        requestBody.put("zipcode", "12345");
        requestBody.put("state", "New York");
        requestBody.put("city", "New York");
        requestBody.put("mobile_number", "1234567890");

        // Make a POST request to the registration endpoint
        Response response = RestAssured
                .given()
                .spec(reqSpec)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/api/createAccount");

        // Validate the response
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "User created!");
    }
}