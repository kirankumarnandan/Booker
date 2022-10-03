package stepdefination;

import com.api.pojo.BookingDetailsDTO;
import com.api.pojo.BookingID;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import utils.ResponseHandler;
import utils.TestContext;

import java.io.FileNotFoundException;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class ViewBooking {
    private TestContext context;
    private static final Logger LOG = LogManager.getLogger(ViewBooking.class);

    public ViewBooking ( TestContext context) {
        this.context = context;
    }

    @Then ("user should see all the booking IDs")
    public void user_should_see_all_the_booking_i_ds() {
        BookingID[] bookingIDs = ResponseHandler.deserializedResponse(context.response, BookingID[].class);
        assertNotNull("Booking ID not found!!", bookingIDs);
    }

    @Then("user makes a request to view details of a booking ID")
    public void userMakesARequestToViewDetailsOfBookingID() throws FileNotFoundException {
        LOG.info("Session BookingID: "+context.session.get("bookingID"));
        context.response = context.requestSetup().pathParam("bookingID", context.session.get("bookingID"))
                .when().get(context.session.get("endpoint")+"/{bookingID}");
        BookingDetailsDTO bookingDetails = ResponseHandler.deserializedResponse(context.response, BookingDetailsDTO.class);
        assertNotNull("Booking Details not found!!", bookingDetails);
        context.session.put("firstname", bookingDetails.getFirstname());
        context.session.put("lastname", bookingDetails.getLastname());
        System.out.println ("Booking Id"+context.session.get("bookingID") );
    }

    @Then("user makes a request to view all the booking IDs of that user name")
    public void userMakesARequestToViewBookingIDByUserName() throws FileNotFoundException {
        LOG.info("Session firstname: "+context.session.get("firstname"));
        LOG.info("Session lastname: "+context.session.get("lastname"));
        context.response = context.requestSetup()
                .queryParams("firstname", context.session.get("firstname"), "lastname", context.session.get("lastname"))
                .when().get(context.session.get("endpoint").toString());
        BookingID[] bookingIDs = ResponseHandler.deserializedResponse(context.response, BookingID[].class);
        assertNotNull("Booking ID not found!!", bookingIDs);
    }

    }

