package app.core.services;

import app.core.domain.Expense;
import app.core.domain.Reckoning;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Vladislav on 6/1/2016.
 */

@Component
public class ExpenseToReckoningConverterImpl implements ExpenseToReckoningConverter {

    @Override
    public Reckoning from(Expense expense) {
        return new Reckoning(expense.getName(), expense.getAmount());
    }

    @Override
    public List<Reckoning> from(List<Expense> expenses) {
        return expenses.stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

}
