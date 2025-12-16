@Token
Feature: Generate API Token

    Background:
        Given I create a token request
        And I provide the request body to generate token
        And I set the headers information for token requet
        
    Scenario: Generate token and validate the response
        When I send the POST request to GenerateToken endpoint
        Then I validate the status code is equal to 200
        And I validate that the body contains "accessToken"
        And I validate that the value of "success" is true
        
        