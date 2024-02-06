package com.example.testspringapp.api.inputoutput.autoamortizationcalculate;

import com.example.testspringapp.api.base.OperationResult;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AutoAmortizationCalculateOutput implements OperationResult {
    private Boolean success;
}
