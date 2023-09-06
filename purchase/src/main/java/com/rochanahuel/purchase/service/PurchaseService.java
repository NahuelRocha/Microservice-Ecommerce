package com.rochanahuel.purchase.service;

import com.rochanahuel.purchase.dto.PurchaseDTO;
import com.rochanahuel.purchase.dto.PurchaseRequest;

import java.util.List;

public interface PurchaseService {

    PurchaseDTO createPurchase(PurchaseRequest purchase);

    List<PurchaseDTO> findByUsername(String userName);


}
