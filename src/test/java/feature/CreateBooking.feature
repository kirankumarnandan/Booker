@createBookingDetails
Feature: Creating hotel booking using Booker api


  Scenario Outline:Creating Booking for following customer
    Given user has access to endpoint "CreateBookingAPI"
    When user creates a booking
      | firstname   | lastname   | totalprice   | depositpaid   | checkin   | checkout   | additionalneeds   |
      | <firstname> | <lastname> | <totalprice> | <depositpaid> | <checkin> | <checkout> | <additionalneeds> |
    Then user should get the response code 200
    Examples:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Kiran      | nanda      |       1200 | true        | 2022-05-05 | 2022-05-15 | Breakfast       |
      | Kumar      | nanda      |       2400 | false       | 2022-06-01 | 2022-07-10 | Lunch          |


