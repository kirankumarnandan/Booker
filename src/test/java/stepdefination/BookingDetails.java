package stepdefination;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.junit.Assert;
import utils.EndPoint;
import utils.TestContext;

import io.restassured.module.jsv.JsonSchemaValidator;
import utils.Utilities;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BookingDetails {
    private TestContext context;
    private static final Logger LOG = LogManager.getLogger(BookingDetails.class);
    public static Response response;
    JsonPath js;
    public static String placeID;

    public BookingDetails(TestContext context) {
        this.context = context;
    }
    @Then ("user should get the response code {int}")
    public void userShpuldGetTheResponseCode(Integer statusCode) {
        assertEquals(Long.valueOf(statusCode), Long.valueOf(context.response.getStatusCode()));
    }


    @Given ( "user makes a request to view booking IDs" )
    public void userMakesARequestToViewBookingIDs ( ) throws FileNotFoundException {
        context.response = context.requestSetup().when().get(context.session.get("endpoint").toString());
        int bookingID = context.response.getBody().jsonPath().getInt("[0].bookingid");
        LOG.info("Booking ID: "+bookingID);
        assertNotNull("Booking ID not found!", bookingID);
        context.session.put("bookingID", bookingID);
    }

    @Given("user has access to endpoint {string}")
    public void userHasAccessToEndpoint(String resource) {
        EndPoint resourceAPI=EndPoint.valueOf(resource);
        System.out.println(resourceAPI.getResource());
        context.session.put("endpoint", resourceAPI.getResource());
    }


    @When ( "user creates auth with username and password" )
    public void userCreatesAuthWithUsernameAndPassword ( ) throws FileNotFoundException {
        JSONObject credentials = new JSONObject();
        credentials.put("username","admin");
        credentials.put("password","password123");
        context.response = context.requestSetup().body(credentials.toString())
                .when().post(context.session.get("endpoint").toString());
        String token = context.response.path("token");
        LOG.info("Auth Token: "+token);
        context.session.put("token", "token="+token);
    }
}
