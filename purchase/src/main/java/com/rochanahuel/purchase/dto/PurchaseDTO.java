package com.rochanahuel.purchase.dto;

import com.rochanahuel.purchase.utils.OrderStatus;

public record PurchaseDTO(
        String orderId,
        OrderStatus orderStatus,
        String shippingAddress,
        String userName
) {
}
