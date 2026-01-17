package com.aleks4ay.hotelprovider.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "keycloak")
public record KeycloakProperties(String realm, String secret, String serverUrl, String clientId) {

}
