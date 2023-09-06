package com.rochanahuel.purchase.dto;

public record UserDTO(
        Long id,
        String username,
        String firstName,
        String lastName,
        String email,
        String phone
) {}
