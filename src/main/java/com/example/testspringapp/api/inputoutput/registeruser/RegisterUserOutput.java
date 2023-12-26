package com.example.testspringapp.api.inputoutput.registeruser;

import com.example.testspringapp.api.base.OperationResult;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserOutput implements OperationResult {

    private String username;

}
