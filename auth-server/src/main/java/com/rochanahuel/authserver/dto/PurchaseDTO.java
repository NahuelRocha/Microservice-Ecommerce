package com.rochanahuel.authserver.dto;


import com.rochanahuel.authserver.utils.OrderStatus;

public record PurchaseDTO(
        String orderId,
        OrderStatus orderStatus,
        String shippingAddress,
        String userName
) {}
