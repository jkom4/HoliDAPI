package org.helmo.HolyD.repository.initializer;

import org.helmo.HolyD.repository.DTO.RoleDTO;
import org.helmo.HolyD.repository.DTO.enums.RoleType;
import org.helmo.HolyD.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public void run(String... args) {
        setRoles();
    }
    
    private void setRoles(){
        for (RoleType roleType: RoleType.values()) {
            roleRepository.save(new RoleDTO(roleType));
        }
    }
}
