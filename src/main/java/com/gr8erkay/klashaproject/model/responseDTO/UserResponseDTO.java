package com.gr8erkay.klashaproject.model.responseDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String phoneNumber;
}
