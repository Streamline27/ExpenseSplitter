package app.core.commands.commands.report;

import app.core.commands.DomainCommandHandler;
import app.core.database.EventDAO;
import app.core.domain.*;
import app.core.services.AverageEvaluator;
import app.core.services.TotalSpendingEvaluator;
import app.core.services.splittin.SplittingEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static app.core.services.Converter.*;

/**
 * Created by Vladislav on 6/5/2016.
 */

@Component
public class GetEventReportCommandHandler implements DomainCommandHandler<GetEventReportCommand, GetEventReportResult> {

    @Autowired private EventDAO eventDAO;
    @Autowired private TotalSpendingEvaluator totalSpendingEvaluator;
    @Autowired private AverageEvaluator averageEvaluator;
    @Autowired private SplittingEngine splittingEngine;


    @Override
    public GetEventReportResult execute(GetEventReportCommand command) {

        Event event = getEvent(command);
        if (event==null) return new GetEventReportResult();

        /* Compute required values */
        List<Expense> expenses                = event.getExpenses();
        List<Reckoning> reckonings            = convert(expenses, FROM_EXPENSE_TO_RECKONING);
        List<Reckoning> totalSpendingByPeople = totalSpendingEvaluator.getSpendingByPeople(reckonings);

        Double average = averageEvaluator.getAverage(totalSpendingByPeople);
        Double total   = getOverallSum(totalSpendingByPeople);

        /* Run splitting algorithm */
        List<Transaction> transactions = splittingEngine.getRequiredTransactions(totalSpendingByPeople, average);

        return getGetEventReportResult(event.getEventName(),
                                       expenses,
                                       totalSpendingByPeople,
                                       average,
                                       total,
                                       transactions);
    }


    @Override
    public Class getCommandType() {
        return GetEventReportCommand.class;
    }


    /*
        Private helper methods
     */

    private GetEventReportResult getGetEventReportResult(String eventName,
                                                         List<Expense> expenses,
                                                         List<Reckoning> summaries,
                                                         Double average,
                                                         Double total,
                                                         List<Transaction> transactions) {

        GetEventReportResult reportResult = new GetEventReportResult();
        reportResult.setEventName(eventName);
        reportResult.setAverage(average);
        reportResult.setTotal(total);
        reportResult.setExpenses(convert(expenses, TO_EXPENSE_DTO));
        reportResult.setSummaries(convert(summaries, TO_RECKONING_DTO));
        reportResult.setTransactions(convert(transactions, TO_TRANSACTION_DTO));
        return reportResult;
    }

    private double getOverallSum(List<Reckoning> summaries) {
        return summaries.stream().mapToDouble(Reckoning::getAmount).sum();
    }

    private Event getEvent(GetEventReportCommand command) {
        return eventDAO.findOne(command.getEventId());
    }



}
