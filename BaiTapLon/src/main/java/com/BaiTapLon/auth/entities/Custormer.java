package com.BaiTapLon.auth.entities;

import com.BaiTapLon.auth.util.UserRoles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Custormer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int custormerId;

    @NotBlank(message = "The username flied can not be blank")
    private String username;

    @NotBlank(message = "The email flied can not be blank")
    @Column(unique = true)
    @Email(message = "Please enter email in proper format")
    private String email;

    @NotBlank(message = "The password flied can not be blank")
    @Column(unique = true)
    @Size(min = 8, message = "The password must have at least 8 characters")
    private String password;

    @NotBlank(message = "The phoneNumber flied can not be blank")
    private String phoneNumber;

    @NotBlank(message = "The date flied can not be blank")
    private String date;

    @Enumerated(EnumType.STRING)
    private UserRoles roles;

    private int money;

}
