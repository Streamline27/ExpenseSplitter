package app.core.commands.commands.expenses.get;

import app.core.commands.DomainCommandResult;
import app.dto.ExpenseDTO;

/**
 * Created by Vladislav on 6/5/2016.
 */
public class GetExpenseResult implements DomainCommandResult {

    private ExpenseDTO expenseDTO;

    public GetExpenseResult() {
    }

    public GetExpenseResult(ExpenseDTO expenseDTO) {
        this.expenseDTO = expenseDTO;
    }

    public ExpenseDTO getExpenseDTO() {
        return expenseDTO;
    }

    public void setExpenseDTO(ExpenseDTO expenseDTO) {
        this.expenseDTO = expenseDTO;
    }
}
