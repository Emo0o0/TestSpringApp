package com.example.testspringapp.core.processors;

import com.example.testspringapp.api.inputoutput.autotypecalculate.AutoTypeCalculateInput;
import com.example.testspringapp.api.inputoutput.autotypecalculate.AutoTypeCalculateOperation;
import com.example.testspringapp.api.inputoutput.notification.NotificationInput;
import com.example.testspringapp.api.inputoutput.notification.NotificationOperation;
import com.example.testspringapp.api.inputoutput.scrapproduct.ScrapProductsInput;
import com.example.testspringapp.api.inputoutput.scrapproduct.ScrapProductsOperation;
import com.example.testspringapp.api.inputoutput.scrapproduct.ScrapProductsOutput;
import com.example.testspringapp.core.exceptions.scrapproduct.ProductInCustomerCardException;
import com.example.testspringapp.persistence.entities.Notification;
import com.example.testspringapp.persistence.entities.Product;
import com.example.testspringapp.persistence.entities.User;
import com.example.testspringapp.persistence.entities.UserType;
import com.example.testspringapp.persistence.repositories.NotificationRepository;
import com.example.testspringapp.persistence.repositories.ProductRepository;
import com.example.testspringapp.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ScrapProductsOperationProcessor implements ScrapProductsOperation {

    private final ProductRepository productRepository;
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final NotificationOperation notificationOperation;
    private final AutoTypeCalculateOperation autoTypeCalculateOperation;

    @Override
    public ScrapProductsOutput process(ScrapProductsInput scrapProductsInput) {


        for (Product p : scrapProductsInput.getProductsToScrap()) {
            if (!p.getCustomers().isEmpty()) {
                throw new ProductInCustomerCardException("Product [" + p.getTitle() + "] exists in one or more customer cards");
            }

            NotificationInput input = NotificationInput.builder()
                    .product(p)
                    .productChange("was scrapped")
                    .build();
            notificationOperation.process(input);
            p.scrapProduct();
            productRepository.save(p);
            autoTypeCalculateOperation.process(new AutoTypeCalculateInput());




        return ScrapProductsOutput.builder()
                .success(true)
                .build();
    }
}
