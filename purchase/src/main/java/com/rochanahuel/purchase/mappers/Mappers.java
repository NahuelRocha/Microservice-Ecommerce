package com.rochanahuel.purchase.mappers;

import com.rochanahuel.purchase.dto.PurchaseDTO;
import com.rochanahuel.purchase.model.Purchase;
import org.springframework.stereotype.Component;

@Component
public class Mappers {

    public PurchaseDTO purchaseToDTO(Purchase purchase) {

        return new PurchaseDTO(
                purchase.getOrderId(),
                purchase.getOrderStatus(),
                purchase.getShippingAddress(),
                purchase.getUserName()
        );

    }

}
