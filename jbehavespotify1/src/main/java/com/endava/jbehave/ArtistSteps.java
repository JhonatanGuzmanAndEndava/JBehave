package com.endava.jbehave;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.exceptions.detailed.BadRequestException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.requests.data.artists.GetArtistRequest;
import net.thucydides.core.annotations.Step;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ArtistSteps {

    private SpotifyApi spotifyApi = Configuration.spotifyClientCredentialInstance();

    private GetArtistRequest getArtistRequest;
    private Artist artist;

    @Step
    public void an_id_that_belongs_to_artist(String id) {
        getArtistRequest = spotifyApi.getArtist(id).build();
    }

    @Step
    public void user_executes_artist_api() {
        try {
            artist = getArtistRequest.execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SpotifyWebApiException e) {
            e.printStackTrace();
        }
    }

    @Step
    public void user_gets_juanes_name() {
        assertEquals(artist.getName(), "Juanes");
    }

    @Step
    public void user_gets_artist_name(String artistName) {
        assertEquals(artist.getName(), artistName);
    }

}
