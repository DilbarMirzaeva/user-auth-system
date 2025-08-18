package intern.backend_tasks.service.impl;

import intern.backend_tasks.dto.request.UpdateUserRequest;
import intern.backend_tasks.dto.response.UserResponse;
import intern.backend_tasks.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public UserResponse getUserById(int id) {
        return null;
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        return null;
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        return null;
    }

    @Override
    public UserResponse updateUser(String email, UpdateUserRequest userRequest) {
        return null;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return List.of();
    }

    @Override
    public void deleteUserById(int id) {

    }

    @Override
    public void deleteUserByEmail(String email) {

    }
}
