package com.warframepda.www.controllers;

import com.warframepda.www.models.Item;
import com.warframepda.www.services.ItemServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemServices itemServices;

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<?> listAllItems() {
        List<Item> myItems = itemServices.findAllItems();
        System.out.println(System.getenv("TickleTom"));
        return new ResponseEntity<>(myItems, HttpStatus.OK);
    }

    @GetMapping(value = "/item/{itemid}", produces = "application/json")
    public ResponseEntity<?> getItemById(@PathVariable Long itemid) {
        Item i = itemServices.findItemById(itemid);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }

    @GetMapping(value = "/item/name/{name}", produces = "application/json")
    public ResponseEntity<?> findItemByName(@PathVariable String name) {
        Item i = itemServices.findItemByName(name);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }

    // Non-read request mappings

    // The primary request that will be used by the application. This functions as both a create and update function,
    // and does not use any incoming ids to create or update data, as the script sending the data is designed to be id-free
    @PostMapping(value = "/item", produces = "application/json")
    public ResponseEntity<?> addNewItem(@Valid @RequestBody Item newItem) {

        newItem.setItemid(0);
        newItem = itemServices.save(newItem);

        HttpHeaders responseHeaders = new HttpHeaders();

        URI newItemURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{itemid}").buildAndExpand(newItem.getItemid()).toUri();
        responseHeaders.setLocation(newItemURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.OK);
    }

}
