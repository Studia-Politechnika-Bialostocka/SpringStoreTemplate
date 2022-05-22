package pawww.example.store.repositories;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pawww.example.store.db.Item;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
@SessionScope
@RequiredArgsConstructor
public class Cart {
    List<Item> items = new LinkedList<Item>();

    public List<Item> getItems() { return items; }

    public Item getItem(int id) { return items.get(id); }
    public Item getItem(String name) {
        for (Item item : items)
            if (item.getName().equals(name))
                return item;
        return null;
    }

    public int getCount(String name) {
        int count = 0;
        for (Item item : items)
            if (item.getName().equals(name))
                count++;
        return count;
    }
    public void addItem(Item item) { this.items.add(item); }
    public void removeItem(int id) {this.items.remove(id); }

    public List<Float> getPrices() {
        List<Float> prices = new ArrayList<>();
        for(Item item : items) prices.add(item.getPrice());
        return prices;
    }

    public float getAmount() {
        float amount = 0.0f;
        for(Item item : items) amount += item.getPrice();
        return amount;
    }

    public void buy() {

    }
}
