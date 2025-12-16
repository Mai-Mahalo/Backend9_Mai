@Student
Feature: Create a new student via API

    Scenario: Create a new student and validate the response
        When I create a new student and store its id
        And I get the student by the stored id
        Then I validate that information of the created student
        
        
