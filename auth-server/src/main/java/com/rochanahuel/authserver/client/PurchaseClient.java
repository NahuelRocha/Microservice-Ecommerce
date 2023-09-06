package com.rochanahuel.authserver.client;


import com.rochanahuel.authserver.dto.PurchaseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "purchase-service", url = "${application.config.purchase-url}")
public interface PurchaseClient {

    @GetMapping("/by-username/{userName}")
    List<PurchaseDTO> findAllPurchaseByUsername(@PathVariable("userName") String userName);

}
