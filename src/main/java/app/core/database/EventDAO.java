package app.core.database;

import app.core.domain.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Vladislav on 6/1/2016.
 */

@Repository
public interface EventDAO extends CrudRepository<Event, Long> {
}
