package com.hostlelo.groundeddoc.controller;

import com.hostlelo.groundeddoc.dto.LoginRequest;
import com.hostlelo.groundeddoc.dto.LoginResponse;
import com.hostlelo.groundeddoc.dto.RegisterUserRequest;
import com.hostlelo.groundeddoc.dto.UserDto;
import com.hostlelo.groundeddoc.entity.User;
import com.hostlelo.groundeddoc.repository.UserRepository;
import com.hostlelo.groundeddoc.service.CustomUserDetail;
import com.hostlelo.groundeddoc.service.JwtService;
import com.hostlelo.groundeddoc.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {


    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest loginRequest
    ) {
        //TODO
        //authenticate and return the token and user
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginRequest.username(),
                loginRequest.password()
        );
        Authentication authenticated = authenticationManager.authenticate(authentication);
        User user = userRepository.findByUsername(loginRequest.username()).orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(new CustomUserDetail(user));
        var response = new LoginResponse(token, new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getRole()));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(
            @Valid @RequestBody RegisterUserRequest registerUserRequest
    ) {
        UserDto userDto = userService.registerUser(registerUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);

    }


}
