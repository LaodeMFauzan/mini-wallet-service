package com.dummy.ewalletservice.service;

import com.dummy.ewalletservice.model.Deposit;
import com.dummy.ewalletservice.model.Wallet;
import com.dummy.ewalletservice.model.response.DepositData;
import com.dummy.ewalletservice.model.response.ErrorData;
import com.dummy.ewalletservice.model.response.ResponseData;
import com.dummy.ewalletservice.model.request.PostMoneyRequest;
import com.dummy.ewalletservice.respository.DepositRepository;
import com.dummy.ewalletservice.respository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

import static java.math.BigDecimal.valueOf;

@Service
public class PostAddMoneyService {
    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private DepositRepository depositRepository;

    @Transactional
    public ResponseData postAddMoney(PostMoneyRequest request){
        String customerId = request.getAuthToken();//todo parse this token to get cust id
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        Optional<Wallet> walletData = walletRepository.findByCustomerId(customerId);

        if (walletData.isPresent()){
            depositRepository.save(Deposit.builder()
                    .amount(valueOf(Long.parseLong(request.getAmount())))
                    .depositedAt(currentTime)
                    .customerId(customerId)
                    .walletId(walletData.get().getId())
                    .build());

            Wallet currentWallet = walletData.get();
            currentWallet.setBalance(currentWallet.getBalance().add(valueOf(Long.parseLong(request.getAmount()))));
            walletRepository.save(currentWallet);

            return ResponseData.builder()
                    .deposit(DepositData.builder()
                            .id("asd") // todo figure out how to save the id
                            .depositedAt(customerId)
                            .status("success")
                            .amount(Integer.valueOf(request.getAmount()))
                            .depositedAt(String.valueOf(currentTime))
                            .referenceId(UUID.randomUUID().toString())
                            .build())
                    .build();
        }

        return ResponseData.builder()
                .error(ErrorData.builder()
                        .error("Customer id not exist")
                        .build())
                .build();
    }
}
