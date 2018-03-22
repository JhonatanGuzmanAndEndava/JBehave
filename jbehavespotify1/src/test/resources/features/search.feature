Feature:
As a user
I want to search by keyword
So that I can view a list of elements whose match with the search

Scenario Outline: when user sends a keyword for search song, user recovers a list of songs
Given a song keyword "<song>"
When user executes search song api
Then system returns a list of songs

Examples:
|song|
|Hotel California|
|Losing my religion|
|Lemon tree|
|Despacito|
|What do you mean|

Scenario Outline: when user sends a keyword for search album, user recovers a list of album
Given an album keyword "<album>"
When user executes search album api
Then system returns a list of album

Examples:
|album|
|Trozos de mi alma|
|Fijacion Oral|
|Los extraterrestres|
|The Wall|
|3.0|

Scenario Outline: when user sends a keyword for search artist, user recovers a list of artist
Given an artist keyword "<artist>"
When user executes search artist api
Then system returns a list of artist

Examples:
|artist|
|Bruno Mars|
|U2|
|Daddy Yankee|