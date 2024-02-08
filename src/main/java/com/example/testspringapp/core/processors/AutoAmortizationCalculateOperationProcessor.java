package com.example.testspringapp.core.processors;

import com.example.testspringapp.api.inputoutput.autoamortizationcalculate.AutoAmortizationCalculateInput;
import com.example.testspringapp.api.inputoutput.autoamortizationcalculate.AutoAmortizationCalculateOperation;
import com.example.testspringapp.api.inputoutput.autoamortizationcalculate.AutoAmortizationCalculateOutput;
import com.example.testspringapp.persistence.entities.Product;
import com.example.testspringapp.persistence.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class AutoAmortizationCalculateOperationProcessor implements AutoAmortizationCalculateOperation {

    private final ProductRepository productRepository;

    @Override
    public AutoAmortizationCalculateOutput process(AutoAmortizationCalculateInput autoAmortizationCalculateInput) {


        LocalDateTime currentDate = Instant.now().atZone(ZoneId.of("UTC")).toLocalDateTime();
        double newAmortizationValue;
        double yearlyIncrease;
        int yearsPassed;


        for (Product p : productRepository.findAllByAmortizationLessThan(100d)) {

            if(p.getScrappingCriteria()>0) {
                yearlyIncrease = (100 - p.getAmortization()) / p.getScrappingCriteria();
                yearsPassed = currentDate.getYear() - p.getTimestamp().toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime().getYear();
                newAmortizationValue = p.getOriginalAmortization() + (yearlyIncrease * yearsPassed);
                p.setAmortization(newAmortizationValue);
                productRepository.save(p);
            }
        }

        return AutoAmortizationCalculateOutput.builder()
                .success(true)
                .build();
    }
}
