package intern.backend_tasks.service.impl;

import intern.backend_tasks.domain.entity.User;
import intern.backend_tasks.domain.repository.UserRepository;
import intern.backend_tasks.dto.request.UpdateUserRequest;
import intern.backend_tasks.dto.response.UserResponse;
import intern.backend_tasks.exception.UserNotFoundException;
import intern.backend_tasks.mapper.UserMapper;
import intern.backend_tasks.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserResponse getUserById(Long id) {
        User user=findUser(id);
        return userMapper.toDto(user);
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        User user=findUser(username);
        return userMapper.toDto(user);
    }

    @Override
    public UserResponse updateUserById(Long id, UpdateUserRequest userRequest) {
        User user=findUser(id);
        userMapper.updateUser(userRequest,user);

        if(user.getPassword()!=null && !userRequest.getPassword().isBlank()){
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        }
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public <T> User findUser(T val){
        if(val instanceof Long id){
            return userRepository.findById(id)
                    .orElseThrow(()->new UserNotFoundException("User not found with id: "+id));
        }else if (val instanceof String username) {
            return userRepository.findByUsername(username)
                    .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
        }
        throw new IllegalArgumentException("Unsupported type: " + val.getClass());
    }
}
