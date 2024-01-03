package com.example.testspringapp.api.inputoutput.login;

import com.example.testspringapp.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginOperationInput implements OperationInput {

    @NotBlank(message = "TEST")
    private String username;
    private String password;

}
