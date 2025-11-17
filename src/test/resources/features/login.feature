Feature: Login

  Scenario: Login fails with empty username and empty password
    Given I am on the login page
    When I enter "username" as username
    And I enter "password" as password
    And I clear the username field
    And I clear the password field
    And I click the login button
    Then I should see an error message containing "Username is required"

  Scenario: Login fails with empty password
    Given I am on the login page
    When I enter "username" as username
    And I enter "password" as password
    And I clear the password field
    And I click the login button
    Then I should see an error message containing "Password is required"

  Scenario Outline: Login succeeds with valid credentials
    Given I am on the login page
    When I enter "<username>" as username
    And I enter "<password>" as password
    And I click the login button
    Then I should see the dashboard title "Swag Labs"

    Examples:
      | username                | password      |
      | standard_user           | secret_sauce  |
      | locked_out_user         | secret_sauce  |
      | problem_user            | secret_sauce  |
      | performance_glitch_user | secret_sauce  |
      | error_user              | secret_sauce  |
      | visual_user             | secret_sauce  |