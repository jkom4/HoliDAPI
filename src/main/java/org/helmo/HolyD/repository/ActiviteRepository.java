package org.helmo.HolyD.repository;

import org.helmo.HolyD.repository.DTO.ActiviteDTO;
import org.helmo.HolyD.repository.DTO.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActiviteRepository extends JpaRepository<ActiviteDTO, Long> {
    Optional<ActiviteDTO> findByIdAndParticipantsContains(Long id, UserDTO userDTO);
}
