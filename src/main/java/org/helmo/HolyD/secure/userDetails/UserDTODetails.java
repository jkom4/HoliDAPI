package org.helmo.HolyD.secure.userDetails;

import org.helmo.HolyD.repository.DTO.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDTODetails extends UserDTO implements UserDetails  {

    private static final long serialVersionUID = 1L;


    public UserDTODetails(UserDTO userDTO) {
        super(userDTO.getId(), userDTO.getRole(), userDTO.getNom(), userDTO.getPrenom(), userDTO.getEmail(), userDTO.getPasswd(), userDTO.getTokenConnection(), userDTO.getOwnedVacances(), userDTO.getOwnedActivites(), userDTO.getVacances(), userDTO.getSendedMessages());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+this.getRoleName()));
    }

    @Override
    public String getPassword() {
        return this.getPasswd();
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
