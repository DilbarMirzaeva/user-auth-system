package intern.backend_tasks.domain.repository;

import intern.backend_tasks.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
