package pawww.example.store.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pawww.example.store.db.Item;

import java.util.List;

public interface ItemRepositoryDB extends JpaRepository<Item, Integer> {
    List<Item> findByCategory_Name(String name);

}