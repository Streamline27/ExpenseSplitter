package app.core.commands.commands.expenses.create;

import app.core.commands.DomainCommandResult;
import app.dto.ExpenseDTO;

/**
 * Created by Vladislav on 6/5/2016.
 */
public class CreateExpenseResult implements DomainCommandResult {
    private ExpenseDTO expenseDTO;

    public CreateExpenseResult(ExpenseDTO expenseDTO) {
        this.expenseDTO = expenseDTO;
    }

    public ExpenseDTO getExpenseDTO() {
        return expenseDTO;
    }

    public void setExpenseDTO(ExpenseDTO expenseDTO) {
        this.expenseDTO = expenseDTO;
    }
}
