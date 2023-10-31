package org.helmo.HolyD.repository;

import org.helmo.HolyD.repository.DTO.RoleDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleDTO, Long> {
}
