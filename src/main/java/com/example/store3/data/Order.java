package com.example.store3.data;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Data
@Getter
@Setter
public class Order {
    private final HashMap<Item, Integer> items;
    private Date orderDate;
    private float totalPrice;

    public Order() {
        items = new HashMap<>();
        totalPrice = 0.0f;
    }
    public Order(HashMap<Item, Integer> items) {
        this.items = items;
        totalPrice = 0.0f;
    }

    public Order(HashMap<Item, Integer> items, Date date, float totalPrice) {
        this.items = items;
        this.orderDate = date;
        this.totalPrice = totalPrice;
    }
}
