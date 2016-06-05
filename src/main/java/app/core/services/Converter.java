package app.core.services;

import app.core.domain.*;
import app.dto.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Vladislav on 6/5/2016.
 */
public class Converter {

    public static <T, R> R convert(T entry, Function<T, R> transformation){
        return transformation.apply(entry);
    }

    public static <T, R> List<R> convert(List<T> entries, Function<T, R> transformation){
        return entries.stream().map(transformation::apply).collect(Collectors.toList());
    }

    /*
            Transformation functions
     */


    /* Domain to DTO */

    public final static Function<Expense, ExpenseDTO> TO_EXPENSE_DTO =
            expense ->  new ExpenseDTO(expense.getId(), expense.getName(), expense.getService(), expense.getAmount());


    public final static Function<Reckoning, ReckoningDTO> TO_RECKONING_DTO =
            reckoning -> new ReckoningDTO(reckoning.getPerson(), reckoning.getAmount());


    public final static Function<Transaction, TransactionDTO> TO_TRANSACTION_DTO =
            transaction -> new TransactionDTO(transaction.getDebtor(), transaction.getCreditor(), transaction.getAmount());


    public final static Function<Event, EventDTO> TO_EVENT_DTO =
            event -> new EventDTO(event.getEventId(), event.getEventName());


    public final static Function<User, UserInfoDTO> TO_USER_INFO_DTO =
            user -> new UserInfoDTO(user.getUsername(), user.getLastName(), user.getPassword());

    /* DTO to Domain */

    public final static Function<ExpenseDTO, Expense> FROM_EXPENSE_DTO =
            expenseDTO -> new Expense(expenseDTO.getName(), expenseDTO.getService(), expenseDTO.getAmount());


    public final static Function<ReckoningDTO, Reckoning> FROM_RECKONING_DTO =
            reckoningDTO -> new Reckoning(reckoningDTO.getPerson(), reckoningDTO.getAmount());


    public final static Function<TransactionDTO, Transaction> FROM_TRANSACTION_DTO =
            transactionDTO -> new Transaction(transactionDTO.getDebtor(), transactionDTO.getCreditor(), transactionDTO.getAmount());


    public final static Function<EventDTO, Event> FROM_EVENT_DTO =
            eventDTO -> new Event(eventDTO.getEventName());


    public final static Function<UserDTO, User> FROM_USER_DTO =
            userDTO -> new User(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getUsername(), userDTO.getPassword());

    /* Other */

    public final static Function<Expense, Reckoning> FROM_EXPENSE_TO_RECKONING =
            expense -> new Reckoning(expense.getName(), expense.getAmount());

}
