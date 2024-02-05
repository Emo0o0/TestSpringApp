package com.example.testspringapp.api.inputoutput.unregisterproductfromcustomer;

import com.example.testspringapp.api.base.OperationResult;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnregisterProductFromCustomerOutput implements OperationResult {
    private Boolean success;
}
