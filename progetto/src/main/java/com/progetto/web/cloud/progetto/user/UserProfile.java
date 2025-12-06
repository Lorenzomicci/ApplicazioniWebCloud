package com.progetto.web.cloud.progetto.user;

import java.util.Collection;

public record UserProfile(String username, boolean authenticated, Collection<String> roles, boolean hasApiRole) {
}
