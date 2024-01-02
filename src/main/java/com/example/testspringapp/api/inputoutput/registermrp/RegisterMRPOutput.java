package com.example.testspringapp.api.inputoutput.registermrp;

import com.example.testspringapp.api.base.OperationResult;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterMRPOutput implements OperationResult {

    private String username;

}
