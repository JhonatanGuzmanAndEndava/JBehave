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

    @Step
    public void an_id_that_belongs_to_un_dia_normal_album_from_juanes(String albumId) {
        getAlbumRequest = spotifyApi.getAlbum(albumId).build();
        getAlbumsTracksRequest = spotifyApi.getAlbumsTracks(albumId).build();
    }

    @Step
    public void user_executes_album_api() {
        try {
            album = getAlbumRequest.execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SpotifyWebApiException e) {
            e.printStackTrace();
        }
    }

    @Step
    public void user_executes_album_track_api() {
        try {
            trackSimplifiedPaging = getAlbumsTracksRequest.execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SpotifyWebApiException e) {
            e.printStackTrace();
        }
    }

    @Step
    public void user_gets_un_dia_normal_name() {
        assertEquals(album.getName(), "Un Día Normal");
    }

    @Step
    public void user_gets_juanes_name_from_album() {
        assertEquals(album.getArtists()[0].getName(), "Juanes");
    }

    @Step
    public void user_gets_number_of_songs() {
        assertEquals((long) trackSimplifiedPaging.getTotal(), 12);
    }

    @Step
    public void user_gets_names_of_each_song() {

        String[] nameOfSongs = {"A Dios Le Pido", "Es Por Tí", "Un Día Normal", "La Paga", "La Unica", "Luna", "Día Lejano",
        "Mala Gente", "Fotografía", "Desde Que Despierto Hasta Que Duermo", "La Historia De Juan", "La Noche"};

        String[] trackNames = new String[trackSimplifiedPaging.getItems().length];
        for (int i = 0; i < trackNames.length ; ++i) {
            trackNames[i] = trackSimplifiedPaging.getItems()[i].getName();
        }
        Assert.assertArrayEquals(nameOfSongs, trackNames);
    }

}
