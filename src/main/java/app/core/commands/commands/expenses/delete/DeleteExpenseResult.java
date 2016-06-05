package app.core.commands.commands.expenses.delete;

import app.core.commands.DomainCommandResult;
import app.dto.ExpenseDTO;

/**
 * Created by Vladislav on 6/5/2016.
 */
public class DeleteExpenseResult implements DomainCommandResult {
    private ExpenseDTO expenseDTO;

    public DeleteExpenseResult() {
    }

    public DeleteExpenseResult(ExpenseDTO expenseDTO) {
        this.expenseDTO = expenseDTO;
    }

    public ExpenseDTO getExpenseDTO() {
        return expenseDTO;
    }

    public void setExpenseDTO(ExpenseDTO expenseDTO) {
        this.expenseDTO = expenseDTO;
    }
}
