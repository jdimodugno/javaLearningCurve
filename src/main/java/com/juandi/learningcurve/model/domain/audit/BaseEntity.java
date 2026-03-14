package com.juandi.learningcurve.model.domain.audit;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@MappedSuperclass
public class BaseEntity {

  @Version
  @Column(nullable = false)
  private static final Long version = 0L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
}
