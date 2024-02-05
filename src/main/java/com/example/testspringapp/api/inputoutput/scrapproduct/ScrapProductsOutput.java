package com.example.testspringapp.api.inputoutput.scrapproduct;

import com.example.testspringapp.api.base.OperationResult;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScrapProductsOutput implements OperationResult {

    private Boolean success;

}
