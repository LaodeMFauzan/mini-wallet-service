package com.dummy.ewalletservice.service;

import com.dummy.ewalletservice.model.Customer;
import com.dummy.ewalletservice.model.response.postinitialize.ErrorDataInitialize;
import com.dummy.ewalletservice.model.response.postinitialize.InitializeData;
import com.dummy.ewalletservice.model.response.postinitialize.InitializeResponse;
import com.dummy.ewalletservice.respository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostInitializeService {
    @Autowired
    private CustomerRepository customerRepository;

    public InitializeResponse initialize(String customerId) {
        Optional<Customer> result = customerRepository.findById(customerId);
        String token = "123"; //todo find a way to generate this - alternative: put this as env var
        String status = "success";

        if (result.isEmpty()) {
            return InitializeResponse.builder()
                    .data(InitializeData.builder()
                            .error(ErrorDataInitialize.builder()
                                    .customerXid(List.of("Missing data for required field."))
                                    .build())
                            .build())
                    .status("fail")
                    .build();
        }

        return InitializeResponse.builder()
                .data(InitializeData.builder()
                        .token(token)
                        .build())
                .status(status)
                .build();
    }
}
