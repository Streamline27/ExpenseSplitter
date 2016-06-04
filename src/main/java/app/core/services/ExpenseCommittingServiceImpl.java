package app.core.services;

import app.core.domain.Event;
import app.core.domain.Expense;
import org.springframework.stereotype.Component;

/**
 * Created by Vladislav on 6/1/2016.
 */

@Component
public class ExpenseCommittingServiceImpl implements ExpenseCommittingService {

    @Override
    public void commitExpense(Expense expense, Event event) {
        expense.setEvent(event);
        event.getExpenses().add(expense);
    }
}
