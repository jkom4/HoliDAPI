package org.helmo.HolyD.controlers.swagger;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.helmo.HolyD.controlers.exception.*;
import org.helmo.HolyD.models.reponses.Activite;
import org.helmo.HolyD.models.requests.ActiviteAdd;
import org.helmo.HolyD.models.requests.OffsetDateTimeChange;
import org.helmo.HolyD.models.requests.ParticipantAdd;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Api(tags = "activite")
public interface ActiviteControlerSwagger {

    @Operation(operationId = "ActiviteControler", summary = "Add activite to a vacance.", description = "Return Successful or error")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Activite.class)), description = "Successful"),
            @ApiResponse(responseCode = UserNotFoundException.STATUCODE_ERROR, description = UserNotFoundException.MESSAGE_ERROR),
            @ApiResponse(responseCode = VacanceNotFoundException.STATUCODE_ERROR, description = VacanceNotFoundException.MESSAGE_ERROR),
            @ApiResponse(responseCode = DateTimeIntervalIsNotAIntervalException.STATUCODE_ERROR, description = DateTimeIntervalIsNotAIntervalException.MESSAGE_ERROR),
            @ApiResponse(responseCode = DateTimeIsInPastException.STATUCODE_ERROR, description = DateTimeIsInPastException.MESSAGE_ERROR),
            @ApiResponse(responseCode = IntervalActiviteIsNotInIntervalVacanceException.STATUCODE_ERROR, description = IntervalActiviteIsNotInIntervalVacanceException.MESSAGE_ERROR),
            @ApiResponse(responseCode = UserAlreadyInActiviteException.STATUCODE_ERROR, description = UserAlreadyInActiviteException.MESSAGE_ERROR)

    })
    Activite add(@Valid @RequestBody ActiviteAdd activiteAdd);

    @Operation(operationId = "ActiviteControler", summary = "Add participant to an activite.", description = "Return Successful or error")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Activite.class)), description = "Successful"),
            @ApiResponse(responseCode = UserNotFoundException.STATUCODE_ERROR, description = UserNotFoundException.MESSAGE_ERROR),
            @ApiResponse(responseCode = ActiviteNotFoundException.STATUCODE_ERROR, description = ActiviteNotFoundException.MESSAGE_ERROR),
            @ApiResponse(responseCode = VacanceNotFoundException.STATUCODE_ERROR, description = VacanceNotFoundException.MESSAGE_ERROR),
            @ApiResponse(responseCode = DateTimeIntervalIsNotAIntervalException.STATUCODE_ERROR, description = DateTimeIntervalIsNotAIntervalException.MESSAGE_ERROR),
            @ApiResponse(responseCode = DateTimeIsInPastException.STATUCODE_ERROR, description = DateTimeIsInPastException.MESSAGE_ERROR),
            @ApiResponse(responseCode = IntervalActiviteIsNotInIntervalVacanceException.STATUCODE_ERROR, description = IntervalActiviteIsNotInIntervalVacanceException.MESSAGE_ERROR),
            @ApiResponse(responseCode = UserAlreadyInActiviteException.STATUCODE_ERROR, description = UserAlreadyInActiviteException.MESSAGE_ERROR)


    })
    Activite addParticipant(@Valid @RequestBody ParticipantAdd participantAdd);

    @Operation(operationId = "ActiviteControler", summary = "Add participant to an activite.", description = "Return Successful or error")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Activite.class)), description = "Successful"),
            @ApiResponse(responseCode = UserNotFoundException.STATUCODE_ERROR, description = UserNotFoundException.MESSAGE_ERROR),
            @ApiResponse(responseCode = ActiviteNotFoundException.STATUCODE_ERROR, description = ActiviteNotFoundException.MESSAGE_ERROR),
            @ApiResponse(responseCode = VacanceNotFoundException.STATUCODE_ERROR, description = VacanceNotFoundException.MESSAGE_ERROR),
            @ApiResponse(responseCode = DateTimeIntervalIsNotAIntervalException.STATUCODE_ERROR, description = DateTimeIntervalIsNotAIntervalException.MESSAGE_ERROR),
            @ApiResponse(responseCode = DateTimeIsInPastException.STATUCODE_ERROR, description = DateTimeIsInPastException.MESSAGE_ERROR),
            @ApiResponse(responseCode = IntervalActiviteIsNotInIntervalVacanceException.STATUCODE_ERROR, description = IntervalActiviteIsNotInIntervalVacanceException.MESSAGE_ERROR),
            @ApiResponse(responseCode = UserAlreadyInActiviteException.STATUCODE_ERROR, description = UserAlreadyInActiviteException.MESSAGE_ERROR)

    })
    Activite changeDateTimeOfActivite(@Valid @RequestBody OffsetDateTimeChange offsetDateTimeChange);
}
