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

    public Activite add(Long idVacance, ActiviteAdd activiteAdd){
        if(activiteAdd.getDateDebut().isAfter(activiteAdd.getDateFin()) || activiteAdd.getDateDebut().isEqual(activiteAdd.getDateFin()))
            throw new DateTimeIntervalIsNotAIntervalException();
        else if (activiteAdd.getDateDebut().isBefore(OffsetDateTime.now()) || activiteAdd.getDateFin().isBefore(OffsetDateTime.now()))
            throw new DateTimeIsInPastException();
        UserDTO userConnected = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        VacanceDTO vacanceDTOToAddActiviteIn = vacanceRepository.findByIdAndParticipantsContains(idVacance, userConnected)
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

    public Activite addParticipant(Long idvacance, Long idActivite, ParticipantAdd participantAdd){
        UserDTO userConnected = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDTO = userRepository.findByEmail(participantAdd.getEmail())
                .orElseThrow(UserNotFoundException::new);
        ActiviteDTO activiteDTO = activiteRepository.findById(idActivite)
                .orElseThrow(ActiviteNotFoundException::new);
        if(idvacance.longValue() != activiteDTO.getVacance().getId()) {
            throw new ActiviteNotFoundException();
        }
        VacanceDTO vacanceDTO = vacanceRepository.findByIdAndParticipantsContains(activiteDTO.getVacance().getId(), userConnected)
                .orElseThrow(VacanceNotFoundException::new);
        if (vacanceDTO.userHasAlreadyAtciviteForDateTimeInterval(userDTO, activiteDTO.getDateDebut(), activiteDTO.getDateFin()))
            throw new UserAlreadyInActiviteException();
        if(!activiteDTO.addParticipant(userDTO)){
            throw new UserAlreadyInsideException(); // Exception qui ne sera plus levée
        }
        return modelMapper.map(activiteRepository.saveAndFlush(activiteDTO), Activite.class);
    }

    public Activite changeDateActivite(Long idvacance, Long idActivite, OffsetDateTimeChange offsetDateTimeChange){
        if(offsetDateTimeChange.getDateDebut().isAfter(offsetDateTimeChange.getDateFin()) || offsetDateTimeChange.getDateDebut().isEqual(offsetDateTimeChange.getDateFin()))
            throw new DateTimeIntervalIsNotAIntervalException();
        else if (offsetDateTimeChange.getDateDebut().isBefore(OffsetDateTime.now()) || offsetDateTimeChange.getDateFin().isBefore(OffsetDateTime.now()))
            throw new DateTimeIsInPastException();
        UserDTO userConnected = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        VacanceDTO vacanceDTOToEditActiviteIn = vacanceRepository.findByIdAndParticipantsContains(idvacance, userConnected)
                .orElseThrow(VacanceNotFoundException::new);
        if(vacanceDTOToEditActiviteIn.getActivites().stream().noneMatch(activite -> activite.getId().longValue() == idActivite.longValue())){
            throw new VacanceNotFoundException();
        }
        if(!vacanceDTOToEditActiviteIn.intervalIsInside(offsetDateTimeChange.getDateDebut(), offsetDateTimeChange.getDateFin()))
            throw new IntervalActiviteIsNotInIntervalVacanceException();
        else if (vacanceDTOToEditActiviteIn.userHasAlreadyAtciviteForDateTimeIntervalWithOutOneActi(userConnected, offsetDateTimeChange.getDateDebut(), offsetDateTimeChange.getDateFin(), idActivite)) // ne pas tenir compte de l'acti qu'on veut modifier
            throw new UserAlreadyInActiviteException();
        if(!vacanceDTOToEditActiviteIn.isOwnerOfActivite(idActivite, userConnected.getId())){
            throw new ActiviteNotFoundException();
        }
        ActiviteDTO activiteDTOToEdit = vacanceDTOToEditActiviteIn.editDateOfActivite(idActivite, offsetDateTimeChange.getDateDebut(), offsetDateTimeChange.getDateFin());
        if(activiteDTOToEdit == null)
            throw new ActiviteNotFoundException();
        return modelMapper.map(activiteRepository.saveAndFlush(activiteDTOToEdit), Activite.class);
    }
}
