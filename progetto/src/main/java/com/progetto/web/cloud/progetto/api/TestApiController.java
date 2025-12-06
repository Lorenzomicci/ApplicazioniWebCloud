package com.progetto.web.cloud.progetto.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@Tag(name = "Test API", description = "Endpoints protected by Keycloak JWT")
public class TestApiController {

    @Operation(
        summary = "Check authentication",
        description = "Returns information from the JWT issued by Keycloak.",
        security = {@SecurityRequirement(name = "bearer-jwt")}
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Token is valid",
            content = @Content(schema = @Schema(implementation = Map.class))),
        @ApiResponse(responseCode = "401", description = "Missing or invalid token")
    })
    @GetMapping
    public Map<String, Object> securedEndpoint(@AuthenticationPrincipal Jwt jwt) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Authenticated via Keycloak JWT");
        response.put("subject", jwt.getSubject());
        response.put("preferred_username", jwt.getClaimAsString("preferred_username"));
        response.put("roles", extractRealmRoles(jwt));
        return response;
    }

    private List<String> extractRealmRoles(Jwt jwt) {
        Map<String, Object> realmAccess = jwt.getClaimAsMap("realm_access");
        Object roles = realmAccess != null ? realmAccess.get("roles") : null;
        if (roles instanceof List<?> roleList) {
            return roleList.stream().map(Object::toString).toList();
        }
        return List.of();
    }
}
