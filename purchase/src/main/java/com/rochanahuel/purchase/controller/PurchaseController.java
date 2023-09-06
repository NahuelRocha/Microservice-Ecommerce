package com.rochanahuel.purchase.controller;

import com.rochanahuel.purchase.dto.PurchaseDTO;
import com.rochanahuel.purchase.dto.PurchaseRequest;
import com.rochanahuel.purchase.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping("/create")
    public ResponseEntity<PurchaseDTO> createPurchase(@RequestBody PurchaseRequest purchaseRequest) {

        return ResponseEntity.ok(purchaseService.createPurchase(purchaseRequest));
    }

    @GetMapping("/by-username/{userName}")
    public ResponseEntity<List<PurchaseDTO>> getPurchaseByUsername(@PathVariable("userName") String username){

        return ResponseEntity.ok(purchaseService.findByUsername(username));
    }

}
