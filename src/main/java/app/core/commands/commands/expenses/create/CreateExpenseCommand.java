package app.core.commands.commands.expenses.create;

import app.core.commands.DomainCommand;
import app.dto.ExpenseDTO;

/**
 * Created by Vladislav on 6/5/2016.
 */
public class CreateExpenseCommand implements DomainCommand<CreateExpenseResult>{
    private Long eventId;
    private ExpenseDTO expenseDTO;

    public CreateExpenseCommand(ExpenseDTO expenseDTO, Long eventId) {
        this.expenseDTO = expenseDTO;
        this.eventId = eventId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public CreateExpenseCommand() {
    }

    public ExpenseDTO getExpenseDTO() {
        return expenseDTO;
    }

    public void setExpenseDTO(ExpenseDTO expenseDTO) {
        this.expenseDTO = expenseDTO;
    }
}
