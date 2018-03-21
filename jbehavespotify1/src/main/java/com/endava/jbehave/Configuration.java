package com.endava.jbehave;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRefreshRequest;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

import java.io.IOException;
import java.net.URI;

public class Configuration {

    private static final String CLIENTIID = "6d27a1bcfddc4d8b909a415f78ea1233";
    private static final String CLIENTISECRET = "e3d01bbb6b634760a3a7bc41e337966d";
    private static final URI REDIRECTURI = SpotifyHttpManager.makeUri("http://example.com/callback");
    private static final String SCOPES = "user-modify-playback-state, user-read-playback-state, user-follow-read, user-follow-modify, playlist-modify-public, playlist-modify-private, playlist-read-private, playlist-read-collaborative";

    private static final String URICODE = "AQACRYI1GkVKsiUOFmwBRD3J1v3YeLK8hiUrQDW0QJquSQoiwsVRyWQMozJ083NJ7VSKO1HWorlVO79f3IP6Uks-Y7N3C1xs_NrCVFUq79QJe2bpZkeb_Mq_Y1cZ7nmXeI-4JTDf47s7IhJsGnhSs2iApu12DYOleqht1LprdqH61iEvWwTzSTY1XItrCYpBo-Xqx5LaYJavWpRg0L0OBO6gk_wAcdLN0tI17JGx_teKuBrMLu4Ls7vpUG36eRlk4b8RBkaq1dXRX8OwtL4xc6KFiaWD5I8jvWQ621_rhiaPVk9ZTuw7lIHzEj992g4xOaHV5VaXnWS6JyYXdOHvt_QfJRG2QuFYgyekj2oljhB_9VYWyVVtYkUUOk78e30pR_5GA_s3kP-lPCpz0XPU9TZ0IHn4dKiACtMLPMesNED73zDk1GqOEqe_KTGN6Ky_Hq4";

    private static AuthorizationCodeCredentials authorizationCodeCredentials;
    private static String authCode;
    private static String refreshCode;

    private static SpotifyApi spotifyApi;

    public static SpotifyApi spotifyClientCredentialInstance() {
        if(spotifyApi == null) {
            buildWithStandardConf();
            setTokenCredentials();
        }
        return spotifyApi;
    }

    public static SpotifyApi spotifyAuthorizationInstance() {
        buildWithStandardConfWithRedirect();
        setAuthorizationCredentials();
        return refresh();
    }

    private static void buildWithStandardConf() {
        spotifyApi = new SpotifyApi.Builder()
                .setClientId(CLIENTIID)
                .setClientSecret(CLIENTISECRET)
                .build();
    }

    private static void buildWithStandardConfWithRedirect() {
        spotifyApi = new SpotifyApi.Builder()
                .setClientId(CLIENTIID)
                .setClientSecret(CLIENTISECRET)
                .setRedirectUri(REDIRECTURI)
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

    private static String codeUri() {
        AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
                .scope(SCOPES).show_dialog(true).build();

        URI uri = authorizationCodeUriRequest.execute();
        System.out.println(uri.toString());
        return uri.toString();
    }

    private static AuthorizationCodeRequest codeRequest() {
        codeUri();
        AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(URICODE)
                .build();
        return authorizationCodeRequest;
    }

    private static void setAuthorizationCredentials() {

        try {
            authorizationCodeCredentials = codeRequest().execute();

            authCode = authorizationCodeCredentials.getAccessToken();
            refreshCode = authorizationCodeCredentials.getRefreshToken();

            spotifyApi.setAccessToken(authCode);
            spotifyApi.setRefreshToken(refreshCode);

            System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SpotifyWebApiException e) {
            e.printStackTrace();
        }
    }

    private static SpotifyApi refresh() {
        spotifyApi = new SpotifyApi.Builder()
                .setClientId(CLIENTIID)
                .setClientSecret(CLIENTISECRET)
                .setRefreshToken(refreshCode)
                .build();

        AuthorizationCodeRefreshRequest authorizationCodeRefreshRequest = spotifyApi.authorizationCodeRefresh()
                .build();

        try {
            authorizationCodeCredentials = authorizationCodeRefreshRequest.execute();


            authCode = authorizationCodeCredentials.getAccessToken();
            refreshCode = authorizationCodeCredentials.getRefreshToken();

            spotifyApi.setAccessToken(authCode);
            spotifyApi.setRefreshToken(refreshCode);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SpotifyWebApiException e) {
            e.printStackTrace();
        }
        return spotifyApi;
    }

}
