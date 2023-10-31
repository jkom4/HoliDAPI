package org.helmo.HolyD.config;

import org.helmo.HolyD.repository.DTO.ProviderDTO;
import org.helmo.HolyD.repository.DTO.RoleDTO;
import org.helmo.HolyD.repository.DTO.enums.ProviderType;
import org.helmo.HolyD.repository.DTO.enums.RoleType;
import org.helmo.HolyD.repository.ProviderRepository;
import org.helmo.HolyD.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final ProviderRepository providerRepository;

    public DataInitializer(RoleRepository roleRepository, ProviderRepository providerRepository) {
        this.roleRepository = roleRepository;
        this.providerRepository = providerRepository;
    }
    @Override
    public void run(String... args) {
        setRoles();
        setProviders();

    }
    
    private void setRoles(){
        for (RoleType roleType: RoleType.values()) {
            roleRepository.save(new RoleDTO(roleType));
        }
        
    }
    private void setProviders(){
        for (ProviderType providerType: ProviderType.values()) {
            providerRepository.save(new ProviderDTO(providerType));
        }
    }
}
