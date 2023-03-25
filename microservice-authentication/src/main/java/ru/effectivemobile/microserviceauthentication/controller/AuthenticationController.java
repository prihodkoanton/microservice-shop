package ru.effectivemobile.microserviceauthentication.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.effectivemobile.microserviceauthentication.dto.UserDto;
import ru.effectivemobile.microserviceauthentication.model.User;
import ru.effectivemobile.microserviceauthentication.security.jwt.JwtRequest;
import ru.effectivemobile.microserviceauthentication.security.jwt.JwtResponse;
import ru.effectivemobile.microserviceauthentication.security.jwt.service.impl.JwtTokenServiceImpl;
import ru.effectivemobile.microserviceauthentication.service.UserService;

import java.util.Optional;


@RestController
@RequestMapping(path = AuthenticationController.BASE_URL)
public class AuthenticationController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

    public static final String BASE_URL = "/api/v1/";

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenServiceImpl jwtTokenServiceImpl;

    public AuthenticationController(UserService userService, AuthenticationManager authenticationManager, JwtTokenServiceImpl jwtTokenServiceImpl) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenServiceImpl = jwtTokenServiceImpl;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        try {
            User user;
            user = userService.createUser(UserDto.toUser(userDto)).orElseThrow();
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            LOG.error("Failed to register user", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody JwtRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            LOG.warn("Invalid username or password");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenServiceImpl.createToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }


    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User user = userService.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userService.updateUser(id, user);

        return ResponseEntity.ok(UserDto.toDto(user));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        User user = userService.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/validateToken")
    public ResponseEntity<Boolean> validateToken(@RequestBody String token){
        try {
            jwtTokenServiceImpl.validateToken(token);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            LOG.warn("Invalid token", e);
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("/getUserInfo")
    public ResponseEntity<UserDto> getUserInfo(@RequestParam("token") String token){
         try {
             UserDetails userDetails = jwtTokenServiceImpl.getUserDetailsFromToken(token);
             Optional<User> user = userService.findByUsername(userDetails.getUsername());
             return ResponseEntity.ok(UserDto.toDto(user.get()));
         }catch (Exception e){
             LOG.error("Doesn't find user with this token", e);
             return ResponseEntity.notFound().build();
         }
    }

    @PostMapping("/refreshToke")
    public ResponseEntity<JwtResponse> refreshToken(@RequestParam("tokenRefreshRequest") String tokenRefreshRequest){

        try {
            final String token = jwtTokenServiceImpl.refreshToken(tokenRefreshRequest);
            return ResponseEntity.ok(new JwtResponse(token));
        }catch (Exception e){
            LOG.error("Invalid refresh token", e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
