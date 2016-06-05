package app.rest;

import app.core.commands.CommandExecutor;
import app.core.commands.commands.report.GetEventReportCommand;
import app.core.commands.commands.report.GetEventReportResult;
import app.dto.ReportDTO;
import app.security.AuthenticationService;
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

    @Autowired private CommandExecutor executor;
    @Autowired private AuthenticationService authenticationService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ReportDTO> get(@PathVariable Long eventId,
                                         @PathVariable String username){

        if (!authenticationService.isLoggedIn(username))
            return new ResponseEntity<>(new ReportDTO(), HttpStatus.BAD_REQUEST);


        GetEventReportCommand command = new GetEventReportCommand(eventId);
        GetEventReportResult result = executor.execute(command);

        ReportDTO reportDTO = getReportDTO(result);

        return new ResponseEntity<>(reportDTO, HttpStatus.OK);
    }


    private ReportDTO getReportDTO(GetEventReportResult result) {
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setEventName(result.getEventName());
        reportDTO.setAverage(result.getAverage());
        reportDTO.setTotal(result.getTotal());
        reportDTO.setExpenses(result.getExpenses());
        reportDTO.setSummaries(result.getSummaries());
        reportDTO.setTransactions(result.getTransactions());
        return reportDTO;
    }
}
