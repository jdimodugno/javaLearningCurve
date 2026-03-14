package com.juandi.learningcurve.controller;

import com.juandi.learningcurve.dto.CreateUserRequest;
import com.juandi.learningcurve.dto.PagedResponse;
import com.juandi.learningcurve.dto.UpdateUserRequest;
import com.juandi.learningcurve.dto.UserResponse;
import com.juandi.learningcurve.mapper.UserMapper;
import com.juandi.learningcurve.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService service;

  public UserController(UserService service) {
    this.service = service;
  }

  @PostMapping
  public UserResponse create(@Valid @RequestBody final CreateUserRequest request) {
    return UserMapper.toResponse(service.create(request));
  }

  @GetMapping("/{id}")
  public UserResponse getUser(@PathVariable final Long id) {
    return UserMapper.toResponse(this.service.findByIdOrThrow(id));
  }

  @GetMapping
  public PagedResponse<UserResponse> getUsers(final Pageable pageable) {
    return PagedResponse.fromPage(service
        .findAll(pageable)
        .map(UserMapper::toResponse));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable final Long id) {
    service.deleteById(id);
  }

  @PutMapping("/{id}")
  public UserResponse update(
      @PathVariable final Long id,
      @Valid @RequestBody final UpdateUserRequest request
  ) {
    return UserMapper.toResponse(service.update(id, request));
  }
}
