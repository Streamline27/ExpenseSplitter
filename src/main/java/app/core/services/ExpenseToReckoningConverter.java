package app.core.services;

import app.core.domain.Expense;
import app.core.domain.Reckoning;

import java.util.List;

/**
 * Created by Vladislav on 6/1/2016.
 */
public interface ExpenseToReckoningConverter {
    Reckoning from(Expense expense);
    List<Reckoning> from(List<Expense> expenses);
}
