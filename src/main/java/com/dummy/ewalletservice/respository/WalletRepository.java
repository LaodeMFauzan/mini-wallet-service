package com.dummy.ewalletservice.respository;

import com.dummy.ewalletservice.model.Wallet;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WalletRepository extends CrudRepository<Wallet, String> {

    Optional<Wallet> findByCustomerId(String s);
}
