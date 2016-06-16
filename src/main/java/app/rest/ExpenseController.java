package app.rest;

import app.core.commands.CommandExecutor;
import app.core.commands.commands.expenses.create.CreateExpenseCommand;
import app.core.commands.commands.expenses.create.CreateExpenseResult;
import app.core.commands.commands.expenses.delete.DeleteExpenseCommand;
import app.core.commands.commands.expenses.delete.DeleteExpenseResult;
import app.core.commands.commands.expenses.get.GetExpenseCommand;
import app.core.commands.commands.expenses.get.GetExpenseResult;
import app.core.commands.commands.expenses.getall.GetEventExpensesCommand;
import app.core.commands.commands.expenses.getall.GetEventExpensesResult;
import app.dto.ExpenseDTO;
import app.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladislav on 6/1/2016.
 */

@RestController
@RequestMapping("/api/user/{username}/event/{eventId}/expense")
public class ExpenseController {

    @Autowired private AuthenticationService authenticationService;
    @Autowired private CommandExecutor executor;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ExpenseDTO> post(@RequestBody ExpenseDTO expense,
                                           @PathVariable Long eventId,
                                           @PathVariable String username){

        if (!authenticationService.isLoggedIn(username)) return getBadRequestResult();
        if (isNotValidInput(expense)) return getBadRequestResult();


        CreateExpenseCommand command = new CreateExpenseCommand(expense, eventId);
        CreateExpenseResult result = executor.execute(command);

        return new ResponseEntity<>(result.getExpenseDTO(), HttpStatus.OK);

    }



    @RequestMapping(path = "/{expenseId}" ,method = RequestMethod.GET)
    public ResponseEntity<ExpenseDTO> get(@PathVariable Long expenseId,
                                          @PathVariable String username){

        if (!authenticationService.isLoggedIn(username)) return getBadRequestResult();

        GetExpenseCommand command = new GetExpenseCommand(expenseId);
        GetExpenseResult result = executor.execute(command);

        return new ResponseEntity<>(result.getExpenseDTO(), HttpStatus.OK);

    }



    @RequestMapping(path = "/{expenseId}" ,method = RequestMethod.DELETE)
    public ResponseEntity<ExpenseDTO> delete(@PathVariable Long expenseId,
                                             @PathVariable String username){

        if (!authenticationService.isLoggedIn(username)) return getBadRequestResult();

        DeleteExpenseCommand command = new DeleteExpenseCommand(expenseId);
        DeleteExpenseResult result = executor.execute(command);

        return new ResponseEntity<>(result.getExpenseDTO(), HttpStatus.OK);

    }



    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ExpenseDTO>> getAll(@PathVariable Long eventId,
                                                   @PathVariable String username){

        if (!authenticationService.isLoggedIn(username))
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

        GetEventExpensesCommand command = new GetEventExpensesCommand(eventId);
        GetEventExpensesResult result = executor.execute(command);

        return new ResponseEntity<>(result.getExpenseDTOList(), HttpStatus.OK);

    }


    private ResponseEntity<ExpenseDTO> getBadRequestResult() {
        return new ResponseEntity<>(new ExpenseDTO(), HttpStatus.BAD_REQUEST);
    }

    private boolean isNotValidInput(@RequestBody ExpenseDTO expense) {
        return expense.getName()==null ||
                expense.getService()==null ||
                expense.getAmount()==null ||
                expense.getName().trim().equals("") ||
                expense.getService().trim().equals("");
    }



}
