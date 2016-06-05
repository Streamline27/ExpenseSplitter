package app.core.commands.commands.expenses.delete;

import app.core.commands.DomainCommandHandler;
import app.core.database.ExpenseDAO;
import app.core.domain.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static app.core.services.Converter.TO_EXPENSE_DTO;
import static app.core.services.Converter.convert;

/**
 * Created by Vladislav on 6/5/2016.
 */

@Component
public class DeleteExpenseHandler implements DomainCommandHandler<DeleteExpenseCommand, DeleteExpenseResult> {

    @Autowired private ExpenseDAO expenseDAO;

    @Override
    public DeleteExpenseResult execute(DeleteExpenseCommand command) {

        Expense expense = expenseDAO.findOne(command.getExpenseId());
        expenseDAO.delete(command.getExpenseId());

        return new DeleteExpenseResult(convert(expense, TO_EXPENSE_DTO));
    }


    @Override
    public Class getCommandType() {
        return DeleteExpenseCommand.class;
    }
}
