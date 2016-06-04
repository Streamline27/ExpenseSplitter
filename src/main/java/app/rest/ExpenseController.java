package app.rest;

import app.core.database.EventDAO;
import app.core.database.ExpenseDAO;
import app.core.domain.Event;
import app.core.domain.Expense;
import app.core.domain.Report;
import app.core.services.ExpenseCommittingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Vladislav on 6/1/2016.
 */

@RestController
@RequestMapping("/api/user/{username}/event/{eventId}/expense")
public class ExpenseController {

    @Autowired private ExpenseCommittingService expenseCommittingService;
    @Autowired private EventDAO eventDAO;
    @Autowired private ExpenseDAO expenseDAO;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Expense> post(@RequestBody Expense expense,
                                          @PathVariable Long eventId){

        Event event = eventDAO.findOne(eventId);
        expenseCommittingService.commitExpense(expense, event);
        expenseDAO.save(expense);

        return new ResponseEntity<Expense>(expense, HttpStatus.OK);
    }


    @RequestMapping(path = "/{expenseId}" ,method = RequestMethod.GET)
    public ResponseEntity<Expense> get(@PathVariable Long expenseId){

        Expense expense = expenseDAO.findOne(expenseId);
        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    @RequestMapping(path = "/{expenseId}" ,method = RequestMethod.DELETE)
    public HttpStatus delete(@PathVariable Long expenseId){
        expenseDAO.delete(expenseId);
        return HttpStatus.OK;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Expense>> getAll(@PathVariable Long eventId){

        Iterable<Expense> expense = eventDAO.findOne(eventId).getExpenses();
        return new ResponseEntity<>(expense, HttpStatus.OK);
    }



}
