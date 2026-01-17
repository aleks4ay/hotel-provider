package com.aleks4ay.hotelprovider.service;

import com.aleks4ay.hotelprovider.config.KeycloakProperties;
import com.aleks4ay.hotelprovider.model.in.register.CreateUserDto;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final Keycloak keycloak;
    private final KeycloakProperties properties;

    public String create(CreateUserDto request) {
        log.info("Creating new user: [{}]", request);
        UserRepresentation user = new UserRepresentation();
        user.setUsername(request.username());
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEnabled(request.enabled());
        user.setEmail(request.email());

        Response response = keycloak
                .realm(properties.realm())
                .users()
                .create(user);

        if (response.getStatus() != 201) {
            throw new IllegalStateException("User creation failed: " + response.getStatus());
        }

        var userId = CreatedResponseUtil.getCreatedId(response);
        setPassword(userId, request.password());
        setRole(userId, "hotel_data_reader");

        return userId;
    }

    public void setPassword(String userId, String password) {

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        credential.setTemporary(false);

        keycloak.realm(properties.realm())
                .users()
                .get(userId)
                .resetPassword(credential);
    }

    public void setRole(String userId, String roleName) {

        RoleRepresentation role = keycloak.realm(properties.realm())
                .roles()
                .get(roleName)
                .toRepresentation();

        keycloak.realm(properties.realm())
                .users()
                .get(userId)
                .roles()
                .realmLevel()
                .add(List.of(role));
    }
}
