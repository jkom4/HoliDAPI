package org.helmo.HolyD.services;

import org.helmo.HolyD.controlers.exception.*;
import org.helmo.HolyD.models.reponses.Activite;
import org.helmo.HolyD.models.requests.ActiviteAdd;
import org.helmo.HolyD.models.requests.OffsetDateTimeChange;
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

import java.time.OffsetDateTime;

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
        else if (activiteAdd.getDateDebut().isBefore(OffsetDateTime.now()) || activiteAdd.getDateFin().isBefore(OffsetDateTime.now()))
            throw new DateTimeIsInPastException();
        UserDTO userConnected = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        VacanceDTO vacanceDTOToAddActiviteIn = vacanceRepository.findByIdAndParticipantsContains(activiteAdd.getIdVacance(), userConnected)
                .orElseThrow(VacanceNotFoundException::new);
        if(!vacanceDTOToAddActiviteIn.intervalIsInside(activiteAdd.getDateDebut(), activiteAdd.getDateFin()))
            throw new IntervalActiviteIsNotInIntervalVacanceException();
        else if (vacanceDTOToAddActiviteIn.userHasAlreadyAtciviteForDateTimeInterval(userConnected, activiteAdd.getDateDebut(), activiteAdd.getDateFin()))
            throw new UserAlreadyInActiviteException();
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
        VacanceDTO vacanceDTO = vacanceRepository.findByIdAndParticipantsContains(activiteDTO.getVacance().getId(), userConnected)
                .orElseThrow(VacanceNotFoundException::new);
        if (vacanceDTO.userHasAlreadyAtciviteForDateTimeInterval(userDTO, activiteDTO.getDateDebut(), activiteDTO.getDateFin()))
            throw new UserAlreadyInActiviteException();
        if(!activiteDTO.addParticipant(userDTO)){
            throw new UserAlreadyInsideException(); // Exception qui ne sera plus levée
        }
        return modelMapper.map(activiteRepository.saveAndFlush(activiteDTO), Activite.class);
    }

    public Activite changeDateActivite(OffsetDateTimeChange offsetDateTimeChange){
        if(offsetDateTimeChange.getDateDebut().isAfter(offsetDateTimeChange.getDateFin()))
            throw new DateTimeIntervalIsNotAIntervalException();
        else if (offsetDateTimeChange.getDateDebut().isBefore(OffsetDateTime.now()) || offsetDateTimeChange.getDateFin().isBefore(OffsetDateTime.now()))
            throw new DateTimeIsInPastException();
        UserDTO userConnected = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        VacanceDTO vacanceDTOToEditActiviteIn = vacanceRepository.findByIdAndParticipantsContains(offsetDateTimeChange.getId(), userConnected)
                .orElseThrow(VacanceNotFoundException::new);
        if(!vacanceDTOToEditActiviteIn.intervalIsInside(offsetDateTimeChange.getDateDebut(), offsetDateTimeChange.getDateFin()))
            throw new IntervalActiviteIsNotInIntervalVacanceException();
        else if (vacanceDTOToEditActiviteIn.userHasAlreadyAtciviteForDateTimeIntervalWithOutOneActi(userConnected, offsetDateTimeChange.getDateDebut(), offsetDateTimeChange.getDateFin(), offsetDateTimeChange.getId())) // ne pas tenir compte de l'acti qu'on veut modifier
            throw new UserAlreadyInActiviteException();

        ActiviteDTO activiteDTOToEdit = vacanceDTOToEditActiviteIn.editDateOfActivite(offsetDateTimeChange.getId(), offsetDateTimeChange.getDateDebut(), offsetDateTimeChange.getDateFin());
        if(activiteDTOToEdit == null)
            throw new ActiviteNotFoundException();
        return modelMapper.map(activiteRepository.saveAndFlush(activiteDTOToEdit), Activite.class);
    }
}
