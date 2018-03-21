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

    private static final String URICODE = "AQCowSrnMpvOCaFOQFGWLMPOpUoWbEtWwsP-pVbT8J7fwndDXQ3y5KpDq9I14u-XIhMFlKRn-5hPW3G__B19-vxFviW6koT5cUgy0PRfqCXP4jftyVU-MrXzyeeBanNttE-uB2oMC3W0HXArNV4qQIEJ_478s7CDYJ9Tq37ESIn5h5qtwr6svBDT4khF3z3ue7ej18REuxpmwANWYWbYfnZ3oh7tvIbIEh6a3jtFGTg15W48AkkQyXgXDpS4yszIPVzxJIo2obqeu2W12-n28xwwjcPSVkThOp6PCLu9A7GsQ0vhC2XXTGHvKOvWMe4KPAi1JxDfqzqF08FH4vdDT7VoetA2K8Z4mGT9ftw_2aimDUMtT0ODDwOPXb-mWoPP717rj3qIqIW983zL9Jk2r9gVLnCuFQvUWd4hK7Be7D8jvY1-bw-psMLY_pH7-hl91a8";

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
