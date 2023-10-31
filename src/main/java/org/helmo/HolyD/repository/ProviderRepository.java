package org.helmo.HolyD.repository;

import org.helmo.HolyD.repository.DTO.ProviderDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<ProviderDTO, Long> {
}
