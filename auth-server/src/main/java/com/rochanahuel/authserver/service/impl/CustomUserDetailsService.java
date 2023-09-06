package com.rochanahuel.authserver.service.impl;

import com.rochanahuel.authserver.config.CustomUserDetails;
import com.rochanahuel.authserver.exceptions.UserNotFoundException;
import com.rochanahuel.authserver.model.UserCredential;
import com.rochanahuel.authserver.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserCredentialRepository userCredentialRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredential> user = userCredentialRepository.findByUsername(username);

        return user.map(CustomUserDetails::new)
                .orElseThrow(() -> new UserNotFoundException("User not found."));
    }
}
