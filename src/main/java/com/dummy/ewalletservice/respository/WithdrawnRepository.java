package com.dummy.ewalletservice.respository;

import com.dummy.ewalletservice.model.Withdrawn;
import org.springframework.data.repository.CrudRepository;

public interface WithdrawnRepository extends CrudRepository<Withdrawn, String> {
}
