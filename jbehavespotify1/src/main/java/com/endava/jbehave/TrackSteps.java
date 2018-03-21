package com.endava.jbehave;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.tracks.GetTrackRequest;
import net.thucydides.core.annotations.Step;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TrackSteps {

    private SpotifyApi spotifyApi = Configuration.spotifyClientCredentialInstance();

    private GetTrackRequest getTrackRequest;
    private Track track;

    @Step("Given an id that belongs to A Dios Le Pido track")
    public void an_id_that_belongs_to_a_dios_le_pido_track(String trackId) {
        getTrackRequest = spotifyApi.getTrack(trackId).build();
    }

    @Step("Given a fake track id")
    public void a_fake_track_id(String fakeTrackId) throws Throwable {
        getTrackRequest = spotifyApi.getTrack(fakeTrackId).build();
    }

    @Step("When user executes track api")
    public void user_executes_track_api() {
        try {
            track = getTrackRequest.execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SpotifyWebApiException e) {
            e.printStackTrace();
        }
    }

    @Step("When user executes track api it will throw exception")
    public void user_executes_track_api_it_will_throw_exception() throws Throwable {
        track = getTrackRequest.execute();
    }

    @Step("Then user gets A Dios Le Pido track")
    public void user_gets_a_dios_le_pido_track() {
        assertEquals(track.getName(), "A Dios Le Pido");
    }

    @Step("Then user gets Un Dia Normal (Europe Version) album")
    public void user_gets_un_dia_normal_europe_version_album() {
        assertEquals(track.getAlbum().getName(), "Un Día Normal (Europe Version)");
    }

    @Step("Then user gets Juanes name from track")
    public void user_gets_juanes_name_from_track() {
        assertEquals(track.getArtists()[0].getName(),"Juanes");
    }

    @Step("Then user gets track NullPointerException")
    public void user_gets_track_nullpointerexception() throws Throwable {
        track.getName();
    }

}
