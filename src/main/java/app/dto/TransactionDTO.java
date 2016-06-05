package app.dto;

/**
 * Created by Vladislav on 6/5/2016.
 */
public class TransactionDTO {

    private String debtor;
    private String creditor;
    private Double amount;

    public TransactionDTO(String debtor, String creditor, Double amount) {
        this.debtor = debtor;
        this.creditor = creditor;
        this.amount = amount;
    }

    public TransactionDTO() {
    }

    public String getDebtor() {
        return debtor;
    }

    public void setDebtor(String debtor) {
        this.debtor = debtor;
    }

    public String getCreditor() {
        return creditor;
    }

    public void setCreditor(String creditor) {
        this.creditor = creditor;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
