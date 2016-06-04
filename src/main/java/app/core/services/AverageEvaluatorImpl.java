package app.core.services;

import app.core.domain.Reckoning;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Vladislav on 6/1/2016.
 */

@Component
public class AverageEvaluatorImpl implements AverageEvaluator{

    @Override
    public double getAverage(List<Reckoning> reckonings) {
        return reckonings.stream().mapToDouble(Reckoning::getAmount).average().orElse(0);
    }
}
