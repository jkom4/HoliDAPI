package org.helmo.HolyD.repository;

import org.helmo.HolyD.repository.DTO.RoleDTO;
import org.helmo.HolyD.repository.DTO.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDTO, Long> {

    Optional<UserDTO> findByEmailAndPasswd(String email, String passwd); //to SignIn
    boolean existsByEmail(String email); // to verifie if email already exist

    Optional<UserDTO> findByEmail(String email);

    int countDistinctByRoleIs(RoleDTO roleUser);

    @Query(value = "SELECT v.lieu.pays as pays, COUNT(u.id) as nbrUserInHoliday FROM UserDTO u JOIN u.vacances v Where v.dateFin >= ?1 AND v.dateDebut <= ?2 GROUP BY v.lieu.pays")
    Collection<Tuple> countNbrOfUserInHolidayForRange(OffsetDateTime dateDebutRange, OffsetDateTime dateFinRange);

}