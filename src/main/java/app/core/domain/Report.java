package app.core.domain;

import java.util.List;

/**
 * Created by Vladislav on 6/1/2016.
 */
public class Report {
    String eventName;

    Double average;
    Double total;

    List<Expense> expenses;
    List<Reckoning> summaries;
    List<Transaction> transactions;

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public List<Reckoning> getSummaries() {
        return summaries;
    }

    public void setSummaries(List<Reckoning> summaries) {
        this.summaries = summaries;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
