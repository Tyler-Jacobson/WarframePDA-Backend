package com.warframepda.www.services;

import com.warframepda.www.models.Item;
import com.warframepda.www.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
