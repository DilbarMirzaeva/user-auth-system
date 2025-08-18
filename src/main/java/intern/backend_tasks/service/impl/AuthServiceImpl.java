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
import intern.backend_tasks.exception.InvalidPasswordException;
import intern.backend_tasks.exception.InvalidVerificationCodeException;
import intern.backend_tasks.exception.UserNotFoundException;
import intern.backend_tasks.security.JwtUtil;
import intern.backend_tasks.service.EmailService;
import intern.backend_tasks.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final EmailService emailService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user=userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(()->new UserNotFoundException("User not found with email: "+loginRequest.getEmail()));

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new InvalidPasswordException("Invalid password");
        }

        String token= jwtUtil.generateToken(user.getEmail());

        return new LoginResponse(token, user.getEmail());
    }

    @Override
    public ApiResponse register(RegisterRequest registerRequest) {
        if(userRepository.findByEmail(registerRequest.getEmail()).isPresent()){
            throw new AlreadyRegisteredException("User already registered with email: "+registerRequest.getEmail());
        }
         User user=new User();
        user.setEmail(registerRequest.getEmail());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        userRepository.save(user);

        return new ApiResponse("User registered successfully");
    }

    @Override
    public ApiResponse forgetPassword(ForgetPasswordRequest forgetPasswordRequest) {
        User user=userRepository.findByEmail(forgetPasswordRequest.getEmail())
                .orElseThrow(()->new UserNotFoundException("User not found with email: "+forgetPasswordRequest.getEmail()));

        String verificationCode=generateVerificationCode();
        emailService.sendVerificationEmail(user.getEmail(),verificationCode);
        user.setVerificationCode(verificationCode);

        userRepository.save(user);

        return new ApiResponse("Verification code sent successfully");
    }

    public String generateVerificationCode(){
        Random random=new Random();
        int code=100000+random.nextInt(900000);
        return String.valueOf(code);
    }

    @Override
    public void resetPassword(ResetPasswordRequest resetPasswordRequest) {
        User user=userRepository.findByEmail(resetPasswordRequest.getEmail())
                .orElseThrow(()->new UserNotFoundException("User not found with email: "+resetPasswordRequest.getEmail()));

        if(!Objects.equals(user.getVerificationCode(), resetPasswordRequest.getVerificationCode())){
            throw new InvalidVerificationCodeException("Invalid verification code");
        }

        user.setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));
        user.setVerificationCode(null);

        userRepository.save(user);
    }

}
