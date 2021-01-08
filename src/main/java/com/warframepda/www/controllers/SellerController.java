package com.warframepda.www.controllers;

import com.warframepda.www.models.Seller;
import com.warframepda.www.services.SellerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    @Autowired
    private SellerServices sellerServices;

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<?> listAllSellers() {
        List<Seller> mySellers = sellerServices.findAllSellers();
        return new ResponseEntity<>(mySellers, HttpStatus.OK);
    }

    @GetMapping(value = "/{sellerid}", produces = "application/json")
    public ResponseEntity<?> getSellerById(@PathVariable Long sellerid) {
        Seller s = sellerServices.findSellerById(sellerid);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @GetMapping(value = "/name/{name}", produces = "application/json")
    public ResponseEntity<?> findSellerByName(@PathVariable String name) {
        Seller s = sellerServices.findSellerByName(name);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @GetMapping(value = "/namelike/{name}", produces = "application/json")
    public ResponseEntity<?> findSellerByNameLike(@PathVariable String name) {
        List<Seller> rtnList = sellerServices.findByNameLike(name);
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

}
