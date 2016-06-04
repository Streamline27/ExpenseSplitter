package app.core.services.splittin;

import app.core.domain.Expense;
import app.core.domain.Reckoning;
import app.core.domain.Transaction;
import app.core.services.AverageEvaluator;
import app.core.services.ExpenseToReckoningConverter;
import app.core.services.TotalSpendingEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Vladislav on 5/31/2016.
 */

@Component
public class SplittingEngineImpl implements SplittingEngine {

    @Autowired private TotalSpendingEvaluator totalSpendingEvaluator;
    @Autowired private ExpenseToReckoningConverter converter;
    @Autowired private AverageEvaluator averageEvaluator;


    @Override
    public List<Transaction> getRequiredTransactions(List<Expense> expenses){
        List<Reckoning> reckonings = totalSpendingEvaluator.getTotals(converter.from(expenses));
        Double average = averageEvaluator.getAverage(reckonings);

        return getRequiredTransactions(reckonings, average);
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
        List<Reckoning> reckonings = new ArrayList<>(summariesByPeople);
        reckonings.stream().forEach(reckoning -> reckoning.extract(average));
        return reckonings;
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
