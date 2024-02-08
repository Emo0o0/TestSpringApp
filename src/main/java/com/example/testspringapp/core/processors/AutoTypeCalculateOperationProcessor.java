package com.example.testspringapp.core.processors;

import com.example.testspringapp.api.inputoutput.autoamortizationcalculate.AutoAmortizationCalculateInput;
import com.example.testspringapp.api.inputoutput.autoamortizationcalculate.AutoAmortizationCalculateOperation;
import com.example.testspringapp.api.inputoutput.autotypecalculate.AutoTypeCalculateInput;
import com.example.testspringapp.api.inputoutput.autotypecalculate.AutoTypeCalculateOperation;
import com.example.testspringapp.api.inputoutput.autotypecalculate.AutoTypeCalculateOutput;
import com.example.testspringapp.api.inputoutput.notification.NotificationInput;
import com.example.testspringapp.api.inputoutput.notification.NotificationOperation;
import com.example.testspringapp.persistence.entities.Product;
import com.example.testspringapp.persistence.entities.ProductType;
import com.example.testspringapp.persistence.repositories.NotificationRepository;
import com.example.testspringapp.persistence.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutoTypeCalculateOperationProcessor implements AutoTypeCalculateOperation {

    private final ProductRepository productRepository;
    private final AutoAmortizationCalculateOperation autoAmortizationCalculateOperation;
    private final NotificationOperation notificationOperation;


    @Override
    public AutoTypeCalculateOutput process(AutoTypeCalculateInput autoTypeCalculateInput) {

        AutoAmortizationCalculateInput input = new AutoAmortizationCalculateInput();
        autoAmortizationCalculateOperation.process(input);


        for (Product p : productRepository.findAllByAmortizationLessThan(100d)) {
            if (p.getProductType() == ProductType.DMA && p.getAmortization() > 64) {
                p.changeTypeToMA();
                NotificationInput notificationInput = NotificationInput.builder()
                        .product(p)
                        .productChange("was moved to MA")
                        .build();
                notificationOperation.process(notificationInput);
                productRepository.save(p);
            }
        }

        return AutoTypeCalculateOutput.builder()
                .success(true)
                .build();

    }
}
