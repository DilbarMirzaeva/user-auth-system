package intern.backend_tasks.service;

import intern.backend_tasks.dto.request.UpdateUserRequest;
import intern.backend_tasks.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse getUserById(int id);
    UserResponse getUserByEmail(String email);
    UserResponse getUserByUsername(String username);
    List<UserResponse> getAllUsers();

    UserResponse updateUserByEmail(String email, UpdateUserRequest userRequest);
    UserResponse updateUserById(int id, UpdateUserRequest userRequest);

    void deleteUserById(int id);
    void deleteUserByEmail(String email);
}
