Feature:
As a user 
I want to get information about an album
So that I can get an album

Scenario: when user provides an album id, user recoveries album name
Given an id "3mRSojK9iE4F9YtmRjwKyl" that belongs to Un dia normal album from Juanes
When user executes album api
Then user gets the name of album

Scenario: when user provides an album id, user recoveries the artist of album
Given an id "3mRSojK9iE4F9YtmRjwKyl" that belongs to Un dia normal album from Juanes
When user executes album api
Then user gets the name of artist

Scenario: When user provides an album id, user gets number of songs in it
Given an id "3mRSojK9iE4F9YtmRjwKyl" that belongs to Un dia normal album from Juanes
When user executes album track api
Then user gets the number of songs

Scenario: When user provides an album id, user gets number of songs in it
Given an id "3mRSojK9iE4F9YtmRjwKyl" that belongs to Un dia normal album from Juanes
When user executes album track api
Then user gets the names of each song