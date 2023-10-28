package org.helmo.HolyD.models.requests;

import javax.validation.constraints.Size;

public class UserSignWithProvider {

    @Size(max = 250, message = "Wrong token connection size max=150")
    private String tokenConnection;

    private String tokenProvider;

    public String getTokenConnection() {
        return tokenConnection;
    }

    public void setTokenConnection(String tokenConnection) {
        this.tokenConnection = tokenConnection;
    }

    public String getTokenProvider() {
        return tokenProvider;
    }

    public void setTokenProvider(String tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public String toString() {
        return "UserSignInWithProvider{" +
                "tokenConnection='" + tokenConnection + '\'' +
                ", tokenProvider='" + tokenProvider + '\'' +
                '}';
    }
}
