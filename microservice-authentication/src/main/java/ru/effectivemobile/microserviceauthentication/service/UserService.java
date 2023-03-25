package ru.effectivemobile.microserviceauthentication.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.effectivemobile.microserviceauthentication.model.Role;
import ru.effectivemobile.microserviceauthentication.model.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    Optional<User> findByEmail(String email);

    Optional<User> getUserById(Long id);

    Optional<User> createUser(User customer);

    Optional<User> findByUsername(String username);

    void deleteUser(Long id);

    void deleteUser(User user);

    Optional<User> updateUser(Long id, User user);

    void increaseUserBalance(User user, BigDecimal amount);

    void decreaseUserBalance(User user, BigDecimal amount);

    void updateUserRole(User user, Role role);

    void updateUserPassword(User user, String password);

    Optional<User> findById(Long id);
}
