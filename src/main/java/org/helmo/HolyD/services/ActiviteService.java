package org.helmo.HolyD.services;

import org.helmo.HolyD.controlers.exception.*;
import org.helmo.HolyD.models.reponses.Activite;
import org.helmo.HolyD.models.requests.ActiviteAdd;
import org.helmo.HolyD.models.requests.ParticipantAdd;
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
public class ActiviteService {
    private final VacanceRepository vacanceRepository;
    private final UserRepository userRepository;
    private final ActiviteRepository activiteRepository;
    private final ModelMapper modelMapper;

    public ActiviteService(VacanceRepository vacanceRepository, UserRepository userRepository, ActiviteRepository activiteRepository, ModelMapper modelMapper) {
        this.vacanceRepository = vacanceRepository;
        this.userRepository = userRepository;
        this.activiteRepository = activiteRepository;
        this.modelMapper = modelMapper;
    }

    public Activite add(ActiviteAdd activiteAdd){
        if(activiteAdd.getDateDebut().isAfter(activiteAdd.getDateFin()))
            throw new DateTimeIntervalIsNotAIntervalException();
        UserDTO userConnected = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        VacanceDTO vacanceDTOToAddActiviteIn = vacanceRepository.findByIdAndParticipantsContains(activiteAdd.getIdVacance(), userConnected)
                .orElseThrow(VacanceNotFoundException::new);
        ActiviteDTO activiteDTOToAdd = modelMapper.map(activiteAdd, ActiviteDTO.class);
        activiteDTOToAdd.setOwner(userConnected);
        activiteDTOToAdd.addParticipant(userConnected);
        activiteDTOToAdd.setVacance(vacanceDTOToAddActiviteIn);
        return modelMapper.map(activiteRepository.saveAndFlush(activiteDTOToAdd), Activite.class);
    }

    public Activite addParticipant(ParticipantAdd participantAdd){
        UserDTO userConnected = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDTO = userRepository.findByEmail(participantAdd.getEmail())
                .orElseThrow(UserNotFoundException::new);
        ActiviteDTO activiteDTO = activiteRepository.findById(participantAdd.getId())
                .orElseThrow(ActiviteNotFoundException::new);
        vacanceRepository.findByIdAndParticipantsContains(activiteDTO.getVacance().getId(), userConnected)
                .orElseThrow(VacanceNotFoundException::new);
        if(!activiteDTO.addParticipant(userDTO)){
            throw new UserAlreadyInsideException();
        }
        return modelMapper.map(activiteRepository.saveAndFlush(activiteDTO), Activite.class);
    }
}
