package com.example.testspringapp.api.inputoutput.autotypecalculate;

import com.example.testspringapp.api.base.OperationResult;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AutoTypeCalculateOutput implements OperationResult {
    private Boolean success;
}
