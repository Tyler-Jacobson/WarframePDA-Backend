package com.warframepda.www.services;

import com.warframepda.www.models.Seller;
import com.warframepda.www.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "sellerService")
public class SellerServicesImpl implements SellerServices {

    @Autowired
    private SellerRepository sellerrepos;

    @Override
    public List<Seller> findAllSellers() {
        List<Seller> list = new ArrayList<>();

        sellerrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Seller findSellerById(long id) throws EntityNotFoundException {

        return sellerrepos.findById(id).orElseThrow(() -> new EntityNotFoundException("Seller With Id " + id + " Not Found"));
    }

    @Override
    public Seller findSellerByName(String name) throws EntityNotFoundException {
        Seller seller = sellerrepos.findBySellernameIgnoringCase(name);

        if (seller == null) {
            throw new EntityNotFoundException("Seller With Name " + name + " Not Found");
        }

        return seller;
    }

    @Override
    public List<Seller> findByNameLike(String name) {
        List<Seller> list = sellerrepos.findBySellernameContainingIgnoringCase(name);
        return list;
    }
}
