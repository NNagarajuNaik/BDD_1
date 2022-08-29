@Hotels
Feature: 
  I want to use this template for Hotels feature

  @filters
  Scenario: I want to check filter features of hotels
    Given I open browser with url as "https://www.cleartrip.com/"
    Then I validate page of url
    And I verify modify search options
    And I verify number of filters options
    And I verify number of options for priority
    And I verify filter by hotels options
    And I verify total price options
    And I verify Hotel class options
    And I verify Special offers options
    And I verify TripAdviser rating options
    And I verify Amenties options
    And I verify Property type options
    When I validate modify search
    And I validate number of options for priority
    And I validate filter by hotels
    And I validate total price
    And I validate Hotel class
    And I validate Special offers
    And I validate TripAdviser rating
    And I validate Amenties
    And I validate Property type
    Then I verify clearAll button
    And I verify populer hotels
    And I verify populer hotel chains
    And I verify other tools
    When I  validate click view detials
    Then I verify select room option
    And I close Browser
