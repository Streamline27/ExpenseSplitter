package app.core.services;

import app.core.domain.Reckoning;

import java.util.List;

/**
 * Created by Vladislav on 6/1/2016.
 */
public interface AverageCalculator {
    double getAverage(List<Reckoning> reckonings);
}
