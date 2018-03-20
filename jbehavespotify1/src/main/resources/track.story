Feature:
As a user 
I want to listen a song
So that I can get a track and play it

  Scenario: user provides a track id, user recoveries track
     Given an id that belongs to A Dios Le Pido track
     When user executes track api
     Then user gets A Dios Le Pido track

  Scenario: user provides a track id, user recoveries the name of the album
     Given an id that belongs to A Dios Le Pido track
     When user executes track api
     Then user gets Un Dia Normal (Europe Version) album

  Scenario: user provides a track id, user recoveries the name of the artist
     Given an id that belongs to A Dios Le Pido track
     When user executes track api
     Then user gets Juanes name from track

  Scenario: user provides a valid id track, user can listen the track
     Given an uri that belongs to A Dios Le Pido track
     When user executes Player api
     Then user listens A Dios Le Pido track

  Scenario: when user doesn't provide a valid track id, user gets exception
     Given a fake track id
     When user executes track api it will throw exception
     Then user gets track NullPointerException