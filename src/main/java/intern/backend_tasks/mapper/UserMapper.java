package intern.backend_tasks.mapper;

import intern.backend_tasks.domain.entity.User;
import intern.backend_tasks.dto.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<User,UserResponse> {
    @Override
     User toEntity(UserResponse userResponse);
    @Override
     UserResponse toDto(User user);
}
