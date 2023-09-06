package com.rochanahuel.authserver.service.impl;

import com.rochanahuel.authserver.client.PurchaseClient;
import com.rochanahuel.authserver.config.AuthConfig;
import com.rochanahuel.authserver.dto.PurchaseByUsernameResponse;
import com.rochanahuel.authserver.dto.UserCredentialRequest;
import com.rochanahuel.authserver.dto.UserDTO;
import com.rochanahuel.authserver.dto.UserTokenRequest;
import com.rochanahuel.authserver.exceptions.UserNotFoundException;
import com.rochanahuel.authserver.exceptions.UsernameInUseException;
import com.rochanahuel.authserver.mapper.Mappers;
import com.rochanahuel.authserver.model.UserCredential;
import com.rochanahuel.authserver.repository.UserCredentialRepository;
import com.rochanahuel.authserver.service.RegisterService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final UserCredentialRepository userRepository;
    private final AuthConfig authConfig;
    private final Mappers mappers;
    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;
    private final PurchaseClient purchaseClient;

    public UserDTO saveUser(UserCredentialRequest userRequest) {

        Optional<UserCredential> findUser = userRepository.findByUsername(userRequest.username());

        if (findUser.isEmpty()) {
            UserCredential newUser = UserCredential.builder()
                    .firstName(userRequest.firstName())
                    .lastName(userRequest.lastName())
                    .username(userRequest.username())
                    .email(userRequest.email())
                    .phone(userRequest.phone())
                    .password(authConfig.passwordEncoder().encode(userRequest.password()))
                    .build();

            userRepository.save(newUser);

            return mappers.userToUserDTO(newUser);
        } else {
            throw new UsernameInUseException("Username in use.");
        }


    }

    @Override
    public String generateToken(UserTokenRequest userTokenRequest) {

        UserCredential findUser = userRepository.findByUsername(userTokenRequest.name())
                .orElseThrow(() -> new UserNotFoundException("User not found with: " + userTokenRequest.name()));

        Authentication verifyAuth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userTokenRequest.name(),
                        userTokenRequest.password()
                )
        );

        if (verifyAuth.isAuthenticated()) {

            return jwtService.generateToken(findUser.getUsername());

        } else {
            throw new RuntimeException("Invalid Access");
        }

    }

    @Override
    public String validateToken(String token) {
        jwtService.validateToken(token);

        return "Success";
    }

    @Override
    public PurchaseByUsernameResponse findAllPuchaseByUsername(String username) {

        try {
            UserCredential findUser = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));

            var purchases = purchaseClient.findAllPurchaseByUsername(findUser.getUsername());

            return PurchaseByUsernameResponse.builder()
                    .firstname(findUser.getFirstName())
                    .lastname(findUser.getLastName())
                    .phone(findUser.getPhone())
                    .email(findUser.getEmail())
                    .purchases(purchases)
                    .build();

        } catch (FeignException.NotFound e) {
            throw new UserNotFoundException("The user did not make purchases");
        }


    }

    @Override
    public UserDTO findByUsername(String username) {

        UserCredential findUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with: " + username));

        return mappers.userToUserDTO(findUser);
    }


}
