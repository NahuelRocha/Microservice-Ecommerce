package com.rochanahuel.authserver.mapper;

import com.rochanahuel.authserver.dto.UserDTO;
import com.rochanahuel.authserver.model.UserCredential;
import org.springframework.stereotype.Component;

@Component
public class Mappers {

    public UserDTO userToUserDTO(UserCredential userCredential) {

        return new UserDTO(
                userCredential.getId(),
                userCredential.getFirstName(),
                userCredential.getLastName(),
                userCredential.getUsername(),
                userCredential.getEmail(),
                userCredential.getPhone()
        );
    }

}
