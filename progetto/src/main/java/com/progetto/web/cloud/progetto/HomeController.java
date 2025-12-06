package com.progetto.web.cloud.progetto;

import com.progetto.web.cloud.progetto.user.UserProfile;
import com.progetto.web.cloud.progetto.user.UserProfileService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {

    private final UserProfileService userProfileService;

    public HomeController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping
    public String getStatus() {
        return "API REST protetta da Keycloak";
    }

    @GetMapping("/profile")
    public UserProfile getProfile(Authentication authentication) {
        return userProfileService.buildUserProfile(authentication);
    }
}
