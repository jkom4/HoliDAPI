package org.helmo.HolyD.controlers;

import org.helmo.HolyD.models.User;
import org.helmo.HolyD.repository.UserRepository;
import org.helmo.HolyD.repository.exception.UserNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControler {

    private final UserRepository repository;


    public UserControler(UserRepository repository) {
        this.repository = repository;
    }

    @PutMapping("/user/signup")
    User putUser(@RequestBody User user){
        if (repository.existsByEmail(user.getEmail())){
            throw new UserNotFoundException();
        }
        return repository.saveAndFlush(user);
    }
    @PostMapping("/user/signin")
    User postUser(@RequestBody User user){

        return repository.findByEmailAndPasswd(user.getEmail(), user.getPasswd());
    }
}
