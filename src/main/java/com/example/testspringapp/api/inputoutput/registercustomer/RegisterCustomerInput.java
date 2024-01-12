package com.example.testspringapp.api.inputoutput.registercustomer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterCustomerInput {

    private String name;
    private String phone;
    @Email
    private String email;
}
