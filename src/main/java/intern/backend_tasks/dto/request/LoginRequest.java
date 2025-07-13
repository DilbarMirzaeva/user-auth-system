package intern.backend_tasks.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @Email(message = "Must be a valid email")
    private String email;

    @NotBlank(message = "password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
}
