package com.endava.jbehave;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.tracks.GetTrackRequest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TrackSteps {

    private SpotifyApi spotifyApi = Configuration.spotifyClientCredentialInstance();

    private GetTrackRequest getTrackRequest;
    private Track track;

    @Step
    public void an_id_that_belongs_to_a_track(String trackId) {
        getTrackRequest = spotifyApi.getTrack(trackId).build();
    }

    @Step
    public void user_executes_track_api() {
        try {
            track = getTrackRequest.execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SpotifyWebApiException e) {
            e.printStackTrace();
        }
    }

    @Step
    public void user_gets_a_dios_le_pido_track() {
        assertEquals(track.getName(), "A Dios Le Pido");
    }

    @Step
    public void user_gets_un_dia_normal_album() {
        assertEquals(track.getAlbum().getName(), "Un Día Normal");
    }

    @Step
    public void user_gets_juanes_name_from_track() {
        assertEquals(track.getArtists()[0].getName(),"Juanes");
    }

    @Step
    public void user_gets_name_from_track(String name) {
        assertEquals(track.getName(),name);
    }

    @Step
    public void user_gets_artist_name_from_track(String name) {
        assertEquals(track.getArtists()[0].getName(),name);
    }
}
