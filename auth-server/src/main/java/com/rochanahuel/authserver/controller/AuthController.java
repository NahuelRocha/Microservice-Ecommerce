package com.rochanahuel.authserver.controller;

import com.rochanahuel.authserver.dto.PurchaseByUsernameResponse;
import com.rochanahuel.authserver.dto.UserCredentialRequest;
import com.rochanahuel.authserver.dto.UserDTO;
import com.rochanahuel.authserver.dto.UserTokenRequest;
import com.rochanahuel.authserver.service.impl.RegisterServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegisterServiceImpl registerService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserCredentialRequest userRequest) {

        return ResponseEntity.ok(registerService.saveUser(userRequest));
    }

    @GetMapping("/token")
    public ResponseEntity<String> generateToken(@RequestBody UserTokenRequest userTokenRequest) {

        return ResponseEntity.ok(registerService.generateToken(userTokenRequest));
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam("token") String token) {

        return ResponseEntity.ok(registerService.validateToken(token));
    }

    @GetMapping("/purchases/{username}")
    public ResponseEntity<PurchaseByUsernameResponse> findPurchasesByUsername(@PathVariable("username") String username) {

        return ResponseEntity.ok(registerService.findAllPuchaseByUsername(username));
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<UserDTO> findByUsername(@PathVariable("username") String username){

        return ResponseEntity.ok(registerService.findByUsername(username));
    }


}
