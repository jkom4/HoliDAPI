package org.helmo.HolyD.controlers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.helmo.HolyD.controlers.swagger.UserControlerSwagger;
import org.helmo.HolyD.models.User;
import org.helmo.HolyD.repository.UserRepository;
import org.helmo.HolyD.repository.exception.UserNotFoundException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserControler implements UserControlerSwagger {

    private final UserRepository repository;


    public UserControler(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @PutMapping("/signup")
    public User putUser(@RequestBody User user){
        if (repository.existsByEmail(user.getEmail())){
            throw new UserNotFoundException();
        }
        return repository.saveAndFlush(user);
    }
    @Override
    @PostMapping("/signin")
    public User postUser(@RequestBody User user){
        return repository.findByEmailAndPasswd(user.getEmail(), user.getPasswd());
    }
}
