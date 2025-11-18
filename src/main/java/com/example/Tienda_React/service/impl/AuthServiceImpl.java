package com.example.Tienda_React.service.impl;

import com.example.Tienda_React.dto.request.LoginRequest;
import com.example.Tienda_React.dto.request.RegisterRequest;
import com.example.Tienda_React.dto.response.LoginResponse;
import com.example.Tienda_React.dto.response.MessageResponse;
import com.example.Tienda_React.model.Role;
import com.example.Tienda_React.model.User;
import com.example.Tienda_React.repository.RoleRepository;
import com.example.Tienda_React.repository.UserRepository;
import com.example.Tienda_React.security.CustomUserDetails;
import com.example.Tienda_React.security.JwtUtils;
import com.example.Tienda_React.service.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    @Override
    public MessageResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return new MessageResponse("Error: El correo ya está en uso!");
        }

        User user = User.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .direccion(request.getDireccion())
                .build();

        Role defaultRole = roleRepository.findByName(Role.ERole.ROLE_CLIENTE)
                .orElseThrow(() -> new RuntimeException("Error: No se encontró el rol CLIENTE"));
        user.setRoles(Set.of(defaultRole));

        userRepository.save(user);
        return new MessageResponse("Usuario registrado exitosamente!");
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // 🔹 Usuario autenticado con éxito
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        // 🔹 Generar token JWT
        String jwt = jwtUtils.generateJwtToken(authentication);

        // 🔹 Roles del usuario autenticado
        List<String> roles = userDetails.getAuthorities().stream()
                .map(r -> r.getAuthority())
                .toList();

        return new LoginResponse(jwt, userDetails.getUsername(), roles);
    }
}
