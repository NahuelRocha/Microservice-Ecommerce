package com.rochanahuel.authserver.dto;

public record UserCredentialRequest(
        String firstName,
        String lastName,
        String username,
        String email,
        String phone,
        String password
) {
}
