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

        //Set<Notification> notifications = new HashSet<>();

        //StringBuilder sb = new StringBuilder();
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

            //sb.delete(0, sb.length());
            //sb.append("Title: ").append(p.getTitle()).append("\n")
            //        .append("Description: ").append(p.getDescription()).append("\n")
            //        .append("Product type: ").append(p.getProductType()).append("\n")
            //        .append("Amortization: ").append(p.getAmortization()).append("\n")
            //        .append("Scrapping criteria: ").append(p.getScrappingCriteria()).append(" years\n")
            //        .append("Date registered: ").append(new Date(p.getTimestamp().getTime())).append("\n")
            //        .append("\n");
//
            //p.scrapProduct();
//
            //Notification notification = Notification.builder()
            //        .title("Product [" + p.getTitle() + "] was scrapped")
            //        .message(sb.toString())
            //        .read(false)
            //        .productId(p.getId().toString())
            //        .build();
            //notificationRepository.save(notification);
            //notifications.add(notification);
//
            //productRepository.save(p);
        }

        // Set<User> mrpUsers = userRepository.findAllByUserType(UserType.MRP);
        // for (User u : mrpUsers) {
        //     for (Notification n : notifications) {
        //         u.notify(n);
        //     }
        //     userRepository.save(u);
        // }


        return ScrapProductsOutput.builder()
                .success(true)
                .build();
    }
}
