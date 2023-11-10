package org.helmo.HolyD.repository;

import org.helmo.HolyD.repository.DTO.UserDTO;
import org.helmo.HolyD.repository.DTO.VacanceDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VacanceRepository extends JpaRepository<VacanceDTO, Long> {

    Optional <VacanceDTO> findByIdAndParticipantsContains(Long id, UserDTO userDTO);
}
