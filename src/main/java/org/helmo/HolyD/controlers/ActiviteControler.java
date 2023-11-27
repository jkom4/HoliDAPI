package org.helmo.HolyD.controlers;

import org.helmo.HolyD.controlers.swagger.ActiviteControlerSwagger;
import org.helmo.HolyD.models.reponses.Activite;
import org.helmo.HolyD.models.requests.ActiviteAdd;
import org.helmo.HolyD.models.requests.ParticipantAdd;
import org.helmo.HolyD.services.ActiviteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(value = "/activite", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ActiviteControler implements ActiviteControlerSwagger {

    private final ActiviteService activiteService;

    public ActiviteControler(ActiviteService activiteService) {
        this.activiteService = activiteService;
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/add")
    public Activite add(@Valid @RequestBody ActiviteAdd activiteAdd) {
        return activiteService.add(activiteAdd);
    }
    
    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/addParticipant")
    public Activite addParticipant(@Valid @RequestBody ParticipantAdd participantAdd) {
        return activiteService.addParticipant(participantAdd);
    }
}
