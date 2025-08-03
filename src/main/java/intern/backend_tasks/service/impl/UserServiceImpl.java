package intern.backend_tasks.service.impl;

import intern.backend_tasks.domain.entity.User;
import intern.backend_tasks.domain.repository.UserRepository;
import intern.backend_tasks.dto.request.ForgetPasswordRequest;
import intern.backend_tasks.dto.request.LoginRequest;
import intern.backend_tasks.dto.request.RegisterRequest;
import intern.backend_tasks.dto.request.ResetPasswordRequest;
import intern.backend_tasks.dto.response.ApiResponse;
import intern.backend_tasks.dto.response.LoginResponse;
import intern.backend_tasks.exception.AlreadyRegisteredException;
import intern.backend_tasks.security.JwtUtil;
import intern.backend_tasks.service.EmailService;
import intern.backend_tasks.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;
    private final EmailService emailService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public ApiResponse register(RegisterRequest registerRequest) {
        if(userRepository.findByEmail(registerRequest.getEmail()).isPresent()){
            throw new AlreadyRegisteredException("User already registered");
        }
         User user=new User();
        user.setEmail(registerRequest.getEmail());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        userRepository.save(user);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("User registered successfully");
        return apiResponse;
    }

    @Override
    public ApiResponse forgetPassword(ForgetPasswordRequest forgetPasswordRequest) {
        return null;
    }

    @Override
    public void resetPassword(ResetPasswordRequest resetPasswordRequest) {

    }

    @Override
    public void logout(String token) {

    }
}
