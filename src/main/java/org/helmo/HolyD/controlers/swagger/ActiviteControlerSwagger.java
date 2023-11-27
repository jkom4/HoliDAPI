package org.helmo.HolyD.controlers.swagger;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.helmo.HolyD.controlers.exception.ActiviteNotFoundException;
import org.helmo.HolyD.controlers.exception.UserNotFoundException;
import org.helmo.HolyD.controlers.exception.VacanceNotFoundException;
import org.helmo.HolyD.models.reponses.Activite;
import org.helmo.HolyD.models.requests.ParticipantAdd;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Api(tags = "activite")
public interface ActiviteControlerSwagger {
    @Operation(operationId = "ActiviteControler", summary = "Add participant to an activite.", description = "Return Successful or error")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Activite.class)), description = "Successful"),
            @ApiResponse(responseCode = UserNotFoundException.STATUCODE_ERROR, description = UserNotFoundException.MESSAGE_ERROR),
            @ApiResponse(responseCode = ActiviteNotFoundException.STATUCODE_ERROR, description = ActiviteNotFoundException.MESSAGE_ERROR),
            @ApiResponse(responseCode = VacanceNotFoundException.STATUCODE_ERROR, description = VacanceNotFoundException.MESSAGE_ERROR)

    })
    Activite addParticipant(@Valid @RequestBody ParticipantAdd participantAdd);
}
