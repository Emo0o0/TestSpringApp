package com.example.testspringapp.api.inputoutput.registermrp;

import com.example.testspringapp.api.base.OperationInput;
import lombok.*;



@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterMRPInput implements OperationInput {

    private String username;
    private String password;
    private String confirmPassword;

}
