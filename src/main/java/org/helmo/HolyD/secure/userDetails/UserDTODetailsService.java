package org.helmo.HolyD.secure.userDetails;

import org.helmo.HolyD.controlers.exception.UserNotFoundException;
import org.helmo.HolyD.repository.DTO.UserDTO;
import org.helmo.HolyD.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDTODetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDTODetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException(UserNotFoundException.MESSAGE_ERROR));
        return new UserDTODetails(userDTO);
    }
}
