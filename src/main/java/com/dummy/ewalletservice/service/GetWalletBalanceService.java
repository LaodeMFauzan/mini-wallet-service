package com.dummy.ewalletservice.service;

import com.dummy.ewalletservice.model.Wallet;
import com.dummy.ewalletservice.model.response.ErrorData;
import com.dummy.ewalletservice.model.response.ResponseData;
import com.dummy.ewalletservice.model.response.WalletData;
import com.dummy.ewalletservice.respository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetWalletBalanceService {
    @Autowired
    private WalletRepository walletRepository;

    public ResponseData getBalance(String tokenAuth){
        String customerId = tokenAuth.split(" ")[0]; // Get the customer id
        Optional<Wallet> walletData = walletRepository.findByCustomerId(customerId);

        if (walletData.isPresent() && walletData.get().getStatus()){
            Wallet result = walletData.get();

            return ResponseData.builder()
                    .wallet(WalletData.builder()
                            .id(result.getId())
                            .ownedBy(result.getCustomerId())
                            .status("enabled")
                            .enabledAt(result.getEnabledAt().toString())
                            .balance(result.getBalance().intValue())
                            .build())
                    .build();
        }

        return ResponseData.builder()
                .error(ErrorData.builder()
                        .error("Disabled")
                        .build())
                .build();
    }
}
