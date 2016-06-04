package app.core.services;

import app.core.domain.Reckoning;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vladislav on 6/1/2016.
 */

@Component
public class TotalSpendingEvaluatorImpl implements TotalSpendingEvaluator {

    @Override
    public List<Reckoning> getTotals(List<Reckoning> reckonings) {
        Map<String, Reckoning> groupedExpenses = new HashMap<>();

        // Add entries where accumulation takes place
        for (Reckoning reckoning: reckonings){
            groupedExpenses.put(reckoning.getPerson(), new Reckoning(reckoning.getPerson(), 0D));
        }

        // Accumulate in corresponding entries
        for (Reckoning reckoning: reckonings){
            groupedExpenses.get(reckoning.getPerson()).add(reckoning.getAmount());
        }

        return new ArrayList<>(groupedExpenses.values());
    }
}
