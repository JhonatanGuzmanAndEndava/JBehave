Feature:
As a user 
I want to get information about a song
So that I can get a track

Scenario: user provides a track id, user recoveries track
Given an id "7HJKzIgaNgDgo3p3a2ToR6" that belongs to A Dios Le Pido track
When user executes track api
Then user gets A Dios Le Pido track

Scenario: user provides a track id, user recoveries the name of the album
Given an id "7HJKzIgaNgDgo3p3a2ToR6" that belongs to A Dios Le Pido track
When user executes track api
Then user gets Un Dia Normal album

Scenario: user provides a track id, user recoveries the name of the artist
Given an id "7HJKzIgaNgDgo3p3a2ToR6" that belongs to A Dios Le Pido track
When user executes track api
Then user gets Juanes name from track

Scenario Outline: user provides a track id, user recoveries track name
Given an id "<id>" that belongs to a track
When user executes track api
Then user gets track "<name>"

Examples:
|id|name|
|1lPI0U6YnTXt9Pja9gI1D1|Si Me Puedo Quedar|
|2Iib2MV3ECFJAourgP9dlY|La Isla Bonita|
|5ITdGzpb4twvkpEHp5CGBK|Escarchas|

Scenario Outline: user provides a track id, user recoveries track name
Given an id "<id>" that belongs to a track
When user executes track api
Then user gets artist "<name>"

Examples:
|id|name|
|1lPI0U6YnTXt9Pja9gI1D1|Marco Antonio Solís|
|2Iib2MV3ECFJAourgP9dlY|Madonna|
|5ITdGzpb4twvkpEHp5CGBK|Héctor Lavoe|