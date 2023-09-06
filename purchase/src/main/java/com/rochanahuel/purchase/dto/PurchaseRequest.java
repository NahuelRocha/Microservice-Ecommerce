package com.rochanahuel.purchase.dto;

public record PurchaseRequest(
        String shippingAddress,
        String username
) {
}
