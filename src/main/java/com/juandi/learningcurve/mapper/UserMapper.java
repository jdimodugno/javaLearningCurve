package com.juandi.learningcurve.mapper;

import com.juandi.learningcurve.dto.CreateUserRequest;
import com.juandi.learningcurve.dto.UpdateUserRequest;
import com.juandi.learningcurve.dto.UserResponse;
import com.juandi.learningcurve.model.domain.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserMapper {

  public static UserResponse toResponse(final User user) {
    return new UserResponse(
        user.getId(),
        user.getName(),
        user.getEmail()
    );
  }

  public static User toEntity(final CreateUserRequest request) {
    return new User(
        request.name(),
        request.email()
    );
  }

  public static void updateEntity(
      final User userToUpdate,
      final UpdateUserRequest request
  ) {
    userToUpdate.setName(request.name());
    userToUpdate.setEmail(request.email());
  }
}
