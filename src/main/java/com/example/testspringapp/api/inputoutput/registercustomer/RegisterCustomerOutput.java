package com.example.testspringapp.api.inputoutput.registercustomer;

import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterCustomerOutput {

    private String name;
    private String phone;
    private String email;
    private boolean success;

}
