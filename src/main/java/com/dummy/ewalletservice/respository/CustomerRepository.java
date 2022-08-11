package com.dummy.ewalletservice.respository;

import com.dummy.ewalletservice.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, String> {

}
