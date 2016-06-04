package app.core.services.splittin;

import app.core.domain.Expense;
import app.core.domain.Reckoning;
import app.core.domain.Transaction;

import java.util.List;

/**
 * Created by Vladislav on 6/1/2016.
 */
public interface SplittingEngine {
    List<Transaction> getRequiredTransactions(List<Expense> expenses);
    public List<Transaction> getRequiredTransactions(List<Reckoning> summariesByPeople, Double average);
}
