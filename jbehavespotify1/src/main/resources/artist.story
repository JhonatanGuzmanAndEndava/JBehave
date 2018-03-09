Feature:
As a user 
I want to get information about an artist
So that I can get an artist

  Scenario: when user provides an artist id, user recoveries artist name
     Given an id that belongs to Juanes artist
     When user executes artist api
     Then user gets Juanes name
     
  Scenario: when user doesn't provide an id, user gets exception
     Given a fake artist id
     When user executes artist api it will throw exception
     Then user gets artist NullPointerException