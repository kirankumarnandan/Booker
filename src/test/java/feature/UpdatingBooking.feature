@updateBookingDeatils
Feature: To update a booking in restful-booker

  Background: Creating Authentication details
    Given user has access to endpoint "CreateAuth"
    When user creates a auth token with credential "admin" & "password123"
    Then user should get the response code 200

  Scenario Outline:Update Booking details
    Given user has access to endpoint "CreateBookingAPI"
    When user makes a request to view booking IDs
    And user updates the details of a booking
      | firstname   | lastname   | totalprice   | depositpaid   | checkin   | checkout   | additionalneeds   |
      | <firstname> | <lastname> | <totalprice> | <depositpaid> | <checkin> | <checkout> | <additionalneeds> |
    Then user should get the response code 200
    Examples:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | John      | Rambo    |      10000 | true        | 2021-05-15 | 2021-06-11 | Breakfast       |
      | Rocky     | Balboa   |       2006 | false       | 2021-06-01 | 2021-07-10 | Dinner          |