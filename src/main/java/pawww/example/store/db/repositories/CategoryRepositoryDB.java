package pawww.example.store.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pawww.example.store.db.Category;

public interface CategoryRepositoryDB extends JpaRepository<Category, Integer> {

    Category findByName(String name);

}