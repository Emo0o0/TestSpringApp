package com.example.testspringapp.api.inputoutput.notification;

import com.example.testspringapp.api.base.OperationInput;
import com.example.testspringapp.persistence.entities.Product;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationInput implements OperationInput {

    private Product product;
    private String productChange;

}
