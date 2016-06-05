package app.core.commands.commands.expenses.getall;

import app.core.commands.DomainCommandHandler;
import app.core.database.EventDAO;
import app.core.domain.Expense;
import app.dto.ExpenseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static app.core.services.Converter.TO_EXPENSE_DTO;
import static app.core.services.Converter.convert;

/**
 * Created by Vladislav on 6/5/2016.
 */

@Component
public class GetEventExpensesHandler implements DomainCommandHandler<GetEventExpensesCommand, GetEventExpensesResult> {

    @Autowired private EventDAO eventDAO;

    @Override
    public GetEventExpensesResult execute(GetEventExpensesCommand command) {

        Long eventId = command.getEventId();
        List<Expense> expenses = eventDAO.findOne(eventId).getExpenses();

        List<ExpenseDTO> expenseDTOs = convert(expenses, TO_EXPENSE_DTO);
        return new GetEventExpensesResult(expenseDTOs);
    }


    @Override
    public Class getCommandType() {
        return GetEventExpensesCommand.class;
    }

}
