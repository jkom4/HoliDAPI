package org.helmo.HolyD.services;

import org.helmo.HolyD.models.reponses.Vacance;
import org.helmo.HolyD.models.requests.VacanceAdd;
import org.helmo.HolyD.repository.DTO.UserDTO;
import org.helmo.HolyD.repository.DTO.VacanceDTO;
import org.helmo.HolyD.repository.VacanceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class VacanceService {

    private final VacanceRepository vacanceRepository;
    private final ModelMapper modelMapper;

    public VacanceService(VacanceRepository vacanceRepository, ModelMapper modelMapper) {
        this.vacanceRepository = vacanceRepository;
        this.modelMapper = modelMapper;
    }

    public Vacance add(VacanceAdd vacanceAdd) {
        UserDTO userConnected = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        VacanceDTO vacanceDTOToAdd = modelMapper.map(vacanceAdd, VacanceDTO.class);
        vacanceDTOToAdd.setOwner(userConnected);
        VacanceDTO vacanceDTOSaved = vacanceRepository.saveAndFlush(vacanceDTOToAdd);
        return modelMapper.map(vacanceDTOSaved, Vacance.class);
    }
}
