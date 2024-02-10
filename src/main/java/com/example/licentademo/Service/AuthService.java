package com.example.licentademo.Service;
import java.util.logging.Logger;
import com.example.licentademo.DTO.AuthRequest;
import com.example.licentademo.DTO.AuthResponse;
import com.example.licentademo.Models.RegisterRequest;
import com.example.licentademo.Models.User;
import com.example.licentademo.Repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AuthService {
    private static final Logger logger = Logger.getLogger(AuthService.class.getName());
    private final PasswordEncoder passwordEncoder;

    private final UserRepo userRepo;

    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {

        var user= User.builder()
                        .name(request.getName())
                                .email(request.getEmail())
                                        .password(passwordEncoder.encode(request.getPassword()))
                                                .build();
        userRepo.save(user);
        AuthResponse response=AuthResponse.builder().userId(user.getUserId()).build();
        return response;
    }

    public AuthResponse login(AuthRequest request){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );
        User user=userRepo.findByEmail(request.getEmail()).orElse(null);

        assert user != null;
        logger.info(user.getEmail());
        return AuthResponse.builder().userId(user.getUserId()).build();
    }
}
