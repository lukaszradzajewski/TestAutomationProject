#/**
#* Test_Automation-automationexercise
#*
#* @author lukasz.radzajewski
#**/

@authentication
Feature: As a user I would like to log in automationexercise.com
#--------------------------------------------------------------------------------#
# [UserStory] https://tracker.FAKE.com/jira/browse/AUTOMATION_PRACTICE-111       #
#--------------------------------------------------------------------------------#

  Background: Navigate to Login page
    Given I open home page
    And I can see automationexercise.com website
    And I click on Sign in button

#--------------------------------------------------------------------------------#
# [ZEPHYR] https://tracker.FAKE.com/jira/browse/AUTOMATION_PRACTICE-0001
  @smoke @critical @regression
  Scenario Outline:[US-111]/[1] As a user I can log into automationexercise.com using registered email "<email>" & password "<password>"
    Given I can see login form
    When I enter login "<email>"
    And I enter password "<password>"
    And I click on Login button
    Then I can see logged in icon

    Examples: SCENARIO OUTLINE DATA
      | email                    | password |
      | lucastest1337@gmail.com  | 12345    |

#--------------------------------------------------------------------------------#
# [ZEPHYR] https://tracker.FAKE.com/jira/browse/AUTOMATION_PRACTICE-0002
# [ZEPHYR] https://tracker.FAKE.com/jira/browse/AUTOMATION_PRACTICE-0003
# [ZEPHYR] https://tracker.FAKE.com/jira/browse/AUTOMATION_PRACTICE-0004
  @non-smoke @normal @regression
  Scenario Outline:[US-111]/[2] As a user I can't log into automationexercise.com using email "<email>" & password "<password>"
    Given I can see login form
    When I enter login "<email>"
    And I enter password "<password>"
    And I click on Login button
    Then I can see warning message with include "<warning message>"

    Examples: SCENARIO OUTLINE DATA
      | email                    | password | warning message                       |
      | wrongmail@example.com    | 12345    | Your email or password is incorrect!  |
      | lucastest1337@gmail.com  | wrongpass| Your email or password is incorrect!  |