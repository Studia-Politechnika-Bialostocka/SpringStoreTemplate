package com.example.store3.controllers;


import com.example.store3.data.Item;
import com.example.store3.data.ItemRepository;
import com.example.store3.data.Order;
import com.example.store3.data.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orders;

    @Autowired
    ItemRepository items;

    @GetMapping("/")
    public String getOrders(Model model) {
        model.addAttribute("orders", orders.getAllOrders());
        model.addAttribute("pendingOrder", orders.getPendingOrder());
        return "orders";
    }

    @PostMapping("/addItem/{id}")
    public String addItem(@PathVariable int id, Model model) {
        orders.addToOrder(items.getItem(id));
        return "redirect:/orders/";
    }

    @PostMapping("/submit")
    public String submitOrder(Model model) {
        orders.submitOrder();
        return "redirect:/orders/";
    }

}
