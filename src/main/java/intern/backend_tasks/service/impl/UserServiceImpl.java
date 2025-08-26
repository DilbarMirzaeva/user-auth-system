package intern.backend_tasks.service.impl;

import intern.backend_tasks.domain.repository.UserRepository;
import intern.backend_tasks.dto.request.UpdateUserRequest;
import intern.backend_tasks.dto.response.UserResponse;
import intern.backend_tasks.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
    public UserResponse updateUserByEmail(String email, UpdateUserRequest userRequest) {
        return null;
    }

    @Override
    public UserResponse updateUserById(int id, UpdateUserRequest userRequest) {
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
