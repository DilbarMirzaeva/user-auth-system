package intern.backend_tasks.service;

import intern.backend_tasks.dto.request.UpdateUserRequest;
import intern.backend_tasks.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse getUserById(Long id);
    UserResponse getUserByUsername(String username);
    List<UserResponse> getAllUsers();

    UserResponse updateUserById(Long id, UpdateUserRequest userRequest);

    void deleteUserById(Long id);
}
