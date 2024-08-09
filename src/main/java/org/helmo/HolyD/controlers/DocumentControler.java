package org.helmo.HolyD.controlers;

import org.helmo.HolyD.controlers.swagger.DocumentControlerSwagger;
import org.helmo.HolyD.services.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/document")
public class DocumentControler implements DocumentControlerSwagger {

    private final StorageService storageService;

    public DocumentControler(StorageService storageService) {
        this.storageService = storageService;

    }

    // Upload File
    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/vacance/{idVacance}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void fileVacanceUpload(@Valid @Min(1) @PathVariable("idVacance") Long idVacance, @Validated @RequestPart("file") MultipartFile file) {
        storageService.store(file, idVacance);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/vacance/{idVacance}/activite/{idActivity}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void fileActivityUpload(@Valid @Min(1) @PathVariable("idVacance") Long idVacance, @Valid @Min(1) @PathVariable("idActivity") Long idActivity, @Validated @RequestPart("file") MultipartFile file) {
        storageService.store(file, idVacance, idActivity);
    }

    //List filename
    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/vacance/{idVacance}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> listVacanceFilenameDocuments(@Valid @Min(1) @PathVariable("idVacance") Long idVacance) {
        return storageService.listFilesNames(idVacance);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/vacance/{idVacance}/activite/{idActivity}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> listActivityFilenameDocuments(@Valid @Min(1) @PathVariable("idVacance") Long idVacance, @Valid @Min(1) @PathVariable("idActivity") Long idActivity) {
        return storageService.listFilesNames(idVacance, idActivity);
    }

    //Download File
    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/vacance/{idVacance}/{filename}", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Resource getVacanceDocument(@Valid @Min(1) @PathVariable("idVacance") Long idVacance, @Valid @Min(1) @PathVariable("filename") String filename, HttpServletResponse response) {
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");
        return storageService.getFileAsResource(idVacance, filename);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/vacance/{idVacance}/activite/{idActivity}/{filename}", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Resource getActivityDocument(@Valid @Min(1) @PathVariable("idVacance") Long idVacance, @Valid @Min(1) @PathVariable("idActivity") Long idActivity, @Valid @Min(1) @PathVariable("filename") String filename, HttpServletResponse response) {
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");
        return storageService.getFileAsResource(idVacance, idActivity, filename);
    }
}
