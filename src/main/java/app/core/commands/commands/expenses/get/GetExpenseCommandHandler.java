package app.core.commands.commands.expenses.get;

import app.core.commands.DomainCommandHandler;
import app.core.database.ExpenseDAO;
import app.core.domain.Expense;
import app.dto.ExpenseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static app.core.services.Converter.*;

/**
 * Created by Vladislav on 6/5/2016.
 */

@Component
public class GetExpenseCommandHandler implements DomainCommandHandler<GetExpenseCommand, GetExpenseResult> {

    @Autowired private ExpenseDAO expenseDAO;

    @Override
    public GetExpenseResult execute(GetExpenseCommand command) {

        Expense expense = expenseDAO.findOne(command.getExpenseId());
        ExpenseDTO expenseDTO = convert(expense, TO_EXPENSE_DTO);

        return new GetExpenseResult(expenseDTO);
    }

    @Override
    public Class getCommandType() {
        return GetExpenseCommand.class;
    }
}
