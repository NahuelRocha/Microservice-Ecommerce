package com.rochanahuel.purchase.service.impl;

import com.rochanahuel.purchase.client.UserClient;
import com.rochanahuel.purchase.dto.PurchaseDTO;
import com.rochanahuel.purchase.dto.PurchaseRequest;
import com.rochanahuel.purchase.dto.UserDTO;
import com.rochanahuel.purchase.exceptions.PurchaseNotFoundException;
import com.rochanahuel.purchase.exceptions.UserNotFoundException;
import com.rochanahuel.purchase.mappers.Mappers;
import com.rochanahuel.purchase.model.Purchase;
import com.rochanahuel.purchase.repository.PurchaseRepository;
import com.rochanahuel.purchase.service.PurchaseService;
import com.rochanahuel.purchase.utils.OrderStatus;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final Mappers mappers;
    private final UserClient userClient;

    @Override
    public PurchaseDTO createPurchase(PurchaseRequest purchase) {

        try {
            Optional<UserDTO> findUser = userClient.findByUsername(purchase.username());

            if (findUser.isPresent()) {

                Purchase newPurchase = Purchase.builder()
                        .orderId(UUID.randomUUID().toString())
                        .date(LocalDateTime.now())
                        .orderStatus(OrderStatus.PLACED)
                        .shippingAddress(purchase.shippingAddress())
                        .userName(purchase.username())
                        .build();

                purchaseRepository.save(newPurchase);

                return mappers.purchaseToDTO(newPurchase);
            }
        } catch (FeignException.NotFound e) {

            throw new UserNotFoundException("User not found with username: " + purchase.username());
        }

        return null;
    }

    @Override
    public List<PurchaseDTO> findByUsername(String userName) {

        List<Purchase> findPurchases = purchaseRepository.findByUserName(userName);

        if (findPurchases.isEmpty()) {
            throw new PurchaseNotFoundException("Purchase not found");
        }

        return findPurchases.stream().map(mappers::purchaseToDTO).collect(Collectors.toList());

    }


}
