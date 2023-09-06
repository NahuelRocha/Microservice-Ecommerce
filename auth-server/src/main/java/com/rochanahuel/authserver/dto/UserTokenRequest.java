package com.rochanahuel.authserver.dto;

public record UserTokenRequest(
        String name,
        String password
) {
}
