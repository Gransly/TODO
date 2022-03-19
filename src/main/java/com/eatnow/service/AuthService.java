package com.eatnow.service;

import com.eatnow.payload.request.LoginRequest;
import com.eatnow.payload.request.SignupRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> singIn(LoginRequest loginRequest);
    ResponseEntity<?> singUp(SignupRequest signUpRequest);
}
