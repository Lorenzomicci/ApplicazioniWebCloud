package com.progetto.web.cloud.progetto.user;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    public UserProfile buildUserProfile(Authentication authentication) {
        boolean authenticated = authentication != null && authentication.isAuthenticated();
        String username = null;
        Collection<String> roles = java.util.List.of();

        if (authentication instanceof JwtAuthenticationToken jwtAuth) {
            username = jwtAuth.getName();
            roles = jwtAuth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        }

        boolean hasApiRole = roles.stream().anyMatch(role -> Objects.equals(role, "ROLE_API") || Objects.equals(role, "API"));

        return new UserProfile(username, authenticated, roles, hasApiRole);
    }
}
