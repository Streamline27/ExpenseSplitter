package app.core.commands.commands.expenses.getall;

import app.core.commands.DomainCommandResult;
import app.dto.ExpenseDTO;

import java.util.List;

/**
 * Created by Vladislav on 6/5/2016.
 */
public class GetEventExpensesResult implements DomainCommandResult {
    private List<ExpenseDTO> expenseDTOList;

    public GetEventExpensesResult(List<ExpenseDTO> expenseDTOList) {
        this.expenseDTOList = expenseDTOList;
    }

    public GetEventExpensesResult() {
    }

    public List<ExpenseDTO> getExpenseDTOList() {
        return expenseDTOList;
    }

    public void setExpenseDTOList(List<ExpenseDTO> expenseDTOList) {
        this.expenseDTOList = expenseDTOList;
    }
}
