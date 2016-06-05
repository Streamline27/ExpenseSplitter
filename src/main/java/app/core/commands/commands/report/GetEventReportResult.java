package app.core.commands.commands.report;

import app.core.commands.DomainCommandResult;
import app.dto.ExpenseDTO;
import app.dto.ReckoningDTO;
import app.dto.TransactionDTO;

import java.util.List;

/**
 * Created by Vladislav on 6/5/2016.
 */
public class GetEventReportResult implements DomainCommandResult {

    private String eventName;

    private Double average;
    private Double total;

    private List<ExpenseDTO> expenses;
    private List<ReckoningDTO> summaries;
    private List<TransactionDTO> transactions;

    public GetEventReportResult() {
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Double getTotal() {
        return total;
    }

    public List<ReckoningDTO> getSummaries() {
        return summaries;
    }

    public void setSummaries(List<ReckoningDTO> summaries) {
        this.summaries = summaries;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<ExpenseDTO> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<ExpenseDTO> expenses) {
        this.expenses = expenses;
    }


    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }
}
