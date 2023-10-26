package org.helmo.HolyD.repository;

import org.helmo.HolyD.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndPasswd(String email, String passwd); //to SignIn
    boolean existsByEmail(String email); // to verifie if email already exist

}