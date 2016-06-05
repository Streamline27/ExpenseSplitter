package app.core.commands.commands.expenses.delete;

import app.core.commands.DomainCommand;

/**
 * Created by Vladislav on 6/5/2016.
 */
public class DeleteExpenseCommand implements DomainCommand<DeleteExpenseResult> {
    private Long expenseId;

    public DeleteExpenseCommand() {
    }

    public DeleteExpenseCommand(Long expenseId) {
        this.expenseId = expenseId;
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }
}
