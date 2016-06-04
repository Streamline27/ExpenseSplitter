package app.core.database;

import app.core.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Vladislav on 6/3/2016.
 */
@Repository
public interface UserDAO extends CrudRepository<User, String> {
}
