Feature:
As a user
I want to search by keyword
So that I can view a list of elements whose match with the search

Scenario: when user sends a keyword for search song, user recovers a list of elements with search
Given a song keyword "Hotel California"
When user executes search song api
Then system returns a list of songs