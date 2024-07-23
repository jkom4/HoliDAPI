package org.helmo.HolyD.controlers;

import org.helmo.HolyD.controlers.swagger.VacanceControlerSwagger;
import org.helmo.HolyD.models.reponses.Activite;
import org.helmo.HolyD.models.reponses.Vacance;
import org.helmo.HolyD.models.requests.*;
import org.helmo.HolyD.services.ActiviteService;
import org.helmo.HolyD.services.StorageService;
import org.helmo.HolyD.services.VacanceService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/vacance")
public class VacanceControler implements VacanceControlerSwagger {

    private final VacanceService vacanceService;
    private final ActiviteService activiteService;

    public VacanceControler(VacanceService vacanceService, ActiviteService activiteService) {
        this.vacanceService = vacanceService;
        this.activiteService = activiteService;

    }


    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Vacance addVacance(@Valid @RequestBody VacanceAdd vacanceAdd) {
        return vacanceService.add(vacanceAdd);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/{idVacance}/participant", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Vacance addParticipantToVacance(@Valid @Min(1) @PathVariable("idVacance") Long idVacance, @Valid @RequestBody ParticipantAdd participantAdd) {
        return vacanceService.addParticipant(idVacance, participantAdd);
    }
    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/{idVacance}/activite", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Activite add(@Valid @Min(1) @PathVariable("idVacance") Long idVacance, @Valid @RequestBody ActiviteAdd activiteAdd) {
        return activiteService.add(idVacance, activiteAdd);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/{idVacance}/activite/{idActivite}/participant", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Activite addParticipantToActivite(@Valid @Min(1) @PathVariable("idVacance") Long idVacance, @Valid @Min(1) @PathVariable("idActivite") Long idActivite, @Valid @RequestBody ParticipantAdd participantAdd) {
        return activiteService.addParticipant(idVacance, idActivite, participantAdd);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{idVacance}/activite/{idActivite}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Activite changeDateTimeOfActivite(@Valid @Min(1) @PathVariable("idVacance") Long idVacance, @Valid @Min(1) @PathVariable("idActivite") Long idActivite, @Valid @RequestBody OffsetDateTimeChange offsetDateTimeChange) {
        return activiteService.changeDateActivite(idVacance, idActivite, offsetDateTimeChange);
    }
    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/{idVacance}/message", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Vacance addMessage(@Valid @Min(1) @PathVariable("idVacance") Long idVacance, @Valid @RequestBody MessageAdd messageAdd){
        return vacanceService.addMessage(idVacance, messageAdd);
    }
    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{idVacance}/message", produces = MediaType.APPLICATION_JSON_VALUE)
    public Vacance getMessages(@Valid @Min(1) @PathVariable("idVacance") Long idVacance){
        return vacanceService.getMessages(idVacance);
    }
}
