@OneClass

Feature: Get one class from API

    Background:
        Given I create a GET request
        And I provide the classId 1232 as path param
        
    Scenario: Get one class and validate the response
        When I send the GET request to GetOneClass endpoint
        Then I validate the status code is 200
        And I validate that the Id in response body is 1232
        And I validate that the class term is "Fall 2024"
    

