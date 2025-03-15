@CheckoutTest
Feature: Checkout Process

  Background:
    Given the user is logged into the SauceDemo website
    And the user has added a product to the cart

  Scenario: User completes the checkout process
    When the user proceeds to checkout
    And the user enters the following details:
      | First Name | Last Name | Postal Code |
      | John       | Doe       | 12345       |
    And the user continues to the next step
    And the user confirms the order
    Then the order confirmation message should be displayed
