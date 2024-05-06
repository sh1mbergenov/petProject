Feature: test log in functionality

  @loginDemo1
  Scenario: check log in is successful with valid credentials
    Given user goes to "https://www.saucedemo.com/"
    When user provides a valid username
    And user provides a valid password
    And user clicks on login button
    Then verify user successfully logged in

    @loginDemo2
    Scenario:  check log in with invalid credentials
      Given user goes to "https://www.saucedemo.com/"
      When user provides an invalid username
      And user provides an invalid password
      And user clicks on login button
      Then verify user sees an error message


