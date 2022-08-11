package com.dummy.ewalletservice.service;

import com.dummy.ewalletservice.model.Wallet;
import com.dummy.ewalletservice.model.request.patchdisable.PostDisableRequest;
import com.dummy.ewalletservice.model.response.ErrorData;
import com.dummy.ewalletservice.model.response.ResponseData;
import com.dummy.ewalletservice.model.response.WalletData;
import com.dummy.ewalletservice.respository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class PatchDisableMyWalletService {

    @Autowired
    private WalletRepository walletRepository;

    public ResponseData disableWallet(PostDisableRequest request){
        String customerId = request.getTokenAuth(); // todo decrypt token
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        Optional<Wallet> walletDataOpt = walletRepository.findByCustomerId(customerId);

        if (walletDataOpt.isPresent()){
            Wallet walletData = walletDataOpt.get();
            walletData.setStatus(false);
            walletData.setDisabledAt(currentTimestamp);
            walletRepository.save(walletData);

            return ResponseData.builder()
                    .wallet(WalletData.builder()
                            .id(walletData.getId())
                            .ownedBy(customerId)
                            .status("disabled")
                            .disabledAt(currentTimestamp.toString())
                            .balance(walletData.getBalance().intValue())
                            .build())
                    .build();
        }

        return ResponseData.builder()
                .error(ErrorData.builder()
                        .error("Customer id not exists")
                        .build())
                .build();
    }
}
