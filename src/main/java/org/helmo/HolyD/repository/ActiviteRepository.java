package org.helmo.HolyD.repository;

import org.helmo.HolyD.repository.DTO.ActiviteDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiviteRepository extends JpaRepository<ActiviteDTO, Long> {
}
