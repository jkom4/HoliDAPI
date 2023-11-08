package org.helmo.HolyD.controlers.swagger;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.helmo.HolyD.models.reponses.Vacance;
import org.helmo.HolyD.models.requests.VacanceAdd;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Api(tags = "vacance")
public interface VacanceControlerSwagger {

    @Operation(operationId = "VacanceControler", summary = "Add vacance to a user (Owner).", description = "Return Successful or error")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vacance.class)), description = "Successful"),
            @ApiResponse(responseCode = "400", description = "Fail to save")
    })
    Vacance addVacance(@RequestBody VacanceAdd vacanceAdd);
}
