package org.helmo.HolyD.controlers;

import org.helmo.HolyD.controlers.swagger.UserControlerSwagger;
import org.helmo.HolyD.repository.DTO.UserDTO;
import org.helmo.HolyD.repository.UserRepository;
import org.helmo.HolyD.repository.exception.UserAlreadyExistException;
import org.helmo.HolyD.repository.exception.UserNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public UserDTO putUser(@Valid @RequestBody UserDTO user){
        if (repository.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistException();
        }
        return repository.saveAndFlush(user);
    }
    @Override
    @PostMapping("/signin")
    public UserDTO postUser(@Valid @RequestBody UserDTO user){
        return repository.findByEmailAndPasswd(user.getEmail(), user.getPasswd())
                .orElseThrow(UserNotFoundException::new);
    }
}
