package org.helmo.HolyD.controlers.swagger;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.helmo.HolyD.controlers.exception.*;
import org.helmo.HolyD.models.reponses.Activite;
import org.helmo.HolyD.models.reponses.Vacance;
import org.helmo.HolyD.models.requests.ActiviteAdd;
import org.helmo.HolyD.models.requests.ParticipantAdd;
import org.helmo.HolyD.models.requests.VacanceAdd;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Api(tags = "vacance")
public interface VacanceControlerSwagger {

    @Operation(operationId = "VacanceControler", summary = "Add vacance to a user (Owner).", description = "Return Successful or error")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vacance.class)), description = "Successful"),
            @ApiResponse(responseCode = UserNotFoundException.STATUCODE_ERROR, description = UserNotFoundException.MESSAGE_ERROR),
            @ApiResponse(responseCode = DateTimeIntervalIsNotAIntervalException.STATUCODE_ERROR, description = DateTimeIntervalIsNotAIntervalException.MESSAGE_ERROR),
            @ApiResponse(responseCode = DateTimeIsInPastException.STATUCODE_ERROR, description = DateTimeIsInPastException.MESSAGE_ERROR),
            @ApiResponse(responseCode = UserAlreadyInHolidayException.STATUCODE_ERROR, description = UserAlreadyInHolidayException.MESSAGE_ERROR),
            @ApiResponse(responseCode = DateTimeIntervalIsNotAIntervalException.STATUCODE_ERROR, description = DateTimeIntervalIsNotAIntervalException.MESSAGE_ERROR)
    })
    Vacance addVacance(@Valid @RequestBody VacanceAdd vacanceAdd);

    @Operation(operationId = "VacanceControler", summary = "Add participant to a vacance.", description = "Return Successful or error")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vacance.class)), description = "Successful"),
            @ApiResponse(responseCode = UserNotFoundException.STATUCODE_ERROR, description = UserNotFoundException.MESSAGE_ERROR),
            @ApiResponse(responseCode = VacanceNotFoundException.STATUCODE_ERROR, description = VacanceNotFoundException.MESSAGE_ERROR),
            @ApiResponse(responseCode = UserAlreadyInHolidayException.STATUCODE_ERROR, description = UserAlreadyInHolidayException.MESSAGE_ERROR),
            @ApiResponse(responseCode = UserAlreadyInsideException.STATUCODE_ERROR, description = UserAlreadyInsideException.MESSAGE_ERROR)

    })
    Vacance addParticipant(@Valid @RequestBody ParticipantAdd participantAdd);
}
