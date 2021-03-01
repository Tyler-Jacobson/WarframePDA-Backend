package com.warframepda.www.services;

import com.warframepda.www.models.Item;
import com.warframepda.www.models.Order;
import com.warframepda.www.models.Part;
import com.warframepda.www.repositories.ItemRepository;
import com.warframepda.www.repositories.PartRepository;
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

    @Autowired
    private PartRepository partrepos;

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

    @Transactional
    @Override
    public Item save(Item item) {

        // A new item is created, which will have all of the desired incoming data appended to it
        // This prevents the accidental addition of something such as an ID in the data, which should not be recieved from incoming requests at all
        Item newItem = new Item();
        

        try {
            // This attempts to find the name of the incoming item in the database
            Item existingItem = itemrepos.findByItemnameIgnoringCase(item.getItemname());

            // This attempts to set the name and ID on the new item.
            // If the either is null (the item is not in the database) this will throw an error, sending us to the catch block
            newItem.setItemid(existingItem.getItemid());
            newItem.setItemname(existingItem.getItemname());
        }
        catch (Exception e) {
            // Since the item does not exist in the database, we simply create it
            newItem.setItemname(item.getItemname());
        }

        // Setting the image
        newItem.setImageurl(item.getImageurl());

        // A new list of parts is created, which is going to receive all of the new parts data
        List<Part> newPartsList = new ArrayList<>();

        // p is the individual part data received from the incoming request
        for (Part p : item.getParts()) {

            // New part is created
            Part newPart = new Part();

            // Newly created part is assigned to the newly created item above
            newPart.setItem(newItem);

            // the name is received from the incoming request
            newPart.setPartname(p.getPartname());

            // o is each individual order on each part received from the incoming request
            for (Order o : p.getOrders()) {

                // New order is created
                Order newOrder = new Order();

                // This order is assigned to the newly created part above
                newOrder.setPart(newPart);

                // Price is received from incoming request
                newOrder.setPrice(o.getPrice());

                // Seller is received from incoming request
                newOrder.setSeller(o.getSeller());

                // Adds the newly created order to the new part
                newPart.getOrders().add(newOrder);
            }

            // Adds the newly created part to new parts list
            newPartsList.add(newPart);
        }

        // The new list of parts is set to the item
        newItem.setParts(newPartsList);

        // The item is saved to the repository
        return itemrepos.save(newItem);
    }

}
