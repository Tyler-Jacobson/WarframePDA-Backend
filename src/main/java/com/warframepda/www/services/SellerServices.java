package com.warframepda.www.services;

import com.warframepda.www.models.Seller;

import java.util.List;

public interface SellerServices {

    List<Seller> findAllSellers();

    Seller findSellerById(long id);

    Seller findSellerByName(String name);

    List<Seller> findByNameLike(String name);
}
