package com.dummy.ewalletservice.service;

import com.dummy.ewalletservice.model.Wallet;
import com.dummy.ewalletservice.model.response.ErrorData;
import com.dummy.ewalletservice.model.response.ResponseData;
import com.dummy.ewalletservice.model.response.WalletData;
import com.dummy.ewalletservice.respository.WalletRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Service
public class PostEnableWalletService {

    @Autowired
    private WalletRepository walletRepository;

    public ResponseData postEnableWallet(String tokenAuth){
        String username = "asd"; // should decrypt the token
        String id = "asd";
        String customerId = "asd";
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        // get the customer first
        Iterable<Wallet> wallets = walletRepository.findAll();
        for (Wallet wallet : wallets) {
            if (StringUtils.equalsIgnoreCase(customerId, wallet.getCustomerId()) && wallet.getStatus()){
                // return response fail
                return ResponseData.builder()
                        .error(ErrorData.builder()
                                .error("Already enabled")
                                .build())
                        .build();
            }
        }

        walletRepository.save(Wallet.builder()
                        .status(true)
                        .balance(BigDecimal.ZERO)
                        .customerId(customerId)
                        .enabledAt(currentTime)
                .build());

        return ResponseData.builder()
                .wallet(WalletData.builder()
                        .id("asd") //todo how to get id
                        .enabledAt(currentTime.toString())
                        .ownedBy(customerId)
                        .balance(0)
                        .build())
                .build();
    }
}
