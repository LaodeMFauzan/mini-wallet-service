package com.dummy.ewalletservice.service;

import com.dummy.ewalletservice.model.Wallet;
import com.dummy.ewalletservice.model.Withdrawn;
import com.dummy.ewalletservice.model.request.PostMoneyRequest;
import com.dummy.ewalletservice.model.response.ErrorData;
import com.dummy.ewalletservice.model.response.ResponseData;
import com.dummy.ewalletservice.model.response.WithdrawalData;
import com.dummy.ewalletservice.respository.WalletRepository;
import com.dummy.ewalletservice.respository.WithdrawnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

import static java.math.BigDecimal.valueOf;

@Service
public class PostUseMoneyWalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private WithdrawnRepository withdrawnRepository;

    @Transactional
    public ResponseData postUseMoney(PostMoneyRequest request){
        String customerId = request.getAuthToken();//todo parse this token to get cust id
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        Optional<Wallet> walletData = walletRepository.findByCustomerId(customerId);

        if (walletData.isPresent()){
            Wallet currentWallet = walletData.get();
            if (currentWallet.getBalance().compareTo(BigDecimal.valueOf(Long.parseLong(request.getAmount()))) > 0){
                withdrawnRepository.save(Withdrawn.builder()
                        .amount(valueOf(Long.parseLong(request.getAmount())))
                        .withdrawnAt(currentTime)
                        .customerId(customerId)
                        .walletId(walletData.get().getId())
                        .build());

                currentWallet.setBalance(currentWallet.getBalance().add(valueOf(Long.parseLong(request.getAmount()))));
                walletRepository.save(currentWallet);

                return ResponseData.builder()
                        .withdrawal(WithdrawalData.builder()
                                .id("asd") // todo figure out how to save the id
                                .withdrawnBy(customerId)
                                .status("success")
                                .amount(Integer.valueOf(request.getAmount()))
                                .withdrawnAt(String.valueOf(currentTime))
                                .referenceId(UUID.randomUUID().toString())
                                .build())
                        .build();
            }

            return ResponseData.builder()
                    .error(ErrorData.builder()
                            .error("Balance is not enough")
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
