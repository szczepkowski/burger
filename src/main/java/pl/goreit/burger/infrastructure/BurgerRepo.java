package pl.goreit.burger.infrastructure;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.goreit.burger.domain.model.Burger;

import java.util.List;


@Repository
public interface BurgerRepo extends MongoRepository<Burger, String> {

    List<Burger> findAllByStatus(Burger.Status status);
    Burger findBurgerByNameAndStatus(String name, Burger.Status status);
}
