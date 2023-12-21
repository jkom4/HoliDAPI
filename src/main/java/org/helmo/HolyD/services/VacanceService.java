package org.helmo.HolyD.services;

import org.helmo.HolyD.controlers.exception.*;
import org.helmo.HolyD.models.reponses.Vacance;
import org.helmo.HolyD.models.requests.MessageAdd;
import org.helmo.HolyD.models.requests.ParticipantAdd;
import org.helmo.HolyD.models.requests.VacanceAdd;
import org.helmo.HolyD.repository.DTO.MessageDTO;
import org.helmo.HolyD.repository.DTO.UserDTO;
import org.helmo.HolyD.repository.DTO.VacanceDTO;
import org.helmo.HolyD.repository.MessageRepository;
import org.helmo.HolyD.repository.UserRepository;
import org.helmo.HolyD.repository.VacanceRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VacanceService {

    private final VacanceRepository vacanceRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final ModelMapper modelMapper;

    public VacanceService(VacanceRepository vacanceRepository, UserRepository userRepository, MessageRepository messageRepository, ModelMapper modelMapper) {
        this.vacanceRepository = vacanceRepository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.modelMapper = modelMapper;
    }

    public Vacance add(VacanceAdd vacanceAdd) {
        if(vacanceAdd.getDateDebut().isAfter(vacanceAdd.getDateFin()) || vacanceAdd.getDateDebut().isEqual(vacanceAdd.getDateFin()))
            throw new DateTimeIntervalIsNotAIntervalException();
        else if (vacanceAdd.getDateDebut().isBefore(OffsetDateTime.now()) || vacanceAdd.getDateFin().isBefore(OffsetDateTime.now()))
            throw new DateTimeIsInPastException();
        UserDTO userConnected = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userConnected.userIsAlreadyInHoliday(vacanceAdd.getDateDebut(), vacanceAdd.getDateFin())) {
            throw new UserAlreadyInHolidayException();
        }
        VacanceDTO vacanceDTOToAdd = modelMapper.map(vacanceAdd, VacanceDTO.class);
        vacanceDTOToAdd.setOwner(userConnected);
        vacanceDTOToAdd.addParticipant(userConnected);
        VacanceDTO vacanceDTOSaved = vacanceRepository.saveAndFlush(vacanceDTOToAdd);
        return modelMapper.map(vacanceDTOSaved, Vacance.class);
    }

    public Vacance addParticipant(Long idVacance, ParticipantAdd participantAdd){
        UserDTO userConnected = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDTO = userRepository.findByEmail(participantAdd.getEmail())
                .orElseThrow(UserNotFoundException::new);
        VacanceDTO vacanceDTO = vacanceRepository.findByIdAndParticipantsContains(idVacance, userConnected)
                .orElseThrow(VacanceNotFoundException::new);
        if(userDTO.userIsAlreadyInHoliday(vacanceDTO.getDateDebut(), vacanceDTO.getDateFin())) {
            throw new UserAlreadyInHolidayException();
        }
        if(!vacanceDTO.addParticipant(userDTO)){
            throw new UserAlreadyInsideException();
        }
        //Set<Long> usersId = vacanceDTO.getParticipants().stream().map(UserDTO::getId).collect(Collectors.toSet());
        //sendMessageSSEToclients(usersId, true);
        return modelMapper.map(vacanceRepository.saveAndFlush(vacanceDTO), Vacance.class);
    }

    public Vacance addMessage(Long idvacance, MessageAdd messageAdd){
        UserDTO userConnected = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        VacanceDTO vacanceDTO = vacanceRepository.findByIdAndParticipantsContains(idvacance, userConnected)
                .orElseThrow(VacanceNotFoundException::new);
        vacanceDTO.addMessage(modelMapper.map(messageAdd, MessageDTO.class), userConnected);
        vacanceDTO = vacanceRepository.saveAndFlush(vacanceDTO);
        vacanceDTO.setMessages(messageRepository.findTop100ByVacanceOrderBySendingDateDesc(vacanceDTO));
        //Set<Long> usersId = vacanceDTO.getParticipants().stream().map(UserDTO::getId).collect(Collectors.toSet());
        //sendMessageSSEToclients(usersId, false);
        return modelMapper.map(vacanceDTO, Vacance.class);
    }
    public Vacance getMessages(Long idVacance){ // changer en list si le temps
        UserDTO userConnected = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        VacanceDTO vacanceDTO = vacanceRepository.findByIdAndParticipantsContains(idVacance, userConnected)
                .orElseThrow(VacanceNotFoundException::new);
        vacanceDTO.setMessages(messageRepository.findTop100ByVacanceOrderBySendingDateDesc(vacanceDTO));
        return modelMapper.map(vacanceDTO, Vacance.class);
    }
}