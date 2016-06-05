package app.core.commands.commands.expenses.get;

import app.core.commands.DomainCommand;

/**
 * Created by Vladislav on 6/5/2016.
 */
public class GetExpenseCommand implements DomainCommand<GetExpenseResult> {
    private Long expenseId;

    public GetExpenseCommand() {
    }

    public GetExpenseCommand(Long expenseId) {
        this.expenseId = expenseId;
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }
}
