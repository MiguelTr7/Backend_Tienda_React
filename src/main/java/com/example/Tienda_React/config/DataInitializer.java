// src/main/java/com/example/Tienda_React/config/DataInitializer.java

package com.example.Tienda_React.config;

import com.example.Tienda_React.model.Role;
import com.example.Tienda_React.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepository roleRepository;

    @PostConstruct
    public void initRoles() {
        if (roleRepository.findByName(Role.ERole.ROLE_CLIENTE).isEmpty()) {
            Role cliente = new Role();
            cliente.setName(Role.ERole.ROLE_CLIENTE);
            roleRepository.save(cliente);
        }

        if (roleRepository.findByName(Role.ERole.ROLE_ADMIN).isEmpty()) {
            Role admin = new Role();
            admin.setName(Role.ERole.ROLE_ADMIN);
            roleRepository.save(admin);
        }

        if (roleRepository.findByName(Role.ERole.ROLE_VENDEDOR).isEmpty()) {
            Role vendedor = new Role();
            vendedor.setName(Role.ERole.ROLE_VENDEDOR);
            roleRepository.save(vendedor);
        }
    }
}
