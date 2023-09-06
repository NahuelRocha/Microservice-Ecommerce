package com.rochanahuel.authserver.service;

import com.rochanahuel.authserver.dto.PurchaseByUsernameResponse;
import com.rochanahuel.authserver.dto.UserCredentialRequest;
import com.rochanahuel.authserver.dto.UserDTO;
import com.rochanahuel.authserver.dto.UserTokenRequest;

public interface RegisterService {

    UserDTO saveUser(UserCredentialRequest userRequest);

    String generateToken(UserTokenRequest userTokenRequest);

    String validateToken(String token);

    PurchaseByUsernameResponse findAllPuchaseByUsername(String userName);

    UserDTO findByUsername(String username);
}
