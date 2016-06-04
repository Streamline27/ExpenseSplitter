package app.core.services;

import app.core.domain.Event;
import app.core.domain.Expense;

/**
 * Created by Vladislav on 6/1/2016.
 */
public interface ExpenseCommittingService {
    public void commitExpense(Expense expense, Event event);
}
