package com.gateway.controllers;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gateway.models.AuthResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(@RegisteredOAuth2AuthorizedClient("okta") 
    OAuth2AuthorizedClient client,@AuthenticationPrincipal OidcUser user, Model model) {
        logger.info("Login request received");
        logger.info("User email Id: {}", user.getEmail());
        List<String> authorities = user.getAuthorities().stream().map(grantedAuthority -> {
            return grantedAuthority.getAuthority();
        }).collect(Collectors.toList());
        AuthResponse response = AuthResponse.builder().userId(user.getEmail())
        .accessToken(client.getAccessToken().getTokenValue())
        .refreshToken(client.getRefreshToken().getTokenValue())
        .expireAt(client.getAccessToken().getExpiresAt().getEpochSecond())
        .authorities(authorities).build();
        return ResponseEntity.ok(response);
    }

}
