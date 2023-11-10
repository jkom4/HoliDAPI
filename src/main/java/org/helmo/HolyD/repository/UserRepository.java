package org.helmo.HolyD.repository;

import org.helmo.HolyD.repository.DTO.RoleDTO;
import org.helmo.HolyD.repository.DTO.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.OffsetDateTime;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDTO, Long> {

    Optional<UserDTO> findByEmailAndPasswd(String email, String passwd); //to SignIn
    boolean existsByEmail(String email); // to verifie if email already exist

    Optional<UserDTO> findByEmail(String email);

    int countDistinctByRoleIs(RoleDTO roleUser);

    @Query(value = "SELECT COUNT(u.id) FROM UserDTO u JOIN u.vacances v Where v.dateFin >= ?1 AND v.dateDebut <= ?2") //DISTINCT  TO_TIMESTAMP(?1,'DD-MON-RRHH24:MI:SS.FF')
    int countNbrOfUserInHolidayForRange(OffsetDateTime dateDebutRange, OffsetDateTime dateFinRange);

}