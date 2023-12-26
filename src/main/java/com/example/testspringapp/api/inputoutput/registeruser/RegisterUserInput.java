package com.example.testspringapp.api.inputoutput.registeruser;

import com.example.testspringapp.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;



@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserInput implements OperationInput {

    private String username;
    private String password;
    private String confirmPassword;

}
