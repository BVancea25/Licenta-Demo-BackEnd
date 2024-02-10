package com.example.licentademo.Controllers;

import com.example.licentademo.DTO.AuthRequest;
import com.example.licentademo.DTO.AuthResponse;
import com.example.licentademo.Models.RegisterRequest;
import com.example.licentademo.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
public class AuthController {
    private final AuthService service;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request){
        return service.register(request);
    }

    @PostMapping("/authenticate")
    public AuthResponse login(@RequestBody AuthRequest request){
        return service.login(request);
    }


}
