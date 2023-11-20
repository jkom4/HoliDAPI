package org.helmo.HolyD.controlers;

import org.helmo.HolyD.controlers.swagger.VacanceControlerSwagger;
import org.helmo.HolyD.models.reponses.Vacance;
import org.helmo.HolyD.models.requests.ParticipantAdd;
import org.helmo.HolyD.models.requests.VacanceAdd;
import org.helmo.HolyD.services.VacanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(value = "/vacance", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class VacanceControler implements VacanceControlerSwagger {

    private final VacanceService vacanceService;

    public VacanceControler(VacanceService vacanceService) {
        this.vacanceService = vacanceService;
    }


    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/add")
    public Vacance addVacance(@Valid @RequestBody VacanceAdd vacanceAdd) {
        return vacanceService.add(vacanceAdd);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/addParticipant")
    public Vacance addParticipant(@Valid @RequestBody ParticipantAdd participantAdd) {
        return vacanceService.addParticipant(participantAdd);
    }
}
