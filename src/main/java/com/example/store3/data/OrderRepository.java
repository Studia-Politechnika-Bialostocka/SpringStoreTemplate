package com.example.store3.data;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
@Getter
public class OrderRepository {

    List<Order> orders = new LinkedList<>();
    List<Item> items = new LinkedList<>();
    List<ItemCategory> categories = new ArrayList<>();


    public OrderRepository(){
        categories.add(new ItemCategory("dairy"));
        categories.add(new ItemCategory("snacks"));

        items.add(new Item(1, "milk", 12.44f, categories.get(0)));
        items.add(new Item(2, "protein bar", 12.58f, categories.get(1)));
        HashMap<Item, Integer> procucts_order1 = new HashMap<Item, Integer>();
        Item firstItem = items.get(0);
        Item secondItem = items.get(1);
        procucts_order1.put(firstItem, 1);
        procucts_order1.put(secondItem, 2);
        orders.add(new Order(1, procucts_order1, true));

        HashMap<Item, Integer> procucts_order2 = new HashMap<Item, Integer>();
        procucts_order2.put(firstItem, 1);
        orders.add(new Order(2, procucts_order2, true));

        HashMap<Item, Integer> procucts_order3 = new HashMap<Item, Integer>();
        procucts_order3.put(firstItem, 10);
        orders.add(new Order(3, procucts_order3, false));
    }

    public Order getOrder(int id){
        for(Order order : orders){
            if(order.getId() == id){
                return order;
            }
        }
        return null;
    }

    public void finalizeOrder(int id){
        for(Order order : orders){
            if(order.getId() == id){
                orders.add(new Order(order.getId(), order.getItems(), true));
                orders.remove(order);
            }
        }
    }

    public void addItem(Item item) {
        Order notFinishedOrder = getNotFinishedOrder();
        if(notFinishedOrder != null){
            orders.add(new Order(notFinishedOrder.getId(),
                    concatOrderItemsWithNewItem(notFinishedOrder.getItems(), item),
                    false));
            orders.remove(notFinishedOrder);
        } else {
            orders.add(new Order(orders.size() + 1,
                    concatOrderItemsWithNewItem(new HashMap<Item, Integer>(), item),
                    false));
        }


    }

    public HashMap<Item, Integer> concatOrderItemsWithNewItem(HashMap<Item, Integer> orderItems, Item item){
        if(orderItems.containsKey(item)){
            orderItems.put(item, orderItems.get(item) + 1);
        } else {
            orderItems.put(item, 1);
        }
        return orderItems;
    }

    private Order getNotFinishedOrder(){
        for(Order order : orders){
            if(!order.isFinished()){
                return order;
            }
        }
        return null;
    }
}
