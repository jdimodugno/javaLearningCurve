package com.juandi.learningcurve.service;

import com.juandi.learningcurve.core.service.BaseService;
import com.juandi.learningcurve.dto.CreateUserRequest;
import com.juandi.learningcurve.dto.UpdateUserRequest;
import com.juandi.learningcurve.exception.DuplicateResourceException;
import com.juandi.learningcurve.mapper.UserMapper;
import com.juandi.learningcurve.model.domain.User;
import com.juandi.learningcurve.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, Long> {

  private final UserRepository repository;

  public UserService(final UserRepository repository) {
    this.repository = repository;
  }

  public User create(final CreateUserRequest request) {
    if (getRepository().existsByEmail(request.email())) {
      throw new DuplicateResourceException(
          "User with email " + request.email() + " already exists"
      );
    }
    final User user = UserMapper.toEntity(request);
    return getRepository().save(user);
  }

  public User update(
      final Long id,
      final UpdateUserRequest request
  ) {
    final User currentUser = findByIdOrThrow(id);
    if (
        !currentUser.getEmail().equals(request.email())
            && getRepository().existsByEmail(request.email())
    ) {
      throw new DuplicateResourceException(
          "User with email " + request.email() + " already exists"
      );
    }
    UserMapper.updateEntity(currentUser, request);
    return getRepository().save(currentUser);
  }

  @Override
  protected UserRepository getRepository() {
    return repository;
  }

  @Override
  protected Class<?> getEntityClass() {
    return User.class;
  }
}
