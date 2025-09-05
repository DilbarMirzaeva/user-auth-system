package intern.backend_tasks.controller;

import intern.backend_tasks.dto.request.UpdateUserRequest;
import intern.backend_tasks.dto.response.UserResponse;
import intern.backend_tasks.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<UserResponse>> findAll(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserResponse> findByUsername(@RequestParam String username){
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER')and @userServiceImpl.getUserById(#id).email==authentication.name)")
    public ResponseEntity<UserResponse> updateById(@PathVariable Long id,@Valid @RequestBody UpdateUserRequest userRequest){
        return ResponseEntity.ok(userService.updateUserById(id, userRequest));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("(hasRole('ADMIN') and @userServiceImpl.getUserById(#id).email!=authentication.name)" +
            "or (hasRole('USER') and @userServiceImpl.getUserById(#id).email==authentication.name)")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

}
