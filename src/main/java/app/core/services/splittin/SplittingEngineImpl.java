package app.core.services.splittin;

import app.core.domain.Expense;
import app.core.domain.Reckoning;
import app.core.domain.Transaction;
import app.core.services.AverageCalculator;
import app.core.services.PersonSpendingCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static app.core.services.Converter.FROM_EXPENSE_TO_RECKONING;
import static app.core.services.Converter.convert;

/**
 * Created by Vladislav on 5/31/2016.
 */

@Component
public class SplittingEngineImpl implements SplittingEngine {

    @Autowired private PersonSpendingCalculator personSpendingCalculator;
    @Autowired private AverageCalculator averageCalculator;


    @Override
    public List<Transaction> getRequiredTransactions(List<Expense> expenses){
        List<Reckoning> reckonings = convert(expenses, FROM_EXPENSE_TO_RECKONING);

        List<Reckoning> totalSpendingByPeople = personSpendingCalculator.getPersonalSpending(reckonings);
        Double average = averageCalculator.getAverage(totalSpendingByPeople);

        return getRequiredTransactions(totalSpendingByPeople, average);
    }

    @Override
    public List<Transaction> getRequiredTransactions(List<Reckoning> totalSpendings, Double average){
        if(isUnprocessable(totalSpendings)) return new ArrayList<>();

        List<Reckoning> reckonings = getPreparedReckonings(totalSpendings, average);

        List<Reckoning> debts = getBelowZero(reckonings);
        List<Reckoning> credits = getAboweZero(reckonings);

        SplittingAlgorithm splittingAlgorithm = new SplittingAlgorithm(credits, debts);
        return splittingAlgorithm.execute();
    }



    /*
        Private helper methods
    */
    private List<Reckoning> getPreparedReckonings(List<Reckoning> summariesByPeople, Double average) {
        return summariesByPeople.stream()
                                .map(r -> new Reckoning(r.getPerson(), r.getAmount() - average))
                                .collect(Collectors.toList());
    }

    private List<Reckoning> getAboweZero(List<Reckoning> reckonings) {

        List<Reckoning> debtors = reckonings.stream().filter(Reckoning::isNegative).collect(Collectors.toList());
        debtors.forEach(Reckoning::inverseSign);
        return debtors;
    }

    private List<Reckoning> getBelowZero(List<Reckoning> reckonings) {
        return reckonings.stream().filter(Reckoning::isPositive).collect(Collectors.toList());
    }

    private boolean isUnprocessable(List<Reckoning> expenses) {
        return expenses==null || expenses.isEmpty() || expenses.size()==1;
    }

}
