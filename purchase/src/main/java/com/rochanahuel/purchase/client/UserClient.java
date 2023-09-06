package com.rochanahuel.purchase.client;

import com.rochanahuel.purchase.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "auth-service", url = "${application.config.user-url}")
public interface UserClient {

    @GetMapping("/by-username/{username}")
    Optional<UserDTO> findByUsername(@PathVariable("username") String username);

}
