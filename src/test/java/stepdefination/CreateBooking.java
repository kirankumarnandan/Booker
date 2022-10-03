package stepdefination;

import com.api.pojo.BookingDTO;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import utils.ResponseHandler;
import utils.TestContext;
import static org.junit.Assert.*;
import io.restassured.*;


import java.io.FileNotFoundException;
import java.util.Map;

public class CreateBooking {
    private TestContext context;
    private static final Logger LOG = LogManager.getLogger(CreateBooking.class);
    public CreateBooking(TestContext context) {
        this.context = context;
    }


    @When ("user creates a booking")
    public void userCreatesABooking( DataTable dataTable) throws FileNotFoundException {
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

//    context.response = context.requestSetup().body(bookingBody.toString())
//            .when().post(context.session.get("endpoint").toString());
//        System.out.println (context.response );

        context.response = context.requestSetup().body(bookingBody.toString())
                .when().post(context.session.get("endpoint").toString());
        System.out.println (context.response );


    BookingDTO bookingDTO = ResponseHandler.deserializedResponse(context.response, BookingDTO.class);
    assertNotNull("Booking not created", bookingDTO);
    LOG.info("Newly created booking ID: "+bookingDTO.getBookingid());
    context.session.put("bookingID", bookingDTO.getBookingid());
 //      validateBookingData(new JSONObject(bookingData), bookingDTO);
   //     validateBookingData((JSONObject) bookingData , bookingDTO);
        String placeID = bookingDTO.getBookingid() ;
        System.out.println("Place ID: "+placeID);
        String firstname =  bookingDTO.getBooking().getFirstname() ;
        System.out.println("firstname"+firstname);
        String lastname =  bookingDTO.getBooking().getLastname() ;
        System.out.println("lastname: "+lastname);
        String totalprice =   bookingDTO.getBooking().getTotalprice();
        System.out.println("totalprice : "+totalprice);
        String depositpaid =   bookingDTO.getBooking().getDepositpaid();
        System.out.println("depositpaid ID: "+depositpaid);
        String additionalneeds =   bookingDTO.getBooking().getAdditionalneeds() ;
        System.out.println("additionalneeds: "+additionalneeds);
        assertEquals("First Name did not match", bookingData.get("firstname"), bookingDTO.getBooking().getFirstname());
        assertEquals("Last Name did not match", bookingData.get("lastname"), bookingDTO.getBooking().getLastname());
        assertEquals("Total Price did not match", bookingData.get("totalprice"), bookingDTO.getBooking().getTotalprice());
        assertEquals("Deposit Paid did not match", bookingData.get("depositpaid"), bookingDTO.getBooking().getDepositpaid());
        assertEquals("Additional Needs did not match", bookingData.get("additionalneeds"), bookingDTO.getBooking().getAdditionalneeds());
        assertEquals("Check in Date did not match", bookingData.get("checkin"), bookingDTO.getBooking().getBookingdates().getCheckin());
        assertEquals("Check out Date did not match", bookingData.get("checkout"), bookingDTO.getBooking().getBookingdates().getCheckout());


    }

//    private void validateBookingData(JSONObject bookingData, BookingDTO bookingDTO) {
//        LOG.info(bookingData);
//        assertNotNull("Booking ID missing", bookingDTO.getBookingid());
//        assertEquals("First Name did not match", bookingData.get("firstname"), bookingDTO.getBooking().getFirstname());
//        assertEquals("Last Name did not match", bookingData.get("lastname"), bookingDTO.getBooking().getLastname());
//        assertEquals("Total Price did not match", bookingData.get("totalprice"), bookingDTO.getBooking().getTotalprice());
//        assertEquals("Deposit Paid did not match", bookingData.get("depositpaid"), bookingDTO.getBooking().getDepositpaid());
//        assertEquals("Additional Needs did not match", bookingData.get("additionalneeds"), bookingDTO.getBooking().getAdditionalneeds());
//        assertEquals("Check in Date did not match", bookingData.get("checkin"), bookingDTO.getBooking().getBookingdates().getCheckin());
//        assertEquals("Check out Date did not match", bookingData.get("checkout"), bookingDTO.getBooking().getBookingdates().getCheckout());
//    }


}
