package com.example.testspringapp.api.inputoutput.registercustomer;

import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterCustomerInput {

    private String name;
    private String phone;
    @Email(message = "THIS IS NOT AN EMAIL")
    private String email;
}
