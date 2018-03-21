Feature:
As a user 
I want to get information about an album
So that I can get an album

  Scenario: when user provides an album id, user recoveries album name
     Given an id that belongs to Un dia normal album from Juanes
     When user executes album api
     Then user gets Un Día Normal (Europe Version) name

  Scenario: when user provides an album id, user recoveries the artist of album
     Given an id that belongs to Un dia normal album from Juanes
     When user executes album api
     Then user gets Juanes name from album

  Scenario: When user provides an album id, user gets number of songs in it
     Given an id that belongs to Un dia normal album from Juanes
     When user executes album track api
     Then user gets number of songs who is eleven

  Scenario: When user provides an album id, user gets number of songs in it
     Given an id that belongs to Un dia normal album from Juanes
     When user executes album track api
     Then user gets names of each song

  Scenario: when user doesn't provide an id, user gets exception
     Given a fake album id
     When user executes album api it will throw exception
     Then user gets album NullPointerException