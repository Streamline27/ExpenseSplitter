package app.dto;

/**
 * Created by Vladislav on 6/5/2016.
 */
public class ExpenseDTO {
    private Long expenseId;

    private String name;
    private String service;
    private Double amount;

    public Long getExpenseId() {
        return expenseId;
    }

    public ExpenseDTO() {
    }

    public ExpenseDTO(String name, String service, Double amount) {
        this.name = name;
        this.service = service;
        this.amount = amount;
    }

    public ExpenseDTO(Long expenseId, String name, String service, Double amount) {
        this.expenseId = expenseId;
        this.name = name;
        this.service = service;
        this.amount = amount;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
