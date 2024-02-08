package com.example.testspringapp.api.inputoutput.login;

import com.example.testspringapp.api.base.OperationResult;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginOutput implements OperationResult {

    private String username;
    private Boolean success;

}
