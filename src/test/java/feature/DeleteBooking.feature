@deleteBooking
Feature: To delete a booking in restful-booker

  Background: Creating Authentication details
    Given user has access to endpoint "CreateAuth"
    When user creates auth with username and password
    Then user should get the response code 200



  Scenario Outline:Delete Booking Created
    Given user has access to endpoint "CreateBookingAPI"
    When user creates a booking
      | firstname   | lastname   | totalprice   | depositpaid   | checkin   | checkout   | additionalneeds   |
      | <firstname> | <lastname> | <totalprice> | <depositpaid> | <checkin> | <checkout> | <additionalneeds> |
    Then user should get the response code 200
    And user makes a request to view details of a booking ID
    And user should get the response code 200
    And user makes a request to delete booking with basic auth "admin" & "password123"
    And user should get the response code 201
    Examples:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Manohar      | V      |       100 | true        | 2022-02-01 | 2022-02-02 | Breakfast       |