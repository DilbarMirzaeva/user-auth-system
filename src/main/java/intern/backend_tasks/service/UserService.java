package intern.backend_tasks.service;

import intern.backend_tasks.dto.request.ForgetPasswordRequest;
import intern.backend_tasks.dto.request.LoginRequest;
import intern.backend_tasks.dto.request.RegisterRequest;
import intern.backend_tasks.dto.request.ResetPasswordRequest;
import intern.backend_tasks.dto.response.ApiResponse;
import intern.backend_tasks.dto.response.LoginResponse;

public interface UserService {
    LoginResponse login(LoginRequest loginRequest);

    ApiResponse register(RegisterRequest registerRequest);

    ApiResponse forgetPassword(ForgetPasswordRequest forgetPasswordRequest);

    void resetPassword(ResetPasswordRequest resetPasswordRequest);

    void logout(String token);
}
