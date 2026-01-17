package com.aleks4ay.hotelprovider.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class KeycloakRealmRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        if (CollectionUtils.isEmpty(realmAccess)) {
            return authorities;
        }
        Object rawRoles = realmAccess.get("roles");
        if (rawRoles instanceof List<?> roles) {
            roles.stream()
                    .map(String::valueOf)
                    .forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
        }
        return authorities;
    }
}
