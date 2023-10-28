package org.helmo.HolyD.controlers.swagger;


import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.helmo.HolyD.models.requests.UserSignIn;
import org.helmo.HolyD.models.requests.UserSignUp;
import org.helmo.HolyD.repository.DTO.UserDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

@Api(tags = "user")
public interface UserControlerSwagger {

    @Operation(operationId = "UserControler", summary = "Used to sign up a user.", description = "Return a user")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)))
    ResponseEntity<UserDTO> putUser(@RequestBody UserSignUp user);

    @Operation(operationId = "UserControler", summary = "Used to sign in a user.", description = "Return a user")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)))
    UserDTO postUser(@RequestBody UserSignIn user);
}
