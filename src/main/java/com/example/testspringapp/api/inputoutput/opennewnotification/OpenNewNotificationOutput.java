package com.example.testspringapp.api.inputoutput.opennewnotification;

import com.example.testspringapp.api.base.OperationResult;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OpenNewNotificationOutput implements OperationResult {

    private Boolean success;

}
