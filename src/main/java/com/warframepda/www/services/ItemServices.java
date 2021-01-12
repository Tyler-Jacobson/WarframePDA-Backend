package com.warframepda.www.services;

import com.warframepda.www.models.Item;

import java.util.List;

public interface ItemServices {

    List<Item> findAllItems();

    Item findItemById(long id);

    Item findItemByName(String name);

    Item save(Item item);
}
