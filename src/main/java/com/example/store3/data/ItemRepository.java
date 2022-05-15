package com.example.store3.data;

import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
@Getter
public class ItemRepository {
    List<Item> items = new LinkedList<>();
    List<ItemCategory> categories = new ArrayList<>();

    public ItemRepository(){
        categories.add(new ItemCategory("dairy"));
        categories.add(new ItemCategory("snacks"));

        items.add(new Item(1, "milk", 12.44f, categories.get(0)));
        items.add(new Item(2, "protein bar", 12.58f, categories.get(1)));
    }

    public Item getItem(int id) {
        return this.items.stream().filter(item -> item.getId() == id).findAny().orElse(null);
    }

    public ItemCategory getCategory(String name) {
        return this.categories.stream().filter(category -> name.equals(category.getName()))
                .findAny()
                .orElse(null);
    }

    public void addItem(Item item) {
        Integer nextId = this.items.stream().mapToInt(Item::getId).max().orElse(0) + 1;
        this.items.add(new Item(nextId, item.getName(), item.getPrice(), item.getCategory()));
    }

    public void removeItem(int id) {
        this.items.remove(id);
    }

    public List<Item> getItemsFromCategory(String category) {
        Predicate<Item> byCat = item -> item.getCategory().getName().equals(category);

        var result = items.stream().filter(byCat).collect(Collectors.toList());

        return result;
    }

    public void deleteItem(int id) {
        this.items.remove(id);
    }
}
