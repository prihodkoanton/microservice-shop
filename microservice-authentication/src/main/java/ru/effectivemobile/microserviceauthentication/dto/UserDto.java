package ru.effectivemobile.microserviceauthentication.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.effectivemobile.microserviceauthentication.model.Role;
import ru.effectivemobile.microserviceauthentication.model.User;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties()
public class UserDto {

    @JsonProperty("username")
    private String username;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("balance")
    private BigDecimal balance;

    @JsonProperty("role")
    private Role role;

    public static UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setBalance(user.getBalance());
        dto.setRole(user.getRole());
        return dto;
    }

    public static User toUser(UserDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setBalance(dto.getBalance());
        user.setRole(dto.getRole());
        return user;
    }
}
