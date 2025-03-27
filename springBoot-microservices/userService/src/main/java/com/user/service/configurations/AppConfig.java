package com.user.service.configurations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
// import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
// import org.springframework.security.oauth2.client.registration.AuthorizationGrantType;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestTemplate;

import com.user.service.configurations.interceptor.RestTemplateInterceptor;

@Configuration
public class AppConfig {

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @Autowired
    private OAuth2AuthorizedClientRepository authorizedClientRepository;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new RestTemplateInterceptor(authorizedClientManager(clientRegistrationRepository, authorizedClientRepository)));
        restTemplate.setInterceptors(interceptors);
        return new RestTemplate();
    }

    // public FeignClient feignClient() {
    //     return new FeignClient();
    // }

    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(
        ClientRegistrationRepository clientRegistrationRepository,
        OAuth2AuthorizedClientRepository authorizedClientRepository
    ) {
        OAuth2AuthorizedClientProvider provider = OAuth2AuthorizedClientProviderBuilder.builder()
            .clientCredentials()
            .build();
        DefaultOAuth2AuthorizedClientManager clientManager = new DefaultOAuth2AuthorizedClientManager(
            clientRegistrationRepository, authorizedClientRepository
        );
        clientManager.setAuthorizedClientProvider(provider);
        return clientManager;
    }

    // @Bean
    // public ClientRegistrationRepository clientRegistrationRepository() {
    //     return new InMemoryClientRegistrationRepository(
    //         ClientRegistration.withRegistrationId("google")
    //             .clientId("your-client-id")
    //             .clientSecret("your-client-secret")
    //             .scope("profile", "email")
    //             .authorizationUri("https://accounts.google.com/o/oauth2/auth")
    //             .tokenUri("https://oauth2.googleapis.com/token")
    //             .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
    //             .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
    //             .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
    //             .build()
    //     );
    // }

}
