package intern.backend_tasks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyRegisteredException.class)
    public ResponseEntity<GlobalResponse> alreadyRegisteredExceptionHandler(AlreadyRegisteredException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                GlobalResponse.builder()
                        .error(ErrorMessage.ALREADY_REGISTERED)
                        .uuid(UUID.randomUUID())
                        .timestamp(LocalDateTime.now())
                        .message(e.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(InvalidVerificationCodeException.class)
    public ResponseEntity<GlobalResponse> invalidVerificationHandler(InvalidVerificationCodeException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                GlobalResponse.builder()
                        .error(ErrorMessage.INVALID_VERIFICATION_CODE)
                        .uuid(UUID.randomUUID())
                        .timestamp(LocalDateTime.now())
                        .message(e.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<GlobalResponse> invalidPasswordHandling(InvalidPasswordException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                GlobalResponse.builder()
                        .error(ErrorMessage.INVALID_PASSWORD)
                        .uuid(UUID.randomUUID())
                        .timestamp(LocalDateTime.now())
                        .message(e.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<GlobalResponse> userNotFoundHandling(UserNotFoundException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                GlobalResponse.builder()
                        .error(ErrorMessage.USER_NOT_FOUND)
                        .uuid(UUID.randomUUID())
                        .timestamp(LocalDateTime.now())
                        .message(e.getMessage())
                        .build()
        );
    }

}
