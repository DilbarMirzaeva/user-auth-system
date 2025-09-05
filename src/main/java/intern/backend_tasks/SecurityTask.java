package intern.backend_tasks;

import intern.backend_tasks.domain.entity.User;
import intern.backend_tasks.domain.enums.Role;
import intern.backend_tasks.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityTask {
    
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(SecurityTask.class, args);
    }

    @Bean
    CommandLineRunner initAdmin(){
        return args -> {
            if(userRepository.findByEmail("admin@admin.com").isEmpty()){
                User admin=new User();
                admin.setEmail("admin@admin.com");
                admin.setPassword(passwordEncoder.encode("admin2345"));
                admin.setUsername("Dilbar");
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);
            }
        };
    }

}
