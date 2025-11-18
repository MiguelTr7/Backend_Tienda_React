package com.example.Tienda_React.controller;



import com.example.Tienda_React.dto.request.LoginRequest;
import com.example.Tienda_React.dto.request.RegisterRequest;
import com.example.Tienda_React.dto.response.LoginResponse;
import com.example.Tienda_React.dto.response.MessageResponse;
import com.example.Tienda_React.service.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
