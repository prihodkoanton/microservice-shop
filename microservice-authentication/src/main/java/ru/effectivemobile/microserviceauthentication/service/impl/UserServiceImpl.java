package ru.effectivemobile.microserviceauthentication.service.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.effectivemobile.microserviceauthentication.model.Role;
import ru.effectivemobile.microserviceauthentication.model.User;
import ru.effectivemobile.microserviceauthentication.repository.UserRepository;
import ru.effectivemobile.microserviceauthentication.service.UserService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service("UserService")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> createUser(User customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return Optional.of(userRepository.save(customer));
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Transactional
    public Optional<User> updateUser(Long id, User user) {
        if (userRepository.existsById(id)) {
            User updateUser = userRepository.findById(id).orElse(null);
            updateUser.setUsername(user.getUsername());
            updateUser.setEmail(user.getEmail());
            updateUser.setPassword(user.getPassword());
            updateUser.setBalance(user.getBalance());
            updateUser.setRole(user.getRole());
            return Optional.of(userRepository.save(updateUser));
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public void increaseUserBalance(User user, BigDecimal amount) {
        BigDecimal currentBalance = user.getBalance();
        BigDecimal newBalance = currentBalance.add(amount);
        user.setBalance(newBalance);
        userRepository.save(user);
    }

    @Transactional
    public void decreaseUserBalance(User user, BigDecimal amount) {
        BigDecimal currentBalance = user.getBalance();
        BigDecimal newBalance = currentBalance.subtract(amount);
        user.setBalance(newBalance);
        userRepository.save(user);
    }

    @Transactional
    public void updateUserRole(User user, Role role) {
        user.setRole(role);
        userRepository.save(user);
    }

    @Transactional
    public void updateUserPassword(User user, String password) {
        user.setPassword(password);
        userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = userOptional.get();
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(user.getRole().toString());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
