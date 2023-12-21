package org.helmo.HolyD.controlers.swagger;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.helmo.HolyD.controlers.exception.UserAlreadyExistException;
import org.helmo.HolyD.controlers.exception.UserNotFoundException;
import org.helmo.HolyD.models.reponses.User;
import org.helmo.HolyD.models.requests.UserSignIn;
import org.helmo.HolyD.models.requests.UserSignUp;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Api(tags = "sse")
public interface SseSwaggerControler {
    @Operation(operationId = "SseControler", summary = "Used to subscribe to the sse flux.", description = "Return a SseEmitter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SseEmitter.class)), description = "Successful"),
            @ApiResponse(responseCode = UserAlreadyExistException.STATUCODE_ERROR, description = UserAlreadyExistException.MESSAGE_ERROR)
    })
    SseEmitter subscribe();

    @Operation(operationId = "SseControler", summary = "Used to unsubscribe to the sse flux.", description = "Return void")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = UserNotFoundException.STATUCODE_ERROR, description = UserNotFoundException.MESSAGE_ERROR)
    })
    void unsubscribe();
}
