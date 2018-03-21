package com.endava.jbehave;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import com.wrapper.spotify.requests.data.albums.GetAlbumRequest;
import com.wrapper.spotify.requests.data.albums.GetAlbumsTracksRequest;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class AlbumSteps {

    private SpotifyApi spotifyApi = Configuration.spotifyClientCredentialInstance();

    private GetAlbumRequest getAlbumRequest;
    private Album album;

    private GetAlbumsTracksRequest getAlbumsTracksRequest;
    private Paging<TrackSimplified> trackSimplifiedPaging;

    @Step("Given an id that belongs to Un dia normal album from Juanes")
    public void an_id_that_belongs_to_un_dia_normal_album_from_juanes(String albumId) {
        getAlbumRequest = spotifyApi.getAlbum(albumId).build();
        getAlbumsTracksRequest = spotifyApi.getAlbumsTracks(albumId).build();
    }

    @Step("Given a fake album id")
    public void a_fake_album_id(String fakeAlbumId) {
        getAlbumRequest = spotifyApi.getAlbum(fakeAlbumId).build();
    }

    @Step("When user executes album api")
    public void user_executes_album_api() {
        try {
            album = getAlbumRequest.execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SpotifyWebApiException e) {
            e.printStackTrace();
        }
    }

    @Step("When user executes album track api")
    public void user_executes_album_track_api() {
        try {
            trackSimplifiedPaging = getAlbumsTracksRequest.execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SpotifyWebApiException e) {
            e.printStackTrace();
        }
    }

    @Step("When user executes album api it will throw exception")
    public void user_executes_album_api_it_will_throw_exception() throws Throwable {
        album = getAlbumRequest.execute();
    }

    @Step("Then user gets Un Día Normal (Europe Version) name")
    public void user_gets_un_dia_normal_europe_version_name() {
        assertEquals(album.getName(), "Un Día Normal (Europe Version)");
    }

    @Step("Then user gets Juanes name from album")
    public void user_gets_juanes_name_from_album() {
        assertEquals(album.getArtists()[0].getName(), "Juanes");
    }

    @Step("Then user gets number of songs who is eleven")
    public void user_gets_number_of_songs_who_is_eleven() {
        assertEquals((long) trackSimplifiedPaging.getTotal(), 13);
    }

    @Step("Then user gets names of each song")
    public void user_gets_names_of_each_song() {

        String[] nameOfSongs = {"A Dios Le Pido", "Es Por Tí", "Un Día Normal", "La Paga", "La Unica", "Luna", "Día Lejano",
        "Mala Gente", "Fotografía", "Desde Que Despierto Hasta Que Duermo", "La Historia De Juan", "La Noche", "La Paga - Radio Version"};

        String[] trackNames = new String[trackSimplifiedPaging.getItems().length];
        for (int i = 0; i < trackNames.length ; ++i) {
            trackNames[i] = trackSimplifiedPaging.getItems()[i].getName();
        }
        Assert.assertArrayEquals(nameOfSongs, trackNames);
    }

    @Step("Then user gets album NullPointerException")
    public void user_gets_album_nullpointerexception() throws Throwable {
        album.getArtists()[0].getName();
    }

}
