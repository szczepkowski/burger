package pl.goreit.burger.infrastructure;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.goreit.burger.domain.model.Profile;

@Repository
public interface ProfileRepo extends MongoRepository<Profile, String> {
    Profile findByLogin(String login);
}
