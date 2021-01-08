package com.warframepda.www.repositories;

import com.warframepda.www.models.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {

    Item findByItemnameIgnoringCase(String name);
}
