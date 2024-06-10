Feature: seller api testing
  @allSellers @regression
  Scenario: get all sellers and verify email is not empty
    Given user hits get all seller api with "api/myaccount/sellers"
    Then verify email is not empty

@sellerEmail @regression
    Scenario: Get a single seller , change email , verify email was changed
      Given user hits get single seller api with "api/myaccount/sellers/"
      Then verify single seller email is not empty
      And hit put api with "api/myaccount/sellers/" and change email to "whatever@gmail.com"
      Then verify email was changed

  @createDelete @regression
    Scenario: create a seller , verify seller was created , delete a seller
    Given user hits create seller api with "api/myaccount/sellers"
    Then verify user id was generated
    And user hits get single seller api with "api/myaccount/sellers/"
    Then verify seller name is not empty
    And verify single seller email is not empty
    Then delete seller api hit with "api/myaccount/sellers/"
