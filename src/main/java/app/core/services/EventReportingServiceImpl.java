package app.core.services;

import app.core.database.EventDAO;
import app.core.domain.*;

import app.core.services.splittin.SplittingEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Vladislav on 6/1/2016.
 */

@Component
public class EventReportingServiceImpl implements EventReportingService{

    @Autowired private EventDAO eventDAO;
    @Autowired private TotalSpendingEvaluator totalSpendingEvaluator;
    @Autowired private ExpenseToReckoningConverter expenseToReckoningConverter;
    @Autowired private AverageEvaluator averageEvaluator;
    @Autowired private SplittingEngine splittingEngine;

    @Override
    public Report getReport(Long id) {
        Event event = eventDAO.findOne(id);
        if (event==null) return new Report();

        List<Expense> expenses = event.getExpenses();
        List<Reckoning> reckonings = expenseToReckoningConverter.from(expenses);
        List<Reckoning> summaries = totalSpendingEvaluator.getTotals(reckonings);

        Double average = averageEvaluator.getAverage(summaries);
        Double total = summaries.stream().mapToDouble(Reckoning::getAmount).sum();

        List<Transaction> transactions = splittingEngine.getRequiredTransactions(expenses);

        Report report = new Report();
        report.setEventName(event.getEventName());
        report.setAverage(average);
        report.setTotal(total);
        report.setSummaries(summaries);
        report.setTransactions(transactions);
        report.setExpenses(expenses);

        return report;
    }
}
