package com.example.testspringapp.api.inputoutput.login;

import com.example.testspringapp.api.base.OperationInput;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginInput implements OperationInput {

    private String username;
    private String password;

}
