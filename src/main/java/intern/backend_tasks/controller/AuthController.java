package intern.backend_tasks.controller;

import intern.backend_tasks.dto.request.ForgetPasswordRequest;
import intern.backend_tasks.dto.request.LoginRequest;
import intern.backend_tasks.dto.request.RegisterRequest;
import intern.backend_tasks.dto.request.ResetPasswordRequest;
import intern.backend_tasks.dto.response.ApiResponse;
import intern.backend_tasks.dto.response.LoginResponse;
import intern.backend_tasks.service.impl.AuthServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {
    private final AuthServiceImpl userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody   LoginRequest loginRequest){
        return ResponseEntity.ok(userService.login(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse>  register(@Valid @RequestBody RegisterRequest registerRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(registerRequest));
    }

    @PostMapping("/forget-password")
    public ResponseEntity<ApiResponse> forgetPassword(@Valid @RequestBody ForgetPasswordRequest forgetPasswordRequest){
        return ResponseEntity.ok(userService.forgetPassword(forgetPasswordRequest));
    }

    @PostMapping("/reset")
    public ResponseEntity<Void> resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest){
        userService.resetPassword(resetPasswordRequest);
        return ResponseEntity.noContent().build();
    }



}
