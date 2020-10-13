package com.movieapp.notificationservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@EnableOAuth2Client
@Configuration
public class OAuthConfig {




@Bean
protected OAuth2ProtectedResourceDetails resource() {

    ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();

    resource.setAccessTokenUri("http://localhost:8081/oauth/token");
    resource.setClientId("movieapp");
    resource.setClientSecret("secret");
    resource.setUsername("mahe123");
    resource.setPassword("Abc!1234");

    return resource;
}

@Bean
@LoadBalanced
 public OAuth2RestTemplate restTemplate() {
    return new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext(new DefaultAccessTokenRequest()));
    }
}