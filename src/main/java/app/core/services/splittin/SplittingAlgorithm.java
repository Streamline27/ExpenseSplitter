package app.core.services.splittin;

import app.core.domain.Reckoning;
import app.core.domain.Transaction;

import java.util.*;

/**
 * Created by Vladislav on 6/1/2016.
 */
public class SplittingAlgorithm {

    private Comparator<Reckoning> DESCENDING_COMPARATOR = (r1, r2) -> r2.getAmount().intValue()-r1.getAmount().intValue();

    private List<Reckoning> debts;
    private List<Reckoning> credits;
    private List<Transaction> transactions;

    public SplittingAlgorithm(List<Reckoning> debts, List<Reckoning> credits) {
        this.debts = debts;
        this.credits = credits;
    }

    public List<Transaction> execute(){
        transactions = new ArrayList<>();

        while (!debts.isEmpty() && !credits.isEmpty()){
            Collections.sort(credits, DESCENDING_COMPARATOR);
            Collections.sort(debts,   DESCENDING_COMPARATOR);

            if      (debtsContainAmountEqualToMaxCreditAmount()) balanceMaxCreditWithEqualDebt();
            else if (creditsContainAmountEqualToMaxDebtAmount()) balanceMaxDebtWithEqualCredit();
            else     transferFromMaxCreditorToMaxDebtor();
        }

        return transactions;
    }

    /*
        Algorithm condition evaluation methods
    */
    private boolean creditsContainAmountEqualToMaxDebtAmount() {
        return containsReckoningWithSameAmount(credits, debts.get(0));
    }

    private boolean debtsContainAmountEqualToMaxCreditAmount() {
        return containsReckoningWithSameAmount(debts, credits.get(0));
    }

    private boolean containsReckoningWithSameAmount(List<Reckoning> reckoningList, Reckoning comparedReckoning){
        return  reckoningList.stream().anyMatch(r -> r.amountEquals(comparedReckoning));
    }

    /*
        First stage
    */
    private void balanceMaxDebtWithEqualCredit() {

        Reckoning maxDebt = debts.get(0);
        int equalIndex = getIndexWhereAmountEqual(maxDebt, credits);

        String debtor = maxDebt.getPerson();
        String creditor = credits.get(equalIndex).getPerson();
        Transaction transaction = new Transaction(debtor, creditor, maxDebt.getAmount());

        transactions.add(transaction);

        debts.remove(0);
        credits.remove(equalIndex);
    }

    private void balanceMaxCreditWithEqualDebt() {

        Reckoning maxCredit = credits.get(0);
        int equalIndex = getIndexWhereAmountEqual(maxCredit, debts);

        String debtor = debts.get(equalIndex).getPerson();
        String creditor = maxCredit.getPerson();
        Transaction transaction = new Transaction(debtor, creditor, maxCredit.getAmount());

        transactions.add(transaction);

        credits.remove(0);
        debts.remove(equalIndex);
    }

    private int getIndexWhereAmountEqual(Reckoning maxReckoning, List<Reckoning> searchList) {
        for (int i = 0; i < searchList.size(); i++) {
            if (searchList.get(i).amountEquals(maxReckoning)) return i;
        }
        return -1;
    }

    /*
        Second stage
    */
    private void transferFromMaxCreditorToMaxDebtor() {
        Reckoning maxDebt = debts.get(0);
        Reckoning maxCredit = credits.get(0);

        Double unbalancedAmount = Math.abs(maxDebt.getAmount() - maxCredit.getAmount());
        Double transferAmount;

        if (maxDebt.getAmount() > maxCredit.getAmount()){
            maxDebt.setAmount(unbalancedAmount);
            credits.remove(0);
            transferAmount = maxCredit.getAmount();
        }
        else{
            maxCredit.setAmount(unbalancedAmount);
            debts.remove(0);
            transferAmount = maxDebt.getAmount();
        }

        transactions.add(new Transaction(maxDebt.getPerson(), maxCredit.getPerson(), transferAmount));
    }
}
