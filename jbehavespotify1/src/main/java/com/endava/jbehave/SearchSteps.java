package com.endava.jbehave;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.search.simplified.SearchAlbumsRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchArtistsRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import java.io.IOException;

public class SearchSteps {

    private SpotifyApi spotifyApi = Configuration.spotifyClientCredentialInstance();

    private SearchTracksRequest searchTracksRequest;
    private Paging<Track> trackPaging;

    private SearchAlbumsRequest searchAlbumsRequest;
    private Paging<AlbumSimplified> albumSimplifiedPaging;

    private SearchArtistsRequest searchArtistsRequest;
    private Paging<Artist> artistPaging;

    @Step
    public void search_song_by_keyword(String arg0) {
        searchTracksRequest = spotifyApi.searchTracks(arg0).build();
    }

    @Step
    public void execute_search_song_api() {
        try {
            trackPaging = searchTracksRequest.execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SpotifyWebApiException e) {
            e.printStackTrace();
        }
    }

    @Step
    public void returns_a_list_of_songs() {
        Assert.assertTrue(trackPaging.getTotal() > 0);
    }

    @Step
    public void search_album_by_keyword(String arg0) {
        searchAlbumsRequest = spotifyApi.searchAlbums(arg0).build();
    }

    @Step
    public void execute_search_album_api() {
        try {
            albumSimplifiedPaging = searchAlbumsRequest.execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SpotifyWebApiException e) {
            e.printStackTrace();
        }
    }

    @Step
    public void returns_a_list_of_albums() {
        Assert.assertTrue(albumSimplifiedPaging.getTotal() > 0);
    }

    @Step
    public void search_artist_by_keyword(String arg0) {
        searchArtistsRequest = spotifyApi.searchArtists(arg0).build();
    }

    @Step
    public void execute_search_artist_api() {
        try {
            artistPaging = searchArtistsRequest.execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SpotifyWebApiException e) {
            e.printStackTrace();
        }
    }

    @Step
    public void returns_a_list_of_artist() {
       Assert.assertTrue(artistPaging.getTotal() > 0);
    }
}
