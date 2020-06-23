Feature: Git Scenarios for respository outline

Background: 
Given User is present on github page
When  User clicks on Sign-in button
When  User enters login details

Scenario: Repository creation
Then User navigates to home page
Then User clicks on create repository 
Then User enters repo details
Then Verify repository created


Scenario: Issue Creation
Given User navigates to repository
When User navigates to Issues column
When User click on New issue button
And User enters issue details
Then User clicks on Submit new issue button
Then Verify issue created
Then User creates another issue mentioning previous issue 

Scenario: Comment to an Issue
Given User navigates to repository
When User navigates to Issues column
When User clicks on existing issue
Then User add a comment to an issue


Scenario: Add Emoji in a repository
Given User navigates to repository
When User clicks on Edit button
When User enters emoji in description
Then emoji is added to repository


Scenario: Issue mention in comments link to issue
Given User navigates to repository
When User navigates to Issues column
When User clicks on existing issue
And User add a comment of the previous issue name 
Then User clicks on the link mentioned in comment


Scenario: Delete Repository
When User navigates to repository
When User clicks on settings button
Then User clicks on delete repository under Damage Area
Then User clicks on delete repository button



