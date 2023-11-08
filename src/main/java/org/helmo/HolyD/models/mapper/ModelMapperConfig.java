package org.helmo.HolyD.models.mapper;

import org.helmo.HolyD.models.reponses.User;
import org.helmo.HolyD.models.reponses.Vacance;
import org.helmo.HolyD.models.requests.UserSignUp;
import org.helmo.HolyD.repository.DTO.UserDTO;
import org.helmo.HolyD.repository.DTO.VacanceDTO;
import org.helmo.HolyD.repository.DTO.enums.RoleType;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(UserDTO.class, User.class)
                .addMapping(UserDTO::getRoleName, User::setRole);
        modelMapper.typeMap(UserSignUp.class, UserDTO.class)
               .addMapping(UserSignUp::getDefaultRoleType, UserDTO::setRoleWithRoleType);
        return modelMapper;
    }
}
