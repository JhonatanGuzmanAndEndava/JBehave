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

    private static final String URICODE = "AQBn0g79noO-bWeOV8OorgBW_VazkSqkG8D6Rrh2sM30TBchUmP_6JT1WNcsgJ6DktK3DBt07TnoDJ0y1w76gbJnEkSXh7zRt74JD--giUrC7KegLxDsihAMX4egIXLYe8SwHY6M62E4M7nJo31xkoDYujJ5sOunJg6Am7m97Ek9BxySZ4IagXTFzXnDNKnXWm-KIOd2GoAqLzfwTulyPaQ_K7dZk_kBdmEFFjZ1Nih0gVcc6VOHH9er-g6l4MxI717P7X114jGew56mj2y0As5-r6MzxiMNfsFJzo1-j7e_emqpJwOQbUXMFkWYS4btd0ClXC2Sq0dhwDOuktG9WDzbMM2HUXWjdh2lbXMa2xZ1wH4WAPE3_8COtrfpVsJoVed7jJv6yM7TRFDiWoXJBbodXabaws-eDKVpa8g874qZe6mXXDF-_X8B-L6gL9k94us";

    private static AuthorizationCodeCredentials authorizationCodeCredentials;
    private static String authCode;
    private static String refreshCode;

    private static SpotifyApi spotifyApi;

    public static SpotifyApi spotifyClientCredentialInstance() {
        buildWithStandardConf();
        setTokenCredentials();
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
