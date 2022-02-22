package com.duongminh.funchat.operation.security.oauth2;

import java.util.Map;

import com.duongminh.funchat.core.enums.AuthProvider;
import com.duongminh.funchat.core.exception.OAuth2AuthenticationProcessingException;
import com.duongminh.funchat.operation.security.oauth2.user.FacebookOAuth2UserInfo;
import com.duongminh.funchat.operation.security.oauth2.user.GithubOAuth2UserInfo;
import com.duongminh.funchat.operation.security.oauth2.user.GoogleOAuth2UserInfo;
import com.duongminh.funchat.operation.security.oauth2.user.OAuth2UserInfo;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(AuthProvider.GOOGLE.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.FACEBOOK.toString())) {
            return new FacebookOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.GITHUB.toString())) {
            return new GithubOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
    
}
