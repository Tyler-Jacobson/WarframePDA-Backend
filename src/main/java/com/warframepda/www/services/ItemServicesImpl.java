package com.warframepda.www.services;

import com.warframepda.www.models.Item;
import com.warframepda.www.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "itemService")
public class ItemServicesImpl implements ItemServices {

    @Autowired
    private ItemRepository itemrepos;

    @Override
    public List<Item> findAllItems() {
        List<Item> list = new ArrayList<>();

        itemrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Item findItemById(long id) throws EntityNotFoundException {

        return itemrepos.findById(id).orElseThrow(() -> new EntityNotFoundException("Item With Id " + id + " Not Found"));
    }

    @Override
    public Item findItemByName(String name) throws EntityNotFoundException {
        Item item = itemrepos.findByItemnameIgnoringCase(name);
        if (item == null) {
            throw new EntityNotFoundException("Item With name " + name + " Not Found");
        }

        return item;
    }
}
