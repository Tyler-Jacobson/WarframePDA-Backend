package com.warframepda.www.controllers;

import com.warframepda.www.models.Item;
import com.warframepda.www.services.ItemServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemServices itemServices;

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<?> listAllItems() {
        List<Item> myItems = itemServices.findAllItems();
        return new ResponseEntity<>(myItems, HttpStatus.OK);
    }

    @GetMapping(value = "/{itemid}", produces = "application/json")
    public ResponseEntity<?> getItemById(@PathVariable Long itemid) {
        Item i = itemServices.findItemById(itemid);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }

    @GetMapping(value = "/name/{name}", produces = "application/json")
    public ResponseEntity<?> findItemByName(@PathVariable String name) {
        Item i = itemServices.findItemByName(name);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }
}
