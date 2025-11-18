package com.example.Tienda_React.service.interfaces;


import com.example.Tienda_React.dto.request.LoginRequest;
import com.example.Tienda_React.dto.request.RegisterRequest;
import com.example.Tienda_React.dto.response.LoginResponse;
import com.example.Tienda_React.dto.response.MessageResponse;

public interface AuthService {
    MessageResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}
