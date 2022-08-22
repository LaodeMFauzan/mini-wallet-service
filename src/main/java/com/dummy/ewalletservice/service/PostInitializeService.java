package com.dummy.ewalletservice.service;

import com.dummy.ewalletservice.model.Customer;
import com.dummy.ewalletservice.model.response.postinitialize.ErrorDataInitialize;
import com.dummy.ewalletservice.model.response.postinitialize.InitializeData;
import com.dummy.ewalletservice.model.response.postinitialize.InitializeResponse;
import com.dummy.ewalletservice.respository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class PostInitializeService {
    @Autowired
    private CustomerRepository customerRepository;

    public InitializeResponse initialize(String customerId) {
        Optional<Customer> result = customerRepository.findById(customerId);
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

        String token = getJwtToken(customerId);
        return InitializeResponse.builder()
                .data(InitializeData.builder()
                        .token(token)
                        .build())
                .status(status)
                .build();
    }

    private String getJwtToken(String customerId){
        String secretKey = "myKey"; // todo put this as env var?
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(customerId)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
