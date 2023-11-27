package org.helmo.HolyD.services;

import org.helmo.HolyD.controlers.exception.UserAlreadyInsideException;
import org.helmo.HolyD.controlers.exception.UserNotFoundException;
import org.helmo.HolyD.controlers.exception.VacanceNotFoundException;
import org.helmo.HolyD.controlers.exception.ActiviteNotFoundException;
import org.helmo.HolyD.models.reponses.Activite;
import org.helmo.HolyD.models.requests.ParticipantAdd;
import org.helmo.HolyD.repository.ActiviteRepository;
import org.helmo.HolyD.repository.DTO.ActiviteDTO;
import org.helmo.HolyD.repository.DTO.UserDTO;
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
