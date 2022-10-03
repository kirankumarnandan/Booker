package stepdefination;

import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import utils.TestContext;

import java.io.FileNotFoundException;

public class DeleteBooking {
    private TestContext context;
    public DeleteBooking(TestContext context) {
        this.context = context;
    }

    private static final Logger LOG = LogManager.getLogger(UpdateBooking.class);
    @When ("user makes a request to delete booking with basic auth {string} & {string}")
    public void userMakesARequestToDeleteBookingWithBasicAuth(String username, String password) throws FileNotFoundException {
       try {
           context.response = context.requestSetup ( )
                   .auth ( ).preemptive ( ).basic (username , password)
                   .pathParam ("bookingID" , context.session.get ("bookingID"))
                   .when ( ).delete (context.session.get ("endpoint") + "/{bookingID}");
       }
       catch(Exception e)
       {e.printStackTrace ();}
       finally {
           System.out.println (context.response );
       }
    }
}
