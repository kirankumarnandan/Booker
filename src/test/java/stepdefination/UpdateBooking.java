package stepdefination;

import com.api.pojo.BookingDetailsDTO;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import utils.ResponseHandler;
import utils.TestContext;

import java.io.FileNotFoundException;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class UpdateBooking {
    private TestContext context;
    private static final Logger LOG = LogManager.getLogger(UpdateBooking.class);

    public UpdateBooking(TestContext context) {
        this.context = context;
    }

    @When ("user creates a auth token with credential {string} & {string}")
    public void userCreatesAAuthTokenWithCredential(String username, String password) throws FileNotFoundException {
        JSONObject credentials = new JSONObject();
        credentials.put("username", username);
        credentials.put("password", password);
        context.response = context.requestSetup().body(credentials.toString())
                .when().post(context.session.get("endpoint").toString());
        String token = context.response.path("token");
        LOG.info("Auth Token: "+token);
        context.session.put("token", "token="+token);
    }


    @And ( "user updates the details of a booking" )
    public void userUpdatesABooking( DataTable dataTable) throws FileNotFoundException {
        Map<String,String> bookingData = dataTable.asMaps().get(0);
        JSONObject bookingBody = new JSONObject();
        bookingBody.put("firstname", bookingData.get("firstname"));
        bookingBody.put("lastname", bookingData.get("lastname"));
        bookingBody.put("totalprice", Integer.valueOf(bookingData.get("totalprice")));
        bookingBody.put("depositpaid", Boolean.valueOf(bookingData.get("depositpaid")));
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", (bookingData.get("checkin")));
        bookingDates.put("checkout", (bookingData.get("checkout")));
        bookingBody.put("bookingdates", bookingDates);
        bookingBody.put("additionalneeds", bookingData.get("additionalneeds"));

        context.response = context.requestSetup()
                .header("Cookie", context.session.get("token").toString())
                .pathParam("bookingID", context.session.get("bookingID"))
                .body(bookingBody.toString())
                .when().put(context.session.get("endpoint")+"/{bookingID}");

        BookingDetailsDTO bookingDetailsDTO = ResponseHandler.deserializedResponse(context.response, BookingDetailsDTO.class);
        assertNotNull("Booking not created", bookingDetailsDTO);
    }
}
