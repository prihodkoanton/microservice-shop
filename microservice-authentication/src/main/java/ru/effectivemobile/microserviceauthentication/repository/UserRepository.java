package ru.effectivemobile.microserviceauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.effectivemobile.microserviceauthentication.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
