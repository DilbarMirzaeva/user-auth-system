package intern.backend_tasks.mapper;

import intern.backend_tasks.domain.entity.User;
import intern.backend_tasks.dto.request.UpdateUserRequest;
import intern.backend_tasks.dto.response.UserResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {
     User toEntity(UserResponse userResponse);
     UserResponse toDto(User user);

     @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
     void updateUser(UpdateUserRequest updateUserRequest,@MappingTarget User user);
}
