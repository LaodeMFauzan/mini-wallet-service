package com.dummy.ewalletservice.respository;

import com.dummy.ewalletservice.model.Deposit;
import com.dummy.ewalletservice.model.Wallet;
import org.springframework.data.repository.CrudRepository;

public interface DepositRepository extends CrudRepository<Deposit, String> {
}
