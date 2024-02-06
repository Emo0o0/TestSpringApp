package com.example.testspringapp.api.inputoutput.deletenotification;

import com.example.testspringapp.api.base.OperationResult;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteNotificationOutput implements OperationResult {

    private Boolean success;

}
