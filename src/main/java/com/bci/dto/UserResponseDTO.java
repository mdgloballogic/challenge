package com.bci.dto;

import com.bci.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDTO {
    private UUID id;
    private LocalDateTime created;
    private LocalDateTime lastLogin;
    private String token;
    private boolean isActive;
    private String name;
    private String email;
    private String password;
    private List<PhoneDTO> phones;

    public UserResponseDTO() {}

    public UserResponseDTO(UUID id, LocalDateTime created, LocalDateTime lastLogin, String token, boolean isActive,
                           String name, String email, String password, List<PhoneDTO> phones) {
        this.id = id;
        this.created = created;
        this.lastLogin = lastLogin;
        this.token = token;
        this.isActive = isActive;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phones = phones;
    }

    public static UserResponseDTO fromUserForSignUp(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getCreated(),
                user.getLastLogin(),
                user.getToken(),
                user.isActive(),
                null,
                null,
                null,
                null
        );
    }

    public static UserResponseDTO fromUserForLogin(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getCreated(),
                user.getLastLogin(),
                user.getToken(),
                user.isActive(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhones().stream()
                        .map(phone -> new PhoneDTO(phone.getNumber(), phone.getCityCode(), phone.getCountryCode()))
                        .collect(Collectors.toList())
        );
    }
}