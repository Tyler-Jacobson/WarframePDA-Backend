package com.warframepda.www.repositories;

import com.warframepda.www.models.Seller;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SellerRepository extends CrudRepository<Seller, Long> {

    Seller findBySellernameIgnoringCase(String name);

    List<Seller> findBySellernameContainingIgnoringCase(String likename);
}
