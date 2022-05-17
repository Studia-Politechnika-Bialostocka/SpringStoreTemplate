package com.example.store3.data;

import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.*;

@Repository
@Getter
public class OrderRepository {

    private List<Order> orders = new LinkedList<>();
    private Order pendingOrder;

    public OrderRepository() {
        HashMap<Item, Integer> h1 = new HashMap<>();
        h1.put(new Item("milk", 12.44f, new ItemCategory("dairy")), 1);
        h1.put(new Item("protein bar", 12.58f, new ItemCategory("snacks")), 1);
        orders.add(new Order(h1, new Date(), 25.02f));

        HashMap<Item, Integer> h2 = new HashMap<>();
        h2.put(new Item("milk", 12.44f, new ItemCategory("dairy")), 2);
        h2.put(new Item("protein bar", 12.58f, new ItemCategory("snacks")), 4);
        orders.add(new Order(h2, new Date(), 50.18f));

        pendingOrder = null;
    }

    public List<Order> getAllOrders() { return orders; }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void addToOrder(Item item) {
        if (pendingOrder == null)
            pendingOrder = new Order(new HashMap<Item, Integer>(), new Date(), 0.0f);

        boolean existsInOrder = false;
        for (Item i : pendingOrder.getItems().keySet()) {
            if (i == item) {
                pendingOrder.getItems().replace(i, pendingOrder.getItems().get(i) + 1);
                existsInOrder = true;
                break;
            }
        }

        if (!existsInOrder)
            pendingOrder.getItems().put(item, 1);

        float totalPrice = 0.0f;
        for (Item i : pendingOrder.getItems().keySet())
            totalPrice += i.getPrice() * pendingOrder.getItems().get(i);
        pendingOrder.setTotalPrice(totalPrice);

    }

    public void submitOrder() {
        if (pendingOrder == null) return;

        orders.add(pendingOrder);
        pendingOrder = null;
    }
}
