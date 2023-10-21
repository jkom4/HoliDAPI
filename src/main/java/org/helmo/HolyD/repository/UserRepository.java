package org.helmo.HolyD.repository;

import org.helmo.HolyD.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User signUp(User user);
    User signIn(User user);


}