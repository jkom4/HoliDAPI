package org.helmo.HolyD.controlers;

import org.helmo.HolyD.controlers.swagger.UserControlerSwagger;
import org.helmo.HolyD.models.reponses.User;
import org.helmo.HolyD.models.requests.UserSignIn;
import org.helmo.HolyD.models.requests.UserSignUp;
import org.helmo.HolyD.repository.DTO.UserDTO;
import org.helmo.HolyD.controlers.exception.UserAlreadyExistException;
import org.helmo.HolyD.controlers.exception.UserNotFoundException;
import org.helmo.HolyD.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserControler implements UserControlerSwagger {

    private final ModelMapper modelMapper;
    private final UserRepository repository;

    private final Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(16, 32, 1, 4096, 3);


    public UserControler(UserRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
    public User signUp(@Valid @RequestBody UserSignUp user){
        if (repository.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistException();
        }
        String hashedPassword = encoder.encode(user.getPasswd());
        user.setPasswd(hashedPassword);
        System.out.println(modelMapper.map(user, UserDTO.class));
        return modelMapper.map(repository.saveAndFlush(modelMapper.map(user, UserDTO.class)),User.class);
    }
    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/signin", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public User signIn(@Valid @RequestBody UserSignIn user){
        String hashedPassword = encoder.encode(user.getPasswd());
        user.setPasswd(hashedPassword);
        UserDTO userDTO = repository.findByEmailAndPasswd(user.getEmail(), user.getPasswd())
                .orElseThrow(UserNotFoundException::new);
        return modelMapper.map(userDTO, User.class);
    }
}
