package org.helmo.HolyD.controlers;

import org.helmo.HolyD.controlers.swagger.UserControlerSwagger;
import org.helmo.HolyD.models.requests.UserSignIn;
import org.helmo.HolyD.models.requests.UserSignUp;
import org.helmo.HolyD.repository.DTO.UserDTO;
import org.helmo.HolyD.repository.UserRepository;
import org.helmo.HolyD.repository.exception.UserAlreadyExistException;
import org.helmo.HolyD.repository.exception.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserControler implements UserControlerSwagger {

    private final ModelMapper modelMapper;
    private final UserRepository repository;


    public UserControler(UserRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    @PutMapping("/signup")
    public ResponseEntity<UserDTO> putUser(@Valid @RequestBody UserSignUp user){
        if (repository.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistException();
        }

        return new ResponseEntity<UserDTO>(repository.saveAndFlush(modelMapper.map(user, UserDTO.class)), HttpStatus.OK);
    }
    @Override
    @PostMapping("/signin")
    public UserDTO postUser(@Valid @RequestBody UserSignIn user){
        return repository.findByEmailAndPasswd(user.getEmail(), user.getPasswd())
                .orElseThrow(UserNotFoundException::new);
    }
}
