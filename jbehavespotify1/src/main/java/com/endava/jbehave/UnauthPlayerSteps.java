package com.endava.jbehave;

import com.google.gson.JsonParser;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.player.StartResumeUsersPlaybackRequest;
import net.thucydides.core.annotations.Step;

import java.io.IOException;

public class UnauthPlayerSteps {

    //private SpotifyApi spotifyApi = Configuration.spotifyClientCredentialInstance();
    private SpotifyApi spotifyApi = Configuration.spotifyAuthorizationInstance();

    private StartResumeUsersPlaybackRequest startResumeUsersPlaybackRequest;

    String msg;

    @Step("Given an uri that belongs to A Dios Le Pido track")
    public void an_uri_that_belongs_to_a_dios_le_pido_track(String uri) {
        startResumeUsersPlaybackRequest = spotifyApi.startResumeUsersPlayback()
                .uris(new JsonParser().parse("[\""+uri+"\"]").getAsJsonArray())
                .build();
    }

    @Step("When user executes Player api")
    public void user_executes_player_api() {
        try {
            msg = startResumeUsersPlaybackRequest.execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SpotifyWebApiException e) {
            e.printStackTrace();
        }
    }

    @Step("Then user listens A Dios Le Pido track")
    public void user_listens_a_dios_le_pido_track() {
        System.out.println("Player: " + msg);
    }
}