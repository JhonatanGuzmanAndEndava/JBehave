package com.endava.jbehave;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

import java.io.IOException;

public class Configuration {

    private static final String CLIENTIID = "6d27a1bcfddc4d8b909a415f78ea1233";
    private static final String CLIENTISECRET = "e3d01bbb6b634760a3a7bc41e337966d";
    private static SpotifyApi spotifyApi;

    public static SpotifyApi spotifyInstance() {
        if(spotifyApi == null) {
            buildWithStandardConf();
            setTokenCredentials();
        }
        return spotifyApi;
    }

    private static void buildWithStandardConf() {
        spotifyApi = new SpotifyApi.Builder()
                .setClientId(CLIENTIID)
                .setClientSecret(CLIENTISECRET)
                .build();
    }

    private static void setTokenCredentials() {
        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
                .build();

        ClientCredentials clientCredentials;

        {
            try {
                clientCredentials = clientCredentialsRequest.execute();
                spotifyApi.setAccessToken(clientCredentials.getAccessToken());
                System.out.println("Expires in: " + clientCredentials.getExpiresIn());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SpotifyWebApiException e) {
                e.printStackTrace();
            }
        }
    }

}
