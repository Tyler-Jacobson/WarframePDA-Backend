package com.warframepda.www.services;

import com.warframepda.www.models.Item;
import com.warframepda.www.models.Order;
import com.warframepda.www.models.Part;
import com.warframepda.www.models.Seller;
import com.warframepda.www.repositories.ItemRepository;
import com.warframepda.www.repositories.SellerRepository;
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
    private SellerRepository sellerrepos;

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
            // This attempts to find the name of the incoming item in our database
            Item existingItem = itemrepos.findByItemnameIgnoringCase(item.getItemname());

            // This attempts to set the name and ID on our new item.
            // If the either is null (the item is not in our database) this will throw an error, sending us to the catch block
            newItem.setItemid(existingItem.getItemid());
            newItem.setItemname(existingItem.getItemname());
        }
        catch (Exception e) {
            // Since the item does not exist in our database, we simply create it
            newItem.setItemname(item.getItemname());
        }

        // Setting the image
        newItem.setImageurl(item.getImageurl());

        // A new list of parts is created, much of the data related to parts is going to be overwritten,
        //
        List<Part> newPartsList = new ArrayList<>();

        for (Part p : item.getParts()) {

            Part newPart = new Part();

            newPart.setItem(newItem);

            newPart.setPartname(p.getPartname());

            for (Order o : p.getOrders()) {

                Order newOrder = new Order();

                // The seller which has been recieved from the incoming network request
                Seller recievedSeller = o.getSeller();

                newOrder.setPart(newPart);

                newOrder.setPrice(o.getPrice());

                Seller newSeller = new Seller();

                try {
                    System.out.println("Seller Try Reached");
                    Seller existingSeller = sellerrepos.findBySellernameIgnoringCase(recievedSeller.getSellername());

                    newSeller.setSellerid(existingSeller.getSellerid());
                    newSeller.setSellername(existingSeller.getSellername());

                    System.out.println("Seller Try Completed");
                }
                catch (Exception e) {
                    System.out.println("Seller Catch Reached");
                    newSeller.setSellername(recievedSeller.getSellername());
                    sellerrepos.save(newSeller);
                    System.out.println("Seller Catch Completed");
                }
                newOrder.setSeller(newSeller);

                newPart.getOrders().add(newOrder);
            }


            newPartsList.add(newPart);
        }

        newItem.setParts(newPartsList);


        return itemrepos.save(newItem);
    }
}
