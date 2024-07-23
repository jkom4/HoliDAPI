package org.helmo.HolyD.controlers;

import org.helmo.HolyD.controlers.exception.StorageException;
import org.helmo.HolyD.controlers.swagger.DocumentControlerSwagger;
import org.helmo.HolyD.controlers.swagger.VacanceControlerSwagger;
import org.helmo.HolyD.models.reponses.Vacance;
import org.helmo.HolyD.models.requests.VacanceAdd;
import org.helmo.HolyD.services.ActiviteService;
import org.helmo.HolyD.services.StorageService;
import org.helmo.HolyD.services.VacanceService;
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
import java.io.IOException;
import java.util.Arrays;
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
    @PostMapping(value = "/vacance/{idVacance}/activity/{idActivity}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
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
    @GetMapping(value = "/vacance/{idVacance}/activity/{idActivity}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> listActivityFilenameDocuments(@Valid @Min(1) @PathVariable("idVacance") Long idVacance, @Valid @Min(1) @PathVariable("idActivity") Long idActivity) {
        return storageService.listFilesNames(idVacance, idActivity);
    }

    //Download File
    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/vacance/{idVacance}/{filename}")
    public Resource getVacanceDocument(@Valid @Min(1) @PathVariable("idVacance") Long idVacance, @Valid @Min(1) @PathVariable("filename") String filename, HttpServletResponse response) {
        Resource resource = storageService.getFileAsResource(idVacance, filename);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        try {
            response.setContentLength((int) resource.contentLength());
        }catch (IOException e) {
            throw new StorageException();
        }
        return resource;
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/vacance/{idVacance}/activity/{idActivity}/{filename}")
    public Resource getActivityDocument(@Valid @Min(1) @PathVariable("idVacance") Long idVacance, @Valid @Min(1) @PathVariable("idActivity") Long idActivity, @Valid @Min(1) @PathVariable("filename") String filename, HttpServletResponse response) {
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        return storageService.getFileAsResource(idVacance, idActivity, filename);
    }
}
