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

@Api(tags = "user")
public interface UserControlerSwagger {

    @Operation(operationId = "UserControler", summary = "Used to sign up a user.", description = "Return a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)), description = "Successful"),
            @ApiResponse(responseCode = UserAlreadyExistException.STATUCODE_ERROR, description = UserAlreadyExistException.MESSAGE_ERROR)
    })
    User signUp(@RequestBody UserSignUp user);

    @Operation(operationId = "UserControler", summary = "Used to sign in a user.", description = "Return a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)), description = "Successful"),
            @ApiResponse(responseCode = UserNotFoundException.STATUCODE_ERROR, description = UserNotFoundException.MESSAGE_ERROR)
    })
    User signIn(@RequestBody UserSignIn user);
}
