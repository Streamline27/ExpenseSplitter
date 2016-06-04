package app.core.splittin;

import app.core.domain.Expense;
import app.core.services.splittin.SplittingEngineImpl;
import app.core.domain.Transaction;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Vladislav on 5/31/2016.
 */

public class SplittingEngineImplTest {
    @Ignore
    @Test
    public void forEmptyExpenseList_ReturnsEmptyTransactionList() throws Exception {
        SplittingEngineImpl splittingEngineImpl = new SplittingEngineImpl();
        List<Expense> emptyList = new ArrayList<>();

        List<Transaction> transactionList = splittingEngineImpl.getRequiredTransactions(emptyList);

        assertTrue(transactionList.isEmpty());
    }

    @Ignore
    @Test
    public void forNull_ReturnsEmptyTransactionList(){
        SplittingEngineImpl splittingEngineImpl = new SplittingEngineImpl();

        List<Transaction> transactionList = splittingEngineImpl.getRequiredTransactions(null);

        assertTrue(transactionList.isEmpty());
    }

    @Ignore
    @Test
    public void forSingleExpense_ReturnsEmptyTransactionList(){
        SplittingEngineImpl splittingEngineImpl = new SplittingEngineImpl();

        List<Expense> listWithSingleExpense = new ArrayList<>();
        listWithSingleExpense.add(new Expense("TestName", "TestService", 10D));

        List<Transaction> transactionList = splittingEngineImpl.getRequiredTransactions(listWithSingleExpense);

        assertTrue(transactionList.isEmpty());
    }

    @Ignore
    @Test
    public void forEquablyDistantExpenses_ShouldKillTwoWithOneTransaction(){
        SplittingEngineImpl splittingEngineImpl = new SplittingEngineImpl();
        Double mean = 300D;
        Double delta = 100D;

        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense("Big", "TestService", mean+delta));
        expenses.add(new Expense("Small", "TestService", mean-delta));

        List<Transaction> transactions = splittingEngineImpl.getRequiredTransactions(expenses);

        assertThat(transactions.size(), is(1));
        assertThat(transactions.get(0).getAmount(), is(100D));

    }

    @Ignore
    @Test
    public void showsTwoTransactions_onSampleData(){
        SplittingEngineImpl splittingEngineImpl = new SplittingEngineImpl();
        List<Expense> expenses = getSampleData();

        List<Transaction> transactions = splittingEngineImpl.getRequiredTransactions(expenses);

        assertThat(transactions.size(), is(2));
    }

    @Ignore
    @Test
    public void run() {
        SplittingEngineImpl splittingEngineImpl = new SplittingEngineImpl();
        List<Expense> expenses = getSampleData2();

        List<Transaction> transactions = splittingEngineImpl.getRequiredTransactions(expenses);
        for (Transaction t : transactions){
            System.out.println("Debtor: "+t.getDebtor()+" Creditor: "+t.getCreditor()+" Amount: "+t.getAmount());
        }

        assertThat(transactions.size(), is(2));
    }



    private List<Expense> getSampleData() {
        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense("Lisa", "Cake", 5D));
        expenses.add(new Expense("Lisa", "Hotle", 120D));
        expenses.add(new Expense("Lisa", "MuseumTicket", 20D));
        expenses.add(new Expense("Hans", "MuseumTicket", 20D));
        expenses.add(new Expense("Hans", "Diner", 34D));
        expenses.add(new Expense("Hans", "MuseumTicket", 20D));
        expenses.add(new Expense("Ivan", "RailwayTicket", 48D));
        expenses.add(new Expense("Ivan", "Supper", 33D));
        return expenses;
    }


    public List<Expense> getSampleData2() {
        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense("Lisa", "Cake", 50D));
        expenses.add(new Expense("Vlad", "Hotle", 300D));
        expenses.add(new Expense("John Snow", "DragonGlass", 900D));
        return expenses;
    }
}