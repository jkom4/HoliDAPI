package org.helmo.HolyD.repository;

import org.helmo.HolyD.repository.DTO.VacanceDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacanceRepository extends JpaRepository<VacanceDTO, Long> {
}
