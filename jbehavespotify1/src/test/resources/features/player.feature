Feature:
As a user
I want to listen a song
So that I can get a track and play it

Scenario: user provides a valid id track, user can listen the track
Given an uri "spotify:track:7HJKzIgaNgDgo3p3a2ToR6" that belongs to A Dios Le Pido track
When user executes Player api
Then user listens A Dios Le Pido track