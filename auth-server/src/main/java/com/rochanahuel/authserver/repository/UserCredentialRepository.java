package com.rochanahuel.authserver.repository;

import com.rochanahuel.authserver.model.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential,Long> {

    Optional<UserCredential> findByUsername(String username);


}
