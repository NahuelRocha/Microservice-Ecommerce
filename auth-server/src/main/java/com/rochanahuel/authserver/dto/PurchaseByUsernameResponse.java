package com.rochanahuel.authserver.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record PurchaseByUsernameResponse(

        String firstname,
        String lastname,
        String phone,
        String email,
        List<PurchaseDTO> purchases
) {
}
