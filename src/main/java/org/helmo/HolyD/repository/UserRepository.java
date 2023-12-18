package org.helmo.HolyD.repository;

import org.helmo.HolyD.repository.DTO.RoleDTO;
import org.helmo.HolyD.repository.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDTO, Long> {

    Optional<UserDTO> findByEmailAndPasswd(String email, String passwd); //to SignIn
    boolean existsByEmail(String email); // to verifie if email already exist

    void deleteByEmail(String email);

    Optional<UserDTO> findByEmail(String email);

    int countDistinctByRoleIs(RoleDTO roleUser);

    String oracleQuery = "SELECT v.lieu.pays as pays, COUNT(u.id) as nbrUserInHoliday FROM UserDTO u JOIN u.vacances v Where trunc(v.dateFin) >= trunc(?1) AND trunc(v.dateDebut) <= trunc(?1) GROUP BY v.lieu.pays";
    String h2Query = "SELECT v.lieu.pays as pays, COUNT(u.id) as nbrUserInHoliday FROM UserDTO u JOIN u.vacances v Where trunc(v.dateFin + 1) > ?1 AND trunc(v.dateDebut) <= ?1 GROUP BY v.lieu.pays";
    @Query(value =  oracleQuery)
    Collection<Tuple> countNbrOfUserInHolidayForADate(OffsetDateTime dateTime);
}