package app.core.database;

import app.core.domain.Expense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Vladislav on 5/29/2016.
 */
@Repository
public interface ExpenseDAO extends CrudRepository<Expense, Long> {
}
