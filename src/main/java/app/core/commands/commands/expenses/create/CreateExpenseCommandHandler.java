package app.core.commands.commands.expenses.create;

import app.core.commands.DomainCommandHandler;
import app.core.database.EventDAO;
import app.core.database.ExpenseDAO;
import app.core.domain.Event;
import app.core.domain.Expense;
import app.dto.ExpenseDTO;
import app.core.services.ExpenseCommittingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static app.core.services.Converter.FROM_EXPENSE_DTO;
import static app.core.services.Converter.TO_EXPENSE_DTO;
import static app.core.services.Converter.convert;

/**
 * Created by Vladislav on 6/5/2016.
 */
@Component
public class CreateExpenseCommandHandler implements DomainCommandHandler<CreateExpenseCommand, CreateExpenseResult> {

    @Autowired private ExpenseCommittingService expenseCommittingService;
    @Autowired private EventDAO eventDAO;
    @Autowired private ExpenseDAO expenseDAO;

    @Override
    public CreateExpenseResult execute(CreateExpenseCommand command) {

        ExpenseDTO expenseDTO = formatFields(command.getExpenseDTO());
        Long eventId          = command.getEventId();

        Event event = eventDAO.findOne(eventId);
        Expense expense = convert(expenseDTO, FROM_EXPENSE_DTO);

        expense = saveEventExpense(expense, event);

        return new CreateExpenseResult(convert(expense, TO_EXPENSE_DTO));
    }

    @Override
    public Class getCommandType() {
        return CreateExpenseCommand.class;
    }


    private Expense saveEventExpense(Expense expense, Event event) {
        expenseCommittingService.commitExpense(expense, event);
        expense = expenseDAO.save(expense);
        return expense;
    }

    private ExpenseDTO formatFields(ExpenseDTO expenseDTO) {
        return new ExpenseDTO(expenseDTO.getName().trim(), expenseDTO.getService(), expenseDTO.getAmount());
    }
}
