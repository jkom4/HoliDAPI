package org.helmo.HolyD.services;

import org.helmo.HolyD.controlers.exception.UserAlreadyExistException;
import org.helmo.HolyD.controlers.exception.UserNotFoundException;
import org.helmo.HolyD.models.reponses.User;
import org.helmo.HolyD.models.requests.UserSignIn;
import org.helmo.HolyD.models.requests.UserSignUp;
import org.helmo.HolyD.repository.DTO.UserDTO;
import org.helmo.HolyD.repository.UserRepository;
import org.helmo.HolyD.secure.jwt.JwtService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final Argon2PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, Argon2PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public User signUp(UserSignUp user) throws UserAlreadyExistException{
        if (userRepository.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistException();
        }
        String hashedPassword = passwordEncoder.encode(user.getPasswd());
        user.setPasswd(hashedPassword);
        User newUserReponse = modelMapper.map(userRepository.saveAndFlush(modelMapper.map(user, UserDTO.class)), User.class);
        newUserReponse.setTokenConnectionAPI(jwtService.generate(newUserReponse.getEmail()));
        return newUserReponse;
    }

    public User signIn(UserSignIn user) throws UserNotFoundException {
        User userResponse = null;
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPasswd())
            );
            if(authentication.isAuthenticated()){
                String token = jwtService.generate(user.getEmail());
                userResponse = modelMapper.map(userRepository.findByEmail(user.getEmail()), User.class);
                userResponse.setTokenConnectionAPI(token);
            }}catch (Exception ex){
                throw new UserNotFoundException();
        }
        return userResponse;
    }
}
