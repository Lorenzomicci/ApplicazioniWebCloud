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

    @GetMapping("/")
  public String getIndex(Model model, Authentication auth) {
    model.addAttribute("name",
        auth instanceof OAuth2AuthenticationToken oauth && oauth.getPrincipal() instanceof OidcUser oidc
        ? oidc.getPreferredUsername()
        : "");
    model.addAttribute("isAuthenticated",
        auth != null && auth.isAuthenticated());
    model.addAttribute("isNice", 
        auth != null && auth.getAuthorities().stream().anyMatch(authority -> {
          return Objects.equals("API", authority.getAuthority());
        }));
    return "index.html";
  }
}
