@ViewBooking
Feature:View All Booking details


  Scenario: View Booking Ids created
    Given user has access to endpoint "CreateBookingAPI"
    When user makes a request to view booking IDs
    Then user should get the response code 200
    And user should see all the booking IDs
