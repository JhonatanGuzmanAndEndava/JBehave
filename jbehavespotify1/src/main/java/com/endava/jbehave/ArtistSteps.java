package com.endava.jbehave;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.requests.data.artists.GetArtistRequest;
import net.thucydides.core.annotations.Step;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ArtistSteps {

    private SpotifyApi spotifyApi = Configuration.spotifyInstance();

    private GetArtistRequest getArtistRequest;
    private Artist artist;

    @Step("Given an id that belongs to Juanes artist")
    public void an_id_that_belongs_to_juanes_artist(String juanesId) {
        getArtistRequest = spotifyApi.getArtist(juanesId).build();
    }

    @Step("Given a fake artist id")
    public void a_fake_artist_id(String fakeId) {
        getArtistRequest = spotifyApi.getArtist(fakeId).build();
    }

    @Step("When user executes artist api")
    public void user_executes_artist_api() {
        try {
            artist = getArtistRequest.execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SpotifyWebApiException e) {
            e.printStackTrace();
        }
    }

    @Step("When user executes artist api it will throw exception")
    public void user_executes_artist_api_it_will_throw_exception() throws Throwable {
        artist = getArtistRequest.execute();
    }

    @Step("Then user gets Juanes name")
    public void user_gets_juanes_name() {
        assertEquals(artist.getName(), "Juanes");
    }

    @Step("Then user gets NullPointerException")
    public void user_gets_artist_nullpointerexception() throws Throwable {
        artist.getName();
    }

}
