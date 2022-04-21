package com.example.store3.controllers;

import com.example.store3.data.Item;
import com.example.store3.data.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/items")
public class ItemController {
    @Autowired
    ItemRepository items;

    public ItemController() {

    }

    @GetMapping("/")
    public String getItems(Model model) {
        model.addAttribute("items", items.getItems());
        return "items";
    }

    @GetMapping("/category")
    public String getItemsFromCategory(@RequestParam(value = "name", defaultValue = "snacks") String category, Model model) {
        model.addAttribute("items", this.items.getItemsFromCategory(category));
        model.addAttribute("category", category);
        return "items";
    }

    @GetMapping("/{id}")
    public String getItem(@PathVariable("id") int id, Model model) {
        model.addAttribute("item", this.items.getItem(id));
        return "item";
    }


    @GetMapping("/new")
    public String newItem(Model model) {
        model.addAttribute("categories", this.items.getCategories());
        System.out.println("Categories " + this.items.getCategories());
        return "newItem";
    }

    @PostMapping("/new")
    public String addItem(@ModelAttribute Item item, Model model) {
        System.out.println("Item " + item);
        this.items.addItem(item);
        return "redirect:/items/";
    }

    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable("id") int id) {
        this.items.deleteItem(id);
        return "redirect:/items/";
    }



}
