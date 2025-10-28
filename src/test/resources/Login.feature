Feature: Login functionality

  Scenario: Successfully login with valid credentials
    Given user is on the login page
    When user enters valid username and valid password
    And user clicks on login button
    Then user should be redirect to the dashboard

  Scenario: Unsuccessfully login with invalid credentials
    Given user is on the login page
    When user enters invalid username and invalid password
    And user click on login button
    Then an error message is displayed
