package pawww.example.store.services;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pawww.example.store.db.Category;

import pawww.example.store.db.Item;
import pawww.example.store.db.repositories.CategoryRepositoryDB;
import pawww.example.store.repositories.Cart;
import pawww.example.store.db.repositories.ItemRepositoryDB;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@Getter
public class StoreService {
    @Autowired
    ItemRepositoryDB items;
    @Autowired
    Cart cart;
    @Autowired
    CategoryRepositoryDB categories;



    public void addToCart(Item item){
        this.cart.addItem(item);
    }
//
    public Item isInCart(String name){
        return this.cart.getItem(name);
    }

    public List<Item> getItems() {
        return this.items.findAll();
    }

    public List<Category> getCategories(){return this.categories.findAll();}


    public Item getItem(int id){
        Optional<Item> item=items.findById(id);
        return item.isEmpty()? null:item.get();
    }
//
//
    public Category getCategory(String name){
        return this.categories.findByName(name);
    }
//
    public void addItem(Item item){
        this.items.save(item);
    }
//
    public void addCategory(Category category) {this.categories.save(category);}
//
    public void removeItem(Item item){
        this.items.delete(item);
    }

    public void removeItem(int id) { items.deleteById(id); }


//
//
//
    public List<Item> getItemsFromCategory(String category){
        return items.findByCategory_Name(category);
    }
//
    public List<Item> searchItems(String name){
        return items.findByNameStartingWith(name);
    }

    public List<Category> getMostPopularCategories(){
        List<Category> cats=new LinkedList<>();
        cats.add(new Category("snacks"));
        cats.add(new Category("bread"));
        return cats;
    }

    public float getCartValue(){
        return this.cart.getAmount();
    }

    public void buy(){
        this.cart.buy();
    }
}
