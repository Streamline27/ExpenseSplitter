package app.rest;

import app.core.domain.Expense;
import app.core.domain.Report;
import app.core.services.EventReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Vladislav on 6/1/2016.
 */

@RestController
@RequestMapping("/api/user/{username}/event/{eventId}/report")
public class ReportController {

    @Autowired private EventReportingService eventReportingService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Report> get(@PathVariable Long eventId){

        Report report = eventReportingService.getReport(eventId);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }
}
