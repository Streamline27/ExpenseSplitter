package app.core.services;

import app.core.domain.Report;

/**
 * Created by Vladislav on 6/1/2016.
 */
public interface EventReportingService {
    Report getReport(Long id);
}
