package org.helmo.HolyD.repository;

import org.helmo.HolyD.repository.DTO.MessageDTO;
import org.helmo.HolyD.repository.DTO.VacanceDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface MessageRepository extends JpaRepository<MessageDTO, Long> {

    Set<MessageDTO> findTopByVacanceOrderBySendingDateDesc(VacanceDTO vacanceDTO);
}
