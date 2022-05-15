package com.example.store3.controllers;

import com.example.store3.data.Item;
import com.example.store3.data.ItemRepository;
import com.example.store3.data.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    ItemRepository items;

    @Autowired
    OrderRepository orders;


    public OrderController(){

    }

    @GetMapping("/")
    public String getOrders(Model model){
        model.addAttribute("orders", orders.getOrders());
        return "orders";
    }

    @GetMapping("/{id}")
    public String getOrder(@PathVariable("id") int id, Model model) {
        model.addAttribute("order", this.orders.getOrder(id));
        return "order";
    }

    @PostMapping("/finalize/{id}")
    public String finalizeOrder(@PathVariable("id") int id, Model model) {
        this.orders.finalizeOrder(id);
        return "redirect:/orders/";
    }

    @PostMapping("/add/{id}")
    public String addItem(@PathVariable("id") int id, Model model) {
        Item item = this.items.getItem(id);
        this.orders.addItem(item);
        return "redirect:/orders/";
    }


}
