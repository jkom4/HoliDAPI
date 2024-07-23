package org.helmo.HolyD.controlers.swagger;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.helmo.HolyD.controlers.exception.*;
import org.helmo.HolyD.models.reponses.Vacance;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Api(tags = "document")
public interface DocumentControlerSwagger {

    @Operation(operationId = "DocumentControler", summary = "Upload a file", description = "Upload file")
    @Parameter(description = "File to be uploaded", content = @Content( mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
    @ApiResponses(value = {
            @ApiResponse(description = "File uploaded successfully", responseCode = "200"),
            @ApiResponse(responseCode = StorageException.STATUCODE_ERROR, description = StorageException.MESSAGE_ERROR)
    })
    public void fileVacanceUpload(@Valid @Min(1) @PathVariable("idVacance") Long idVacance, @Validated @RequestPart("file") MultipartFile file);

    @Operation(operationId = "DocumentControler", summary = "Upload a file", description = "Upload file")
    @Parameter(description = "File to be uploaded", content = @Content( mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
    @ApiResponses(value = {
            @ApiResponse(description = "File uploaded successfully", responseCode = "200"),
            @ApiResponse(responseCode = StorageException.STATUCODE_ERROR, description = StorageException.MESSAGE_ERROR)
    })
    public void fileActivityUpload(@Valid @Min(1) @PathVariable("idVacance") Long idVacance, @Valid @Min(1) @PathVariable("idActivity") Long idActivity, @Validated @RequestPart("file") MultipartFile file);

    @Operation(operationId = "DocumentControler", summary = "List all files", description = "list all files fo a given holiday")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class)), description = "Successful"),
            @ApiResponse(responseCode = VacanceNotFoundException.STATUCODE_ERROR, description = VacanceNotFoundException.MESSAGE_ERROR)

    })
    public List<String> listVacanceFilenameDocuments(@Valid @Min(1) @PathVariable("idVacance") Long idVacance);

    @Operation(operationId = "DocumentControler", summary = "List all files", description = "list all files fo a given holiday and activity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class)), description = "Successful"),
            @ApiResponse(responseCode = VacanceNotFoundException.STATUCODE_ERROR, description = VacanceNotFoundException.MESSAGE_ERROR),
            @ApiResponse(responseCode = ActiviteNotFoundException.STATUCODE_ERROR, description = ActiviteNotFoundException.MESSAGE_ERROR)

    })
    public List<String> listActivityFilenameDocuments(@Valid @Min(1) @PathVariable("idVacance") Long idVacance, @Valid @Min(1) @PathVariable("idActivity") Long idActivity);

    @Operation(operationId = "DocumentControler", summary = "Get a given file in holiday files", description = "Get a given file in holiday files")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class)), description = "Successful"),
            @ApiResponse(responseCode = VacanceNotFoundException.STATUCODE_ERROR, description = VacanceNotFoundException.MESSAGE_ERROR),
            @ApiResponse(responseCode = DocumentNotFoundException.STATUCODE_ERROR, description = DocumentNotFoundException.MESSAGE_ERROR),
            @ApiResponse(responseCode = StorageException.STATUCODE_ERROR, description = StorageException.MESSAGE_ERROR),

    })
    public Resource getVacanceDocument(@Valid @Min(1) @PathVariable("idVacance") Long idVacance, @Valid @Min(1) @PathVariable("filename") String filename, HttpServletResponse response);

    @Operation(operationId = "DocumentControler", summary = "Get a given file in activity files", description = "Get a given file in activity files")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class)), description = "Successful"),
            @ApiResponse(responseCode = VacanceNotFoundException.STATUCODE_ERROR, description = VacanceNotFoundException.MESSAGE_ERROR),

            @ApiResponse(responseCode = DocumentNotFoundException.STATUCODE_ERROR, description = DocumentNotFoundException.MESSAGE_ERROR),
            @ApiResponse(responseCode = StorageException.STATUCODE_ERROR, description = StorageException.MESSAGE_ERROR),
            @ApiResponse(responseCode = ActiviteNotFoundException.STATUCODE_ERROR, description = ActiviteNotFoundException.MESSAGE_ERROR)
    })
    public Resource getActivityDocument(@Valid @Min(1) @PathVariable("idVacance") Long idVacance, @Valid @Min(1) @PathVariable("idActivity") Long idActivity, @Valid @Min(1) @PathVariable("filename") String filename, HttpServletResponse response);

}
