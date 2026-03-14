package com.juandi.learningcurve.repository;

import com.juandi.learningcurve.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  boolean existsByEmail(final String email);
}
