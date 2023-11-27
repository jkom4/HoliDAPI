package org.helmo.HolyD.services;

import org.helmo.HolyD.controlers.exception.DateTimeIntervalIsNotAIntervalException;
import org.helmo.HolyD.controlers.exception.UserAlreadyInsideException;
import org.helmo.HolyD.controlers.exception.UserNotFoundException;
import org.helmo.HolyD.controlers.exception.VacanceNotFoundException;
import org.helmo.HolyD.models.reponses.Activite;
import org.helmo.HolyD.models.reponses.Vacance;
import org.helmo.HolyD.models.requests.ActiviteAdd;
import org.helmo.HolyD.models.requests.ParticipantAdd;
import org.helmo.HolyD.models.requests.VacanceAdd;
import org.helmo.HolyD.repository.ActiviteRepository;
import org.helmo.HolyD.repository.DTO.ActiviteDTO;
import org.helmo.HolyD.repository.DTO.UserDTO;
import org.helmo.HolyD.repository.DTO.VacanceDTO;
import org.helmo.HolyD.repository.UserRepository;
import org.helmo.HolyD.repository.VacanceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class VacanceService {

    private final VacanceRepository vacanceRepository;
    private final UserRepository userRepository;
    private final ActiviteRepository activiteRepository;
    private final ModelMapper modelMapper;

    public VacanceService(VacanceRepository vacanceRepository, UserRepository userRepository, ActiviteRepository activiteRepository, ModelMapper modelMapper) {
        this.vacanceRepository = vacanceRepository;
        this.userRepository = userRepository;
        this.activiteRepository = activiteRepository;
        this.modelMapper = modelMapper;
    }

    public Vacance add(VacanceAdd vacanceAdd) {
        if(vacanceAdd.getDateDebut().isAfter(vacanceAdd.getDateFin()))
            throw new DateTimeIntervalIsNotAIntervalException();
        UserDTO userConnected = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        VacanceDTO vacanceDTOToAdd = modelMapper.map(vacanceAdd, VacanceDTO.class);
        vacanceDTOToAdd.setOwner(userConnected);
        vacanceDTOToAdd.addParticipant(userConnected);
        VacanceDTO vacanceDTOSaved = vacanceRepository.saveAndFlush(vacanceDTOToAdd);
        return modelMapper.map(vacanceDTOSaved, Vacance.class);
    }

    public Vacance addParticipant(ParticipantAdd participantAdd){
        UserDTO userConnected = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDTO = userRepository.findByEmail(participantAdd.getEmail())
                .orElseThrow(UserNotFoundException::new);
        VacanceDTO vacanceDTO = vacanceRepository.findByIdAndParticipantsContains(participantAdd.getId(), userConnected)
                .orElseThrow(VacanceNotFoundException::new);
        if(!vacanceDTO.addParticipant(userDTO)){
            throw new  UserAlreadyInsideException();
        }
        return modelMapper.map(vacanceRepository.saveAndFlush(vacanceDTO), Vacance.class);
    }
}