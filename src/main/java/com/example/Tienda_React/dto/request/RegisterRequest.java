package com.example.Tienda_React.dto.request;





import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String direccion;
}
