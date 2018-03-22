Feature:
As a user 
I want to get information about an artist
So that I can get an artist

Scenario: when user provides an artist id, user recoveries artist name
Given an id "0UWZUmn7sybxMCqrw9tGa7" that belongs to Juanes artist
When user executes artist api
Then user gets Juanes name

Scenario Outline: when user provides an artist id, user recoveries artist name
Given an id "<id>" that belongs to an artist
When user executes artist api
Then user gets "<name>" name

Examples:
|id|name|
|0UWZUmn7sybxMCqrw9tGa7|Juanes|
|3tJnB0s6c3oXPq1SCCavnd|Marco Antonio Solís|
|3SEztK9fNxg81qZ8qETGNT|Yeison Jimenez|