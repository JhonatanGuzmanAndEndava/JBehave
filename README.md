# BDD for Spotify API with Serenity

This is BDD Challenge for Endava Internship. We tested scenarios for Spotify Wrapper from https://github.com/thelinmichael/spotify-web-api-java

URI Token auth can be request in https://accounts.spotify.com:443/authorize?client_id=6d27a1bcfddc4d8b909a415f78ea1233&response_type=code&redirect_uri=http%3A%2F%2Fexample.com%2Fcallback&scope=user-modify-playback-state%2C+user-read-playback-state%2C+user-follow-read%2C+user-follow-modify%2C+playlist-modify-public%2C+playlist-modify-private%2C+playlist-read-private%2C+playlist-read-collaborative&show_dialog=true

Just Copy and paste the token in URICODE from Configuration.java class

For Run scenarios just run EjecutionTest.java file or directly for verify in IntelliJ using Maven (mvn verify command). You can view the Serenity report in target/site/serenity/index.html

**Note:** Just one test is failing because spotify api needs a delay for play song test and confirm the boolean flag
